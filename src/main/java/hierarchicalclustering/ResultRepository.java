package hierarchicalclustering;

import org.springframework.data.repository.CrudRepository;

import hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.data.HierClusterOutput;

public interface ResultRepository extends CrudRepository<HierClusterOutput, Long> {
	
}
