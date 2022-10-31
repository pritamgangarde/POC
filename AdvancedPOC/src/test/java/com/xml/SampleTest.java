package com.xml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

public class SampleTest {

	public static void main(String[] args) throws IOException {
		String actual = Files.readString(Path.of("D:\\workspace\\Files\\Student1.xml"));
		String stub = Files.readString(Path.of("D:\\workspace\\Files\\Student2.xml"));

		Diff myDiff = DiffBuilder.compare(actual).withTest(stub).ignoreComments().ignoreWhitespace()
				.ignoreElementContentWhitespace().build();

		Iterator<Difference> iter = myDiff.getDifferences().iterator();
		int size = 0;
		while (iter.hasNext()) {
			Difference difference = iter.next();
			System.err.println(difference.getComparison().getControlDetails().getXPath() + "<-->"
					+ difference.getComparison().getControlDetails().getValue());
			
			System.err.println(difference.getComparison().getTestDetails().getXPath() + "<-->"
					+ difference.getComparison().getTestDetails().getValue());
			System.out.println("------------------------------------------");
			size++;
		}
		System.err.println("-->" + size);

	}

}
