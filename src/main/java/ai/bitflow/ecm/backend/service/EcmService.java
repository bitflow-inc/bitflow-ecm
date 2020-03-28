package ai.bitflow.ecm.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ai.bitflow.ecm.backend.dao.FileDao;
import ai.bitflow.ecm.backend.domain.EsFile;
import ai.bitflow.ecm.backend.domain.TbFileTreeMap;
import ai.bitflow.ecm.backend.repository.EsRepository;
import ai.bitflow.ecm.backend.repository.PsRepository;
import ai.bitflow.ecm.backend.vo.req.ContentPutRequest;
import ai.bitflow.ecm.backend.vo.res.FileTree;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EcmService {

	private final Logger logger = LoggerFactory.getLogger(EcmService.class);
	
	@Autowired
	private FileDao fdao;
	
	@Autowired
	private PsRepository prepo;
	
	@Autowired
	private EsRepository erepo;
	
	/**
	 * title, path, ext, isDir
	 * @param item
	 */
	@Transactional
	public TbFileTreeMap saveFile(ContentPutRequest params) {
		TbFileTreeMap ret = null;
		TbFileTreeMap item = new TbFileTreeMap();
		item.setTitle(params.getTitle());
		item.setContent(params.getContent());
		item.setPath("");
		item.setExt(".html");
		item.setDir(false);
		try {
			ret = prepo.save(item);
			fdao.newFile(ret);
			EsFile efile = new EsFile();
			efile.setId("" + ret.getId());
			efile.setTitle(ret.getTitle());
			String textOnly = Jsoup.parse(params.getContent()).text();
			efile.setText(textOnly);
			efile.setAuthor("method76");
			erepo.save(efile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
		
	}
	
	
	public Optional<TbFileTreeMap> getContents(int id) {
		Optional<TbFileTreeMap> ret = null;
		Optional<EsFile> item = erepo.findById("" + id);
		if (item.isPresent()) {
			logger.debug("id " + item.get().getId());
			ret = prepo.findById(Integer.parseInt(item.get().getId()));
		}
		return ret;
	}
	
	public List<TbFileTreeMap> getTree() {
		return prepo.findAll();
	}
	
	public String getTreeString() {
		List<TbFileTreeMap> rawtree = getTree();
		List<FileTree> tree = new ArrayList<>();
		for (TbFileTreeMap item : rawtree) {
			FileTree file = new FileTree();
			file.setId(item.getId());
			file.setText(item.getTitle());
			tree.add(file);
		}
		return new Gson().toJson(tree);
	}
	
	public List<EsFile> search(String keyword) {
		return erepo.findAllByTitleOrTextOrAuthor(keyword, keyword, keyword);
	}
	
}
