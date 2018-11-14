package com.jeetprksh.pcconnectserver.controller;

import com.jeetprksh.pcconnectserver.entity.Item;
import com.jeetprksh.pcconnectserver.entity.http.Response;
import com.jeetprksh.pcconnectserver.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.List;

@RestController("/")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<? extends Response> getItems(
            @RequestParam(value = "root", required = false) String root,
            @RequestParam(value = "path", required = false) String path) throws Exception {
        List<Item> items = (root == null) ? itemService.getRootItems() : itemService.getItems(root, path);
        Response res = new Response(true, "List Item", items);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(res);
    }

    @GetMapping("/item/download")
    public ResponseEntity<Resource> downloadItem(
            @RequestParam(value = "root", required = false) String root,
            @RequestParam(value = "path", required = false) String path,
            @RequestParam(value = "download", required = false) String download) throws Exception {
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
