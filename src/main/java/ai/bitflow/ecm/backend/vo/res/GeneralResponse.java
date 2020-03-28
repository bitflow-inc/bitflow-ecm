package ai.bitflow.ecm.backend.vo.res;

import lombok.Data;

@Data
public class GeneralResponse {

	private int code;
	private int status = 200;
	private String error;
	private String token;
	
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
