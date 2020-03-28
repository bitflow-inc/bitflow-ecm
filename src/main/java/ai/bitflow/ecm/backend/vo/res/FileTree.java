package ai.bitflow.ecm.backend.vo.res;

import java.util.List;

import lombok.Data;

@Data
public class FileTree {

	private int id;
	private String text;
	private List<FileTree> nodes;
	
}
