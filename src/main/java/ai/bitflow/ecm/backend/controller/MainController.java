package ai.bitflow.ecm.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ai.bitflow.ecm.backend.domain.TbFileTreeMap;
import ai.bitflow.ecm.backend.service.EcmService;
import ai.bitflow.ecm.backend.vo.req.ContentPutRequest;
import ai.bitflow.ecm.backend.vo.res.FileTree;
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
	public ModelAndView doc() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("tree", eservice.getTreeString());
		mv.setViewName("doc");
		return mv;
	}
	
	@RequestMapping("edit")
	public ModelAndView editor() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("tree", eservice.getTreeString());
		mv.addObject("title", "글 작성");
		mv.setViewName("edit");
		return mv;
	}
	
	@PostMapping("put")
	public ModelAndView put(ContentPutRequest params) {
		ModelAndView mv = new ModelAndView();
		TbFileTreeMap file = eservice.saveFile(params);
		mv.setViewName("redirect:doc#" + file.getId());
		return mv;
	}
	
	@GetMapping("doc/{id}")
	public ModelAndView docWithId(@PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		List<TbFileTreeMap> rawtree = eservice.getTree();
		List<FileTree> tree = new ArrayList<>();
		for (TbFileTreeMap item : rawtree) {
			FileTree file = new FileTree();
			file.setId(item.getId());
			file.setText(item.getTitle());
			tree.add(file);
		}
		mv.addObject("tree", new Gson().toJson(tree));
		logger.debug("tree " + new Gson().toJson(tree));
		Optional<TbFileTreeMap> file = eservice.getContents(id);
		mv.setViewName("doc");
		String contents = "";
		if (file.isPresent()) {
			logger.debug("title " + file.get().getTitle());
			mv.addObject("layout.title", file.get().getTitle());
			contents = file.get().getContent();
		}
		mv.addObject("contents", contents);
		return mv;
	}
	
}
