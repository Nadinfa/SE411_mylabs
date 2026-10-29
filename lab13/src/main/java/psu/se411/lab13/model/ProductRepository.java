
package psu.se411.lab13.model;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface ProductRepository
    extends CrudRepository<Product, Integer>,
            PagingAndSortingRepository<Product, Integer> {
}
