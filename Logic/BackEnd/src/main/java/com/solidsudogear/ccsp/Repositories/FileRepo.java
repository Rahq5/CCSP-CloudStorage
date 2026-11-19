package com.solidsudogear.ccsp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.solidsudogear.ccsp.Entity.FileMetaData;


public interface FileRepo extends JpaRepository<FileMetaData,Long> {

}
