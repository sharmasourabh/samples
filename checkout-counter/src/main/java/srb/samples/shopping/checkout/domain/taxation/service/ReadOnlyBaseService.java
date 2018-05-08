package srb.samples.shopping.checkout.domain.taxation.service;

import srb.samples.shopping.checkout.domain.taxation.repository.ReadOnlyRepository;

/**
 *
 * @author Sourabh Sharma
 * @param <TE>
 * @param <T>
 */
public abstract class ReadOnlyBaseService<TE, T> {

    private ReadOnlyRepository<TE, T> repository;

    ReadOnlyBaseService(ReadOnlyRepository<TE, T> repository) {
        this.repository = repository;
    }
}
