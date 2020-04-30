package ai.bitflow.ecm.backend.dao;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import ai.bitflow.ecm.backend.domain.elastic.EsFile;
import ai.bitflow.ecm.backend.domain.elastic.FileTree;
import ai.bitflow.ecm.backend.repository.elastic.FileTreeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ElasticDao {

	private final Logger logger = LoggerFactory.getLogger(ElasticDao.class);
	
	@Autowired
	private FileTreeRepository ftrepo;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	public List<EsFile> findAll() {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
			      .withQuery(QueryBuilders.matchAllQuery())
			      .withIndices("bitflow_ecm")
//			      .withTypes("_doc")
			      .withFields("id", "text")
			      .build();
	    return elasticsearchTemplate.queryForList(searchQuery, EsFile.class);
	}
	
	public List<FileTree> findTree() {
		return ftrepo.findAll();
	}
	
}
