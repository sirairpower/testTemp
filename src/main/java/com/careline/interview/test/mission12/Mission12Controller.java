package com.careline.interview.test.mission12;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mission12")
@Slf4j
public class Mission12Controller {

  @Value("classpath:test.pdf")
  private Resource resource;

  @GetMapping("/readPDF")
  public ResponseEntity<byte[]> readPDF() {
    log.info("start readPDF ~~");
    ResponseEntity<byte[]> response = null;
    try (
        InputStream resourceInputStream = resource.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();) {
      int nRead;
      byte[] data = new byte[4];
      while ((nRead = resourceInputStream.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, nRead);
      }
      buffer.flush();
      byte[] targetArray = buffer.toByteArray();

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_PDF);
      headers.setContentDispositionFormData("ReNameFile.pdf", "ReNameFile.pdf");
      headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
      response = new ResponseEntity<>(targetArray, headers, HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      response = new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }
    return response;
  }
}
