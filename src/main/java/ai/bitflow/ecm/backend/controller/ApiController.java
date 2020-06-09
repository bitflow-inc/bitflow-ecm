package ai.bitflow.ecm.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.bitflow.ecm.backend.domain.elastic.EsFile;
import ai.bitflow.ecm.backend.domain.elastic.FileTree;
import ai.bitflow.ecm.backend.service.EcmService;
import ai.bitflow.ecm.backend.vo.req.NewContentRequest;
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
	
	
	@PostMapping("/folder")
	public GeneralResponse folder(NewContentRequest params) {
		logger.debug("params " + params.toString());
		GeneralResponse ret = new GeneralResponse();
		if (params.getDirectory()!=null && params.getDirectory()) {
			FileTree item = eservice.saveDirectory(params);
			logger.debug("filetree " + item.toString());
		} else {
			eservice.newFile(params);
		}
		return ret;
	}
	
	@PostMapping("/folder/{parentid}")
	public GeneralResponse childFolder(NewContentRequest params, @PathVariable String parentid) {
		logger.debug("params " + params.toString());
		GeneralResponse ret = new GeneralResponse();
		if (params.getDirectory()!=null && params.getDirectory()) {
			boolean success = eservice.saveDirectory(params, parentid);
		}
		return ret;
	}
	
	@GetMapping("/doc/{id}")
	public ContentsResponse get(@PathVariable String id) {
		ContentsResponse ret = new ContentsResponse();
		EsFile item = eservice.getContents(id);
		ContentResult result = new ContentResult();
		if (item!=null) {
			logger.debug("title " + item.getText());
			result.setId(item.getId());
			result.setTitle(item.getText());
			result.setContents(item.getHtmlcontent());
		} else {
			ret.setFailResponse(404);
		}
		ret.setResult(result);
		return ret;
	}
	
	@DeleteMapping("/doc/{id}")
	public ContentsResponse delete(@PathVariable String id) {
		ContentsResponse ret = new ContentsResponse();
		boolean success = eservice.deleteContent(id);
		return ret;
	}
	
	@PutMapping("/doc")
	public ContentsResponse newDoc(NewContentRequest params) {
		ContentsResponse ret = new ContentsResponse();
		eservice.newFile(params);
		return ret;
	}
	
//	@PutMapping("/doc/")
//	public ContentsResponse newDocSlash(NewContentRequest params, @PathVariable String id) {
//		return newDoc(params);
//	}
	
	@PutMapping("/doc/{id}")
	public ContentsResponse updateDoc(NewContentRequest params, @PathVariable String id) {
		ContentsResponse ret = new ContentsResponse();
		eservice.updateFile(params, id);
		return ret;
	}
	
	// ES7 윈도우즈 참고)
	// https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html
	@GetMapping("/search/{keyword}")
	public SearchResponse search(@PathVariable String keyword) {
		logger.debug("keyword " + keyword);
		String[] keywords = keyword.split(" ");
		logger.debug("keywords " + keywords);
		SearchResponse ret = new SearchResponse();
		List<EsFile> list = eservice.search(keywords);
//		logger.debug("list " + list.toString());
//		for (EsFile item : list) {
//			String summary = item.getSummary();
//			if (summary==null) { continue; }
//			for (String key : keywords) {
//				summary = summary.replaceAll("(?i)" + key, "<em>$0</em>");
//			}
//			item.setSummary(summary);
//			item.setHtmlcontent(null);
//		}
		ret.setResult(list);
		return ret;
	}
	
}
