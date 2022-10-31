package com.xml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

@SpringBootTest
class AdvancedPocApplicationTests {


	@Test
	void contextLoads() throws IOException {
		String actual = Files.readString(Path.of("D:\\workspace\\Files\\Student1.xml"));
		String stub = Files.readString(Path.of("D:\\workspace\\Files\\Student2.xml"));

		Diff myDiff = DiffBuilder.compare(actual).withTest(stub).ignoreComments().ignoreWhitespace()
				.ignoreElementContentWhitespace().build();

		Iterator<Difference> iter = myDiff.getDifferences().iterator();
		int size = 0;
		while (iter.hasNext()) {
			Difference difference = iter.next();
			System.err.println(difference.getResult());
			System.err.println(difference.getResult());
			size++;
		}
		System.err.println("-->" + size);
	}


}
