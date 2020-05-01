package ai.bitflow.ecm.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.google.gson.Gson;

import ai.bitflow.ecm.backend.dao.ElasticDao;
import ai.bitflow.ecm.backend.dao.FileDao;
import ai.bitflow.ecm.backend.domain.elastic.EsFile;
import ai.bitflow.ecm.backend.domain.elastic.FileTree;
import ai.bitflow.ecm.backend.repository.elastic.ContentsRepository;
import ai.bitflow.ecm.backend.repository.elastic.FileTreeRepository;
import ai.bitflow.ecm.backend.util.Base62Util;
import ai.bitflow.ecm.backend.vo.req.NewContentRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EcmService {

	private final Logger logger = LoggerFactory.getLogger(EcmService.class);
	
	@Autowired
	private ContentsRepository erepo;
	
	@Autowired
	private FileTreeRepository ftrepo;

	@Autowired
	private ElasticDao edao;
	
	@Transactional
	public FileTree saveDirectory(NewContentRequest params) {
		FileTree item1 = new FileTree();
		if (params.getId()!=null) {
			item1.setId(params.getId());
		} else {
			item1.setId(Base62Util.generate20RandomStr());
		}
		item1.setText(params.getText());
		item1.setDirectory(params.getDirectory());
		return ftrepo.save(item1);
	}
	
	@Transactional
	public boolean saveDirectory(NewContentRequest params, String parentid) {
		Optional<FileTree> row = ftrepo.findById(parentid);
		if (row.isPresent()) {
//			logger.debug("saveDirectory found");
			FileTree item1 = row.get();
			List<FileTree> nodes = item1.getNodes();
			if (nodes==null) {
				nodes = new ArrayList<>();
			}
			FileTree item2 = new FileTree();
			if (params.getId()!=null) {
				item2.setId(params.getId());
			} else {
				item2.setId(Base62Util.generate20RandomStr());
			}
			item2.setText(params.getText());
			item2.setDirectory(params.getDirectory());
			nodes.add(item2);
			item1.setNodes(nodes);
			ftrepo.save(item1);
			return true;
		} else {
//			logger.debug("saveDirectory not found");
			List<FileTree> list = ftrepo.findAll();
			for (FileTree item : list) {
				boolean found = saveDirectoryRecursive(params, parentid, item);
				if (found) { 
					ftrepo.save(item);
					return true; 
				} else {
					continue;
				}
			}
			return false;
		}
	}
	
	/**
	 * 
	 * @param params
	 * @param parentid
	 * @param item1
	 * @return
	 */
	public boolean saveDirectoryRecursive(NewContentRequest params, String parentid, FileTree item1) {
		
//		logger.debug("saveDirectoryRecursive parentid " + parentid + " nodeid " + item1.getId());
		if (parentid.equals(item1.getId())) {
//			logger.debug("saveDirectoryRecursive found");
			List<FileTree> nodes = item1.getNodes();
			if (nodes==null) {
				nodes = new ArrayList<>();
			}
			// 부모 ID를 찾았으면 새 Tree 객체 생성
			FileTree item2 = new FileTree();
			if (params.getId()!=null) {
				item2.setId(params.getId());
			} else {
				item2.setId(Base62Util.generate20RandomStr());
			}
			item2.setText(params.getText());
			item2.setDirectory(params.getDirectory());
			nodes.add(item2);
			item1.setNodes(nodes);
//			logger.debug("item " + item1.toString());
			return true;
		} else {
//			logger.debug("saveDirectoryRecursive not found");
			if (item1.getNodes()!=null) {
				for (FileTree item3 : item1.getNodes()) {
					boolean found = saveDirectoryRecursive(params, parentid, item3);
					if (found) {
						return true;
					} else {
						continue;
					}
				}
			}
			return false;
		}
		
	}
	
	/**
	 * title, path, ext, isDir
	 * @param item
	 */
	@Transactional
	public String saveFile(NewContentRequest params) {
		params.setDirectory(false);
		EsFile item1 = new EsFile();
		item1.setText(params.getText());
		String textOnly = Jsoup.parse(params.getContent()).text();
		item1.setSummary(textOnly);
		item1.setHtmlcontent(params.getContent());
		EsFile item2 = erepo.save(item1);
//		logger.debug("id1 " + item1.getId() + " id2 " + item2.getId());
		params.setId(item2.getId());
		saveDirectory(params);
		return item2.getId();
	}
	
	@Transactional
	public String saveFile(NewContentRequest params, String parentid) {
//		logger.debug("saveFile " + params.toString());
		params.setDirectory(false);
		EsFile item1 = new EsFile();
		item1.setText(params.getText());
		item1.setHtmlcontent(params.getContent());
		String textOnly = Jsoup.parse(params.getContent()).text();
		item1.setSummary(textOnly);
		EsFile item2 = erepo.save(item1);
		params.setId(item2.getId());
		saveDirectory(params, parentid);
		return item2.getId();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public EsFile getContents(String id) {
		Optional<EsFile> row = erepo.findById(id);
		return row.isPresent()?row.get():null;
	}
	
	@Transactional
	public boolean deleteContent(String id) {
		Optional<EsFile> row = erepo.findById(id);
		if (row.isPresent()) {
			EsFile item = row.get();
			erepo.delete(item);
		}
		return true;
	}

	public List<EsFile> search(String keyword) {
		return erepo.findAllByTextOrSummary(keyword, keyword);
	}
	
	public String getFileTree() {
		List<FileTree> list = edao.findTree();
		logger.debug("list" + list.toString());
		return new Gson().toJson(list);
	}
}
