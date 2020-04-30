package ai.bitflow.ecm.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

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
		eservice.getFileTree();
		mo.addAttribute("title", "글 작성");
		return "edit";
	}
	
	@GetMapping("search")
	public String search(Model mo) {
		String treeStr = eservice.getFileTree();
		mo.addAttribute("tree", treeStr);
		mo.addAttribute("title", "검색");
		return "search";
	}
	
	@PostMapping("doc")
	public ModelAndView createDoc(NewContentRequest params) {
		ModelAndView mv = new ModelAndView();
		String id = eservice.saveFile(params);
		mv.setViewName("redirect:/doc#" + id);
		return mv;
	}
	
	@PostMapping("/doc/{parentid}")
	public ModelAndView createChildDoc(NewContentRequest params, @PathVariable String parentid) {
		ModelAndView mv = new ModelAndView();
		String id = eservice.saveFile(params, parentid);
		mv.setViewName("redirect:/doc#" + id);
		return mv;
	}
	
	
//	@GetMapping("doc/{id}")
//	public ModelAndView docWithId(@PathVariable int id) {
//		ModelAndView mv = new ModelAndView();
//		List<TbFileTreeMap> rawtree = eservice.getTree();
//		List<FileTree> tree = new ArrayList<>();
//		for (TbFileTreeMap item : rawtree) {
//			FileTree file = new FileTree();
//			file.setId(item.getId());
//			file.setText(item.getTitle());
//			tree.add(file);
//		}
//		mv.addObject("tree", new Gson().toJson(tree));
//		logger.debug("tree " + new Gson().toJson(tree));
//		Optional<TbFileTreeMap> file = eservice.getContents(id);
//		mv.setViewName("doc");
//		String contents = "";
//		if (file.isPresent()) {
//			logger.debug("title " + file.get().getTitle());
//			mv.addObject("layout.title", file.get().getTitle());
//			contents = file.get().getContent();
//		}
//		mv.addObject("contents", contents);
//		return mv;
//	}
	
}
