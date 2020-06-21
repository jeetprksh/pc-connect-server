package com.jeetprksh.pcconnect.server.controller;

import com.jeetprksh.pcconnect.server.entity.Item;
import com.jeetprksh.pcconnect.server.entity.http.Response;
import com.jeetprksh.pcconnect.server.service.AuthService;
import com.jeetprksh.pcconnect.server.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
@CrossOrigin
@RestController()
public class ItemController {

  private final Logger logger = Logger.getLogger(ItemController.class.getName());

  @Autowired private ItemService itemService;
  @Autowired private AuthService authService;

  @GetMapping("/items")
  public ResponseEntity<? extends Response> getItems(
      @RequestParam(value = "root", required = false) String root,
      @RequestParam(value = "path", required = false) String path,
      @RequestHeader("token") String token) throws Exception {
    String user = authService.verifyToken(token).getName();
    logger.info(user + " accessed the path " + root + "::" + path);

    List<Item> items = (root == null) ? itemService.getRootItems() : itemService.getItems(root, path);

    Response response = new Response(true, "List Items", items);
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
  }

  @GetMapping("/item/download")
  public ResponseEntity<Resource> downloadItem(
      @RequestParam(value = "root", required = false) String root,
      @RequestParam(value = "path", required = false) String path,
      @RequestParam(value = "download", required = false) String download,
      @RequestHeader("token") String token) throws Exception {
    String user = authService.verifyToken(token).getName();
    logger.info(user + " accessed the file " + root + "::" + path);

    File file = itemService.downloadItem(root, path);
    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    String contentType = Files.probeContentType(file.toPath());
    if (contentType == null) contentType = "application/octet-stream";

    HttpHeaders headers = new HttpHeaders();
    String downloadType = (download == null) ? "inline" : "attachment";
    headers.add(HttpHeaders.CONTENT_DISPOSITION, downloadType + "; filename=\"" + file.getName() + "\"");
    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.asMediaType(MimeType.valueOf(contentType)))
            .body(resource);
  }

}
