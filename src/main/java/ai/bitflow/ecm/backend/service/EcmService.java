package ai.bitflow.ecm.backend.service;

import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ai.bitflow.ecm.backend.dao.FileDao;
import ai.bitflow.ecm.backend.domain.elastic.EsFile;
import ai.bitflow.ecm.backend.repository.elastic.ContentsRepository;
import ai.bitflow.ecm.backend.vo.req.ContentPutRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EcmService {

	private final Logger logger = LoggerFactory.getLogger(EcmService.class);
	
	@Autowired
	private FileDao fdao;
	
	@Autowired
	private ContentsRepository erepo;
	
	/**
	 * title, path, ext, isDir
	 * @param item
	 */
	@Transactional
	public String saveFile(ContentPutRequest params) {
//		Document item = new Document();
//		item.setTitle(params.getTitle());
//		item.setContent(params.getContent());
//		item.setDir(false);
//		Document ret = drepo.save(item);
//		fdao.newFile(saved);
		
		EsFile efile = new EsFile();
		efile.setTitle(params.getTitle());
		String textOnly = Jsoup.parse(params.getContent()).text();
		efile.setText(textOnly);
		efile.setHtmlcontent(params.getContent());
//		efile.setAuthor("method76");
		erepo.save(efile);
		return efile.getId();
	}
	
//	public List<TbFileTreeMap> getTree() {
//		return prepo.findAll();
//	}
	
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

//	public String getTreeString() {
//		List<TbFileTreeMap> rawtree = getTree();
//		List<FileTree> tree = new ArrayList<>();
//		for (TbFileTreeMap item : rawtree) {
//			FileTree file = new FileTree();
//			file.setId(item.getId());
//			file.setText(item.getTitle());
//			tree.add(file);
//		}
//		return new Gson().toJson(tree);
//	}
	
	public List<EsFile> search(String keyword) {
		return erepo.findAllByTitleOrTextOrAuthor(keyword, keyword, keyword);
	}
	
}
