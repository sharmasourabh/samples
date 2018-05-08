package srb.samples.shopping.checkout.domain.product.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import srb.samples.shopping.checkout.domain.product.Category;
import srb.samples.shopping.checkout.domain.product.entity.Entity;
import srb.samples.shopping.checkout.domain.product.entity.Product;

/**
 *
 * @author Sourabh Sharma
 */
@Repository("productRepository")
public class InMemProductRepository implements ProductRepository<Product, String> {

	private Map<String, Product> entities;
	private static BigInteger sequence = BigInteger.valueOf(0L);

	/**
	 * Initialize the in-memory Product Repository with empty Map
	 */
	public InMemProductRepository() {
		entities = new ConcurrentHashMap<>();
		Product product = new Product(nextSequenceString(), "Product 1", "123456789001", Category.A,
				BigDecimal.valueOf(500.00), null);
		entities.put(product.getId(), product);
		product = new Product(nextSequenceString(), "Product 2", "123456789002", Category.B, BigDecimal.valueOf(400.00),
				null);
		entities.put(product.getId(), product);
		product = new Product(nextSequenceString(), "Product 3", "123456789003", Category.C, BigDecimal.valueOf(100.00),
				null);
		entities.put(product.getId(), product);
		product = new Product(nextSequenceString(), "Product 4", "123456789004", Category.A, BigDecimal.valueOf(200.00),
				null);
		entities.put(product.getId(), product);
		product = new Product(nextSequenceString(), "Product 5", "123456789005", Category.B, BigDecimal.valueOf(300.00),
				null);
		entities.put(product.getId(), product);
		product = new Product(nextSequenceString(), "Product 6", "123456789006", Category.C, BigDecimal.valueOf(400.00),
				null);
		entities.put(product.getId(), product);
		product = new Product(nextSequenceString(), "Product 7", "123456789007", Category.A, BigDecimal.valueOf(550.00),
				null);
		entities.put(product.getId(), product);
		product = new Product(nextSequenceString(), "Product 8", "123456789008", Category.B, BigDecimal.valueOf(600.00),
				null);
		entities.put(product.getId(), product);
		product = new Product(nextSequenceString(), "Product 9", "123456789009", Category.C, BigDecimal.valueOf(700.00),
				null);
		entities.put(product.getId(), product);
		product = new Product(nextSequenceString(), "Product 10", "123456789010", Category.A,
				BigDecimal.valueOf(900.00), null);
		entities.put(product.getId(), product);
	}

	/**
	 * Check if given product name already exist.
	 *
	 * @param name
	 * @return true if already exist, else false
	 */
	@Override
	public boolean containsName(String name) {
		try {
			return !this.findByName(name).isEmpty();
		} catch (Exception ex) {
			// Exception Handler
		}
		return false;
	}

	/**
	 *
	 * @param entity
	 */
	@Override
	public void add(Product entity) {
		entities.put(entity.getId(), entity);
	}

	/**
	 *
	 * @param id
	 */
	@Override
	public void remove(String id) {
		if (entities.containsKey(id)) {
			entities.remove(id);
		}
	}

	/**
	 *
	 * @param entity
	 */
	@Override
	public void update(Product entity) {
		if (entities.containsKey(entity.getId())) {
			entities.put(entity.getId(), entity);
		}
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@Override
	public boolean contains(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Product get(String id) {
		return entities.get(id);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Collection<Product> getAll() {
		return entities.values();
	}

	/**
	 *
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@Override
	public Collection<Product> findByName(String name) throws Exception {
		Collection<Product> products = new ArrayList<>();
		int noOfChars = name.length();
		entities.forEach((k, v) -> {
			if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
				products.add(v);
			}
		});
		return products;
	}

	@Override
	public Entity<?> findByBarcode(String barcode) throws Exception {
		List<Product> products = new ArrayList<>();
		entities.forEach((k, v) -> {
			if (v.getBarcode().equals(barcode)) {
				products.add(v);
			}
		});
		return products.get(0);
	}

	@Override
	public Map<String, Product> findByBarcodes(Set<String> barcodes) throws Exception {
		Map<String, Product> products = new HashMap<>();
		barcodes.forEach(barcode -> entities.forEach((k, v) -> {
			if (v.getBarcode().equals(barcode)) {
				products.put(barcode, v);
			}
		}));
		return products;
	}

	public static String getCurrentSequence() {
		return sequence.toString();
	}

	public static String nextSequenceString() {
		return nextSequence().toString();
	}

	public static BigInteger nextSequence() {
		sequence = sequence.add(BigInteger.valueOf(1L));
		return sequence;
	}

	@Override
	public boolean validateBarcode(String barcode) {
		List<Boolean> temp = new ArrayList<>();
		entities.forEach((k, v) -> {
			if (v.getBarcode().equals(barcode)) {
				temp.add(Boolean.TRUE);
			}
		});
		return !temp.isEmpty();
	}
}
