package com.jeetprksh.pcconnectserver.controller;

import com.jeetprksh.pcconnectserver.entity.Item;
import com.jeetprksh.pcconnectserver.entity.http.Response;
import com.jeetprksh.pcconnectserver.service.AuthService;
import com.jeetprksh.pcconnectserver.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;

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

    private ItemService itemService;
    private AuthService authService;

    @Autowired
    public ItemController(ItemService itemService, AuthService authService) {
        this.itemService = itemService;
        this.authService = authService;
    }

    @GetMapping("/items")
    public ResponseEntity<? extends Response> getItems(
            @RequestParam(value = "root", required = false) String root,
            @RequestParam(value = "path", required = false) String path,
            @RequestHeader("token") String token) throws Exception {
        String user = authService.verifyToken(token);
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
        String user = authService.verifyToken(token);
        logger.info(user + " accessed the file " + root + "::" + path);
        File file = itemService.downloadItem(root, path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        String contentType = Files.probeContentType(file.toPath());
        if (contentType == null) contentType = "application/octet-stream";

        HttpHeaders headers = new HttpHeaders();
        String downloadType = (download == null) ? "inline" : "attachment";
        headers.add("Content-Disposition", downloadType + "; filename=\"" + file.getName() + "\"");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .contentType(MediaType.asMediaType(MimeType.valueOf(contentType)))
                .body(resource);
    }

}
