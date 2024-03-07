package com.example.osahaneat.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

	public boolean save(MultipartFile file);

	public Resource load(String filename);
}
