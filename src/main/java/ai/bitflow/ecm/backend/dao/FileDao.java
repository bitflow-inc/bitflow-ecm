package ai.bitflow.ecm.backend.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ai.bitflow.ecm.backend.domain.TbFileTreeMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileDao {

	private final Logger logger = LoggerFactory.getLogger(FileDao.class);
	
	@Value("${app.upload.root.path}")
	private String UPLOAD_ROOT_PATH;
	
	public boolean newFile(TbFileTreeMap result) throws IOException {
		String fsdir = UPLOAD_ROOT_PATH + ((result.getPath())==null?"":result.getPath());
		File dir = new File(fsdir);
		logger.debug("dir " + dir.exists() + " " + fsdir);
		if (!dir.exists()) {
			boolean success = dir.mkdirs();
		}
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fsdir + File.separator + result.getId() + ".html"));
			writer.write(result.getContent());
		    return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (writer!=null) {
				try {
					writer.close();
				} catch (IOException e) { }
			}
		}
	}
	
}
