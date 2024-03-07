package com.example.osahaneat.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.osahaneat.service.impl.FilesStorageService;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

	@Value("${fileUpload.rootPath}")
	private String rootPath;

	private Path root;

	private void init() {

		try {
			root = Paths.get(rootPath);
			if (Files.notExists(root)) {
				Files.createDirectories(root);
			}
		} catch (IOException e) {
			System.out.println("Error create folder root : " + e.getMessage());

		}

	}

	@Override
	public boolean save(MultipartFile file) {

		try {
			init();
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			return true;
		} catch (Exception e) {
			System.out.println("Error save file : " + e.getMessage());
			return false;
		}

	}

	@Override
	public Resource load(String filename) {
		try {
			init();
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (Exception e) {
			System.out.println("Error load file : " + e.getMessage());
			return null;
		}

	}

}
