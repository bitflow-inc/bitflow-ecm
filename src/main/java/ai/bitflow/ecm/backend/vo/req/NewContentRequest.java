package ai.bitflow.ecm.backend.vo.req;

import lombok.Data;

@Data
public class NewContentRequest {

	private String id;
	private String text;
	private String content;
	private Boolean directory;
	
}
