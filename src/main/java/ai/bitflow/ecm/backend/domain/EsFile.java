package ai.bitflow.ecm.backend.domain;

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
	private String title;
	private String text;
	private String author;
	
}
