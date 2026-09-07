package com.solidsudogear.ccsp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solidsudogear.ccsp.Entity.FileMetaData;

@Repository 
public interface FileRepo extends JpaRepository<FileMetaData,Long> {

}
