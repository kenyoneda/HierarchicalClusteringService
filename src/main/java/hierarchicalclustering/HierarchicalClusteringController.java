package hierarchicalclustering;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.data.HierClusterInput;
import hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.data.HierClusterOutput;
import hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.service.HierClusterService;

@RestController
public class HierarchicalClusteringController {
	
	private final AtomicLong jobId = new AtomicLong();
	private final ResultRepository resultRepository;
	
	@RequestMapping(value="/execute", method = RequestMethod.POST)
	public ResponseEntity<HierClusterOutput> execute(@RequestBody HierClusterInput input) {
		return new ResponseEntity<HierClusterOutput>(new HierClusterService().execute(input), HttpStatus.OK);
	}
	
	@RequestMapping(value="/submit", method = RequestMethod.POST)
	public ResponseEntity<?> createTask(@RequestBody HierClusterInput data) {
		new Thread(){
				public void run() {
					HierClusterOutput output = new HierClusterService().execute(data);
					resultRepository.save(output);
				}
		}.start();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		// Return URI here
		Long id = jobId.incrementAndGet();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentContextPath().path("/query/{id}").buildAndExpand(id).toUri());
		return new ResponseEntity<>(httpHeaders, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/query/{jobId}", method = RequestMethod.GET)
	public ResponseEntity<HierClusterOutput> getResult(@PathVariable Long jobId) {
		return new ResponseEntity<HierClusterOutput>(this.resultRepository.findOne(jobId), HttpStatus.OK);
	}
	
	@Autowired
	HierarchicalClusteringController(ResultRepository resultRepository) {
		this.resultRepository = resultRepository;
	}
}