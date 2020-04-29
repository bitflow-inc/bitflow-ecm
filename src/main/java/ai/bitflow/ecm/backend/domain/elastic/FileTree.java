package ai.bitflow.ecm.backend.domain.elastic;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Document(indexName = "bitflow_tree")
public class FileTree {
	
	@Id
	private String id;
	private String title;
	private List<FileTree> child;
	
}
