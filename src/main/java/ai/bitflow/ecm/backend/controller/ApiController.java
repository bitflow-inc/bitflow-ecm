package ai.bitflow.ecm.backend.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ContentsResponse get(@PathVariable String id) {
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
	
	@DeleteMapping("/doc/{id}")
	public ContentsResponse delete(@PathVariable String id) {
		ContentsResponse ret = new ContentsResponse();
		boolean success = eservice.deleteContent(id);
		return ret;
	}
	
	@GetMapping("/search/{keyword}")
	public SearchResponse search(@PathVariable String keyword) {
		logger.debug("keyword " + keyword);
		String[] keywords = keyword.split(" ");
		SearchResponse ret = new SearchResponse();
		List<EsFile> list = eservice.search(keyword);
		for (EsFile item : list) {
			for (String key : keywords) {
				String before = item.getText();
				item.setText(before.replaceAll("(?i)" + key, "<em>$0</em>"));
				item.setTitle(item.getTitle().replaceAll("(?i)" + key, "<em>$0</em>"));
				logger.debug("before " + before);
				logger.debug("after " + item.getText());
			}
		}
		ret.setResult(list);
		return ret;
	}
	
}
