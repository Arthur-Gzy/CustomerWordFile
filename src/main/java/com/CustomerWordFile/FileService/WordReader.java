package com.CustomerWordFile.FileService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.CustomerWordFile.Dto.CustomerDto;

public class WordReader {
	
	public static void updateWordDoc(CustomerDto customerDto) {
		try{
			String source = "c:\\CustomerFiles\\CustomerTotalLoanSchema.docx";
			String target = "c:\\CustomerFiles\\CustomerTotalLoan.docx";
			XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(source)));
			List<XWPFParagraph> paragraphList = doc.getParagraphs();
			for(XWPFParagraph paragraph: paragraphList) {
				List<XWPFRun> runs = paragraph.getRuns();
				if(runs != null) {
					for(XWPFRun run: runs) {
						String text = run.getText(0);
						if(text != null && text.contains("${firstName}")) {
							text = text.replace("${firstName}", customerDto.getFirstName());
							run.setText(text, 0);
							run.setBold(false);
							System.out.println(run);
						}
						else if(text != null && text.contains("${secondName}")) {
							text = text.replace("${secondName}", customerDto.getSecondName());
							run.setText(text, 0);
							run.setBold(false);
							System.out.println(run);
						}
						else if(text != null && text.contains("${loan}")) {
							text = text.replace("${loan}", customerDto.getLoan().toString());
							run.setText(text, 0);
							run.setBold(false);
							System.out.println(run);
						}
					}
				}
			}
			FileOutputStream outputStream = new FileOutputStream(target);
			doc.write(outputStream);
		}
		catch(IOException ex ) {
			ex.printStackTrace();
		}
	}
}
