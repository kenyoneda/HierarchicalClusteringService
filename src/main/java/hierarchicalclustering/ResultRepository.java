package hierarchicalclustering;

import org.springframework.data.repository.CrudRepository;

import hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.data.HierClusterOutput;

/**
 * @author Ken Yoneda
 * Interface automatically implemented on the fly by Spring Data JPA (Java Persistence API). 
 */
public interface ResultRepository extends CrudRepository<HierClusterOutput, Long> {
	
}
