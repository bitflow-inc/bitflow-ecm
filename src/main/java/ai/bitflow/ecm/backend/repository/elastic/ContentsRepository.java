package ai.bitflow.ecm.backend.repository.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import ai.bitflow.ecm.backend.domain.elastic.EsFile;

public interface ContentsRepository extends ElasticsearchRepository<EsFile, String> {

	List<EsFile> findAllByTextOrSummary(String keyword1, String keyword2);
	List<EsFile> findAllByTextOrSummaryOrAuthor(String keyword1, String keyword2, String keyword3);
	List<EsFile> findAll();
	
}
