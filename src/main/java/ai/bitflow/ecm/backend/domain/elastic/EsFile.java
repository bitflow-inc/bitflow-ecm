package ai.bitflow.ecm.backend.domain.elastic;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Document(indexName = "bitflow_ecm")
public class EsFile {
	
	@Id
	private String id;
	private String text;
//	private String summary;
	private String htmlcontent;
	private String author;
	
}
