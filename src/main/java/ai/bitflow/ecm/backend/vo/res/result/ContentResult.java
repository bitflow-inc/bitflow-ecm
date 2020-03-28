package ai.bitflow.ecm.backend.vo.res.result;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContentResult {
	private int id;
	private String title;
	private String contents;
}
