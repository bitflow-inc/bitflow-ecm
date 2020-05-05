package ai.bitflow.ecm.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ai.bitflow.ecm.backend.service.EcmService;
import ai.bitflow.ecm.backend.vo.req.NewContentRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author method76
 */
@Slf4j
@Controller
@RequestMapping("/") 
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private EcmService eservice;
	
	
	@GetMapping("") 
	public String index() {
		return "redirect:doc";
	}
	
	@GetMapping("doc")
	public String doc(Model mo) {
		String treeStr = eservice.getFileTree();
		mo.addAttribute("tree", treeStr);
		return "doc";
	}
	
	@GetMapping("edit")
	public String edit(Model mo) {
		String treeStr = eservice.getFileTree();
		mo.addAttribute("tree", treeStr);
		mo.addAttribute("title", "글 작성");
		return "edit";
	}
	
	/**
	 * 검색
	 * @param mo
	 * @return
	 */
	@GetMapping("search")
	public String search(Model mo) {
		String treeStr = eservice.getFileTree();
		mo.addAttribute("tree", treeStr);
		mo.addAttribute("title", "검색");
		return "search";
	}
	
	/**
	 * 글등록
	 * @param params
	 * @return
	 */
	@PostMapping("doc")
	public String createDoc(NewContentRequest params) {
		String id = eservice.newFile(params);
		return "redirect:/doc#" + id;
	}
	
//	@PostMapping("doc/{parentid}")
//	public String createChildDoc(NewContentRequest params, @PathVariable String parentid) {
//		String id = eservice.newFile(params, parentid);
//		return "redirect:/doc#" + id;
//	}
	
	
}
