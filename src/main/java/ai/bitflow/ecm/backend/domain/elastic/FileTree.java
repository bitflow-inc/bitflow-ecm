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
	private String text;
	private List<FileTree> nodes;
	private Boolean directory;
	private State state = new State();
	
	@Data
	public class State {
		private Boolean expanded = false; 
		private Boolean selected = false; 
	}
	
}
