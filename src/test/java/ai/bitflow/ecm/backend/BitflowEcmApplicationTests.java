package ai.bitflow.ecm.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ai.bitflow.ecm.backend.dao.ElasticDao;

@SpringBootTest
class BitflowEcmApplicationTests {

	@Autowired
	private ElasticDao edao;
	
	@Test
	void contextLoads() {
//		edao.find();
	}

}
