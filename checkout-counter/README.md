# Checkout Counter

Checkout Counter app supports scanning of products (adding them to cart) and generating the itemized bill with tax details.

It is created as a monolithic application. It can easily converted to microservice project by creating four services - billing, product, taxation and customer. Therefore, you would find some duplicate code.

No JPA layer has been added. However, code is written in same manner. In-memory (Concurrent HashMap) is used for storing data.

## REST Endpoints
There are various endpoints available in this application. However, we would specifically talk about the feature for generating the itemized bill with tax details.
### Add items in customer's (id: 1) cart
Cart entry contains the barcode and ordered quantity in ```barcode:quantity``` format. Ideally a separate keys (barcode, quantity) and values (123456789008, 1) preferred.
```
curl -X POST \
  http://localhost:8080/v1/customers/1/cart \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"cartEntry": {
		"123456789008": 1
	}
}'
```
#### Output
```
Status: 201 Created
```
You can add same item again. It would increase the order quantity by given quantity
``` bash
curl -X POST \
  http://localhost:8080/v1/customers/1/cart \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"cartEntry": {
		"123456789008": 1
	}
}'
```
#### Output
``` json
Status: 201 Created
```
We would add one more item to our cart:
``` bash
curl -X POST \
  http://localhost:8080/v1/customers/1/cart \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"cartEntry": {
		"123456789005": 2
	}
}'
```
#### Output
``` json
Status: 201 Created
```
### Generate the bill for given customer
``` bash
curl -X POST \
  http://localhost:8080/v1/bills/customer/1 \
  -H 'Accept-Language: de' \
  -H 'Cache-Control: no-cache' \  
```
#### Output
``` json
{
    "name": "Le Meurice",
    "id": "3",
    "address": "228 rue de Rivoli, 75001, Paris",
    "dateTime": "2018-05-08T18:33:33.458+0000",
    "billItems": [
        {
            "code": "123456789005",
            "name": "Product 5",
            "category": "B",
            "price": 300,
            "quantity": 2,
            "taxPercentage": 20,
            "taxAmount": 120,
            "totalPrice": 600,
            "totalPriceWithTax": 720
        },
        {
            "code": "123456789008",
            "name": "Product 8",
            "category": "B",
            "price": 600,
            "quantity": 2,
            "taxPercentage": 20,
            "taxAmount": 240,
            "totalPrice": 1200,
            "totalPriceWithTax": 1440
        }
    ],
    "totalTax": 360,
    "totalPrice": 1800,
    "grandTotal": 2160
}
```
### Get the Generated the bill by its Id
You would get the same bill generated above
``` bash
curl -X GET \
  http://localhost:8080/v1/bills/3 \
  -H 'Cache-Control: no-cache' \
```
#### Output
``` json
{
    "name": "Le Meurice",
    "id": "3",
    "address": "228 rue de Rivoli, 75001, Paris",
    "dateTime": "2018-05-08T18:33:33.458+0000",
    "billItems": [
        {
            "code": "123456789005",
            "name": "Product 5",
            "category": "B",
            "price": 300,
            "quantity": 2,
            "taxPercentage": 20,
            "taxAmount": 120,
            "totalPrice": 600,
            "totalPriceWithTax": 720
        },
        {
            "code": "123456789008",
            "name": "Product 8",
            "category": "B",
            "price": 600,
            "quantity": 2,
            "taxPercentage": 20,
            "taxAmount": 240,
            "totalPrice": 1200,
            "totalPriceWithTax": 1440
        }
    ],
    "totalTax": 360,
    "totalPrice": 1800,
    "grandTotal": 2160
}
```

## Testing
Spring Test, JUnit, Mockito and Hemcrest libs are used for Unit and Integration testing.

Integration tests are added for 5 endpoints in test class: ```src\test\java\srb\samples\shopping\checkout\CheckoutApplicationIntegrationTests.java```

Unit tests are added for BillController and BillService at ```src\test\java\srb\samples\shopping\checkout\domain\billing```

## i18n
Exception messages are added in two languages - default (en_US) and German (de)
