package ai.bitflow.ecm.backend.dao;

import java.util.List;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Component;

import ai.bitflow.ecm.backend.domain.elastic.EsFile;
import ai.bitflow.ecm.backend.domain.elastic.FileTree;
import ai.bitflow.ecm.backend.repository.elastic.FileTreeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DocumentDao {

	private final Logger logger = LoggerFactory.getLogger(DocumentDao.class);
	
	@Autowired
	private FileTreeRepository ftrepo;
	
//	@Autowired
//	private RestHighLevelClient client;
//	
//	@Autowired
//	private ElasticsearchTemplate elasticsearchTemplate;
//	
//	@Autowired 
//	private ElasticsearchOperations operations;
	
	public List<FileTree> findTree() {
		return ftrepo.findAll();
	}
	
	// 참고) https://yhmane.tistory.com/100
	// https://github.com/spring-projects/spring-data-examples/tree/master/elasticsearch/rest
//	public List<EsFile> findAll() {
//		SearchQuery searchQuery = new NativeSearchQueryBuilder()
//			      .withQuery(QueryBuilders.matchAllQuery())
//			      .withIndices("bitflow_ecm")
////			      .withTypes("_doc")
//			      .withFields("id", "text")
//			      .build();
//	    return elasticsearchTemplate.queryForList(searchQuery, EsFile.class);
	    
	    // agrs: 인덱스명, 타입명, 문서명
//	    IndexRequest request = new IndexRequest("bitflow_ecm", "_doc", randomID())
//	    		  .source(singletonMap("feature", "high-level-rest-client"))
//	    		  .setRefreshPolicy(IMMEDIATE);
//	    IndexResponse response = highLevelClient.index(request);
//	    
//		CriteriaQuery query = new CriteriaQuery("keywords").contains("java");
//		List<Conference> result = operations.find(query, Conference.class);
		
//		return null;
//	}
	
//	public boolean newFile(Document item) throws IOException {
//	String fsdir = UPLOAD_ROOT_PATH + ((item.getPath())==null?"":item.getPath());
//	File dir = new File(fsdir);
//	logger.debug("dir " + dir.exists() + " " + fsdir);
//	if (!dir.exists()) {
//		boolean success = dir.mkdirs();
//	}
//	
//	BufferedWriter writer = null;
//	try {
//		writer = new BufferedWriter(new FileWriter(fsdir + File.separator + item.getId() + ".html"));
//		writer.write(item.getContent());
//	    return true;
//	} catch (IOException e) {
//		e.printStackTrace();
//		return false;
//	} finally {
//		if (writer!=null) {
//			try {
//				writer.close();
//			} catch (IOException e) { }
//		}
//	}
//}

//public List<EsFile> getDocuments() {
//    GetRequest request = new GetRequest("bitflow_ecm", "_doc", );
//    GetResponse response = client.get(request, RequestOptions.DEFAULT);
//    Map<String, Object> sourceAsMap = response.getSourceAsMap();
//}
	
}
