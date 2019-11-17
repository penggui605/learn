package edu.hubu.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.hubu.learn.service.NovelService;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private NovelService novelService;
	private String novelname;

	@Test
	public void testNovel() {
		String novelname = novelService.getNovel(1l).getNovelname();
		String password = novelService.getNovel(1l).getPassword();
		TestCase.assertEquals(novelname, "root");
		TestCase.assertEquals(password, "1234");
	}

}
