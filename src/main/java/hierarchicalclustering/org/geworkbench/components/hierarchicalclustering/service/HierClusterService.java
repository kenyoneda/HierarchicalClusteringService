package hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.service;

import hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.computation.HNode;
import hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.computation.HierarchicalClustering;
import hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.data.HierClusterInput;
import hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.data.HierClusterOutput;

public class HierClusterService {

	public HierClusterOutput execute(HierClusterInput input) {		 
		  			 
			HNode node  = new HierarchicalClustering(input).compute();		 
			HierClusterOutput output = new HierClusterOutput(node);
			
			return output;
	}
}
