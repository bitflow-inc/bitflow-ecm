package ai.bitflow.ecm.backend.vo.res;

import java.util.List;

import ai.bitflow.ecm.backend.domain.elastic.EsFile;
import lombok.Data;

@Data
public class SearchResponse {

	private int code;
	private int status = 200;
	private String error;
	private String token;
	private List<EsFile> result;
	
	public void setSuccessResponse() {
		this.code = 99;
	}
	
	public void setFailResponse() {
		this.code = -1;
	}
	
	public void setFailResponse(String error) {
		this.error = error;
	}

	public void setFailResponse(int status) {
		this.status = status;
	}
	
	public void setFailResponse(int status, String error) {
		this.status = status;
		this.error = error;
	}
	
}
