package az.project.usermanagementsystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;

public interface FileService {
     void init();
     void save(MultipartFile file);
     Resource load(String filename);
     void deleteAll();
     Stream<Path> loadAll();
}
