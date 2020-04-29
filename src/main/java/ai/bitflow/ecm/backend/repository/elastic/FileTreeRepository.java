package ai.bitflow.ecm.backend.repository.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import ai.bitflow.ecm.backend.domain.elastic.FileTree;

public interface FileTreeRepository extends ElasticsearchRepository<FileTree, String> {

	List<FileTree> findAll();
	
}
