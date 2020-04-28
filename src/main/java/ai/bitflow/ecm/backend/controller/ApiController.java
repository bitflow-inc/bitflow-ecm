package ai.bitflow.ecm.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.bitflow.ecm.backend.domain.elastic.EsFile;
import ai.bitflow.ecm.backend.service.EcmService;
import ai.bitflow.ecm.backend.vo.req.ContentPutRequest;
import ai.bitflow.ecm.backend.vo.res.ContentsResponse;
import ai.bitflow.ecm.backend.vo.res.GeneralResponse;
import ai.bitflow.ecm.backend.vo.res.SearchResponse;
import ai.bitflow.ecm.backend.vo.res.result.ContentResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author method76
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/ecm") 
public class ApiController {
	
	private final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	private EcmService eservice;
	
	@PutMapping("put")
	public GeneralResponse put(ContentPutRequest params) {
		GeneralResponse ret = new GeneralResponse();
		eservice.saveFile(params);
		return ret;
	}
	
	@GetMapping("/doc/{id}")
	public ContentsResponse doc(@PathVariable String id) {
		ContentsResponse ret = new ContentsResponse();
		EsFile item = eservice.getContents(id);
		ContentResult result = new ContentResult();
		logger.debug("title " + item.getTitle());
		result.setId(item.getId());
		result.setTitle(item.getTitle());
		result.setContents(item.getHtmlcontent());
		ret.setResult(result);
		return ret;
	}
	
	@GetMapping("/search/{keyword}")
	public SearchResponse search(@PathVariable String keyword) {
		logger.debug("keyword " + keyword);
		SearchResponse ret = new SearchResponse();
		List<EsFile> files = eservice.search(keyword);
		ret.setResult(files);
		return ret;
	}
	
}
