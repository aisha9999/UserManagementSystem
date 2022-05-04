package az.project.usermanagementsystem.controller;

import az.project.usermanagementsystem.dto.response.ResponseMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FileExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("One or more files are too large!"));
    }
}
