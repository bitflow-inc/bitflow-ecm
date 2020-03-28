package ai.bitflow.ecm.backend.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import ai.bitflow.ecm.backend.domain.EsFile;

public interface EsRepository extends ElasticsearchRepository<EsFile, String> {

	List<EsFile> findAllByTitleOrTextOrAuthor(String keyword1, String keyword2, String keyword3);
	
}
