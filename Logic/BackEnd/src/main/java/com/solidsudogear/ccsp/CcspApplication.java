package com.solidsudogear.ccsp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.solidsudogear.ccsp.Entity.FileMetaData;
import com.solidsudogear.ccsp.Repositories.FileRepo;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor 
public class CcspApplication implements CommandLineRunner{

	FileRepo fileRepo;

	public static void main(String[] args) {
		SpringApplication.run(CcspApplication.class, args);
	}


	// just a mock data in the DB
	@Override
	public void run(String... args) throws Exception {
		FileMetaData r1 = new FileMetaData();
		r1.setUserID(1L);
		r1.setFileName("report.pdf");
		r1.setContentType("application/pdf");
		r1.setSize("248192");
		r1.setFilePath("uploads/7b3e1c0a-report.pdf");
		r1.setUploadedDate("2026-11-19");
		r1.setModifiedDate("2026-11-19");
		r1.setDescription("Q3 architecture review");

		FileMetaData r2 = new FileMetaData();
		r2.setUserID(1L);
		r2.setFileName("avatar.png");
		r2.setContentType("image/png");
		r2.setSize("84211");
		r2.setFilePath("uploads/c41d9f22-avatar.png");
		r2.setUploadedDate("2026-11-18");
		r2.setModifiedDate("2026-11-18");
		r2.setDescription("Profile picture");

		FileMetaData r3 = new FileMetaData();
		r3.setUserID(2L);
		r3.setFileName("usage.csv");
		r3.setContentType("text/csv");
		r3.setSize("15420");
		r3.setFilePath("uploads/9a0e77b4-usage.csv");
		r3.setUploadedDate("2026-11-17");
		r3.setModifiedDate("2026-11-17");
		r3.setDescription("November usage export");

		FileMetaData r4 = new FileMetaData();
		r4.setUserID(3L);
		r4.setFileName("contract.docx");
		r4.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		r4.setSize("512004");
		r4.setFilePath("uploads/2f8c5d61-contract.docx");
		r4.setUploadedDate("2026-11-16");
		r4.setModifiedDate("2026-11-16");
		r4.setDescription("Signed vendor contract");

		FileMetaData r5 = new FileMetaData();
		r5.setUserID(2L);
		r5.setFileName("notes.txt");
		r5.setContentType("text/plain");
		r5.setSize("32768");
		r5.setFilePath("uploads/e6b1a903-notes.txt");
		r5.setUploadedDate("2026-11-15");
		r5.setModifiedDate("2026-11-15");
		r5.setDescription("Migration notes");

		FileMetaData[] fileMetaDatas = new FileMetaData[]{ r1, r2, r3, r4, r5 };

		for (FileMetaData fileMetaData : fileMetaDatas) {
			fileRepo.save(fileMetaData);

		}
	}

}
