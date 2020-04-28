package ai.bitflow.ecm.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ai.bitflow.ecm.backend.dao.ElasticDao;
import ai.bitflow.ecm.backend.domain.elastic.EsFile;
import ai.bitflow.ecm.backend.service.EcmService;
import ai.bitflow.ecm.backend.vo.req.ContentPutRequest;
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
	
	@Autowired
	private ElasticDao edao;
	
	
	@GetMapping("") 
	public String index() {
		return "redirect:doc";
	}
	
	@GetMapping("doc")
	public String doc(Model mo) {
//		mv.addObject("tree", eservice.getTreeString());
		List<EsFile> list = edao.findAll();
		for (EsFile item : list) {
			item.setText(item.getTitle());
		}
		logger.debug("list" + list.toString());
		mo.addAttribute("tree", new Gson().toJson(list));
		return "doc";
	}
	
	@RequestMapping("edit")
	public ModelAndView editor() {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("tree", eservice.getTreeString());
		mv.addObject("title", "글 작성");
		mv.setViewName("edit");
		return mv;
	}
	
	@PostMapping("put")
	public ModelAndView put(ContentPutRequest params) {
		ModelAndView mv = new ModelAndView();
		String id = eservice.saveFile(params);
		mv.setViewName("redirect:doc#" + id);
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
