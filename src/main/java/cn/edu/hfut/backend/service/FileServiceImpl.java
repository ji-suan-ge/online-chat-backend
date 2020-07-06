package cn.edu.hfut.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    private final Path fileStorageLocation; // 文件在本地存储的地址

    public FileServiceImpl() {
        String uploadPath = "./upload-files";
        this.fileStorageLocation = Paths.get(uploadPath).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            log.error("创建文件夹失败", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String oldName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            log.error("保存文件失败", ex);
        }
        return null;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            }
        } catch (MalformedURLException ex) {
            log.error("文件不存在", ex);
        }
        return null;
    }
}
