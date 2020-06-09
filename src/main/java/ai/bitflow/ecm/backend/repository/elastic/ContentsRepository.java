package ai.bitflow.ecm.backend.repository.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonArray;

import ai.bitflow.ecm.backend.domain.elastic.EsFile;


@Repository
public interface ContentsRepository extends ElasticsearchRepository<EsFile, String> {

	List<EsFile> findAllByTextContainsOrHtmlcontentContains(List<String> keyword, List<String> keyword2);
//	List<EsFile> findAllByTextOrSummary(String keyword1, String keyword2);
//	List<EsFile> findAllByTextOrSummaryOrAuthor(String keyword1, String keyword2, String keyword3);
//	List<EsFile> findAll();
	
}
