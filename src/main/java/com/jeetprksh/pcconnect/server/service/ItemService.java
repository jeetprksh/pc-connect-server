package com.jeetprksh.pcconnect.server.service;

import com.jeetprksh.pcconnect.server.PcConnectServer;
import com.jeetprksh.pcconnect.server.entity.Item;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 * @author Jeet Prakash
 * */
@Component
public class ItemService {

  private final static Logger logger = Logger.getLogger(ItemService.class.getName());

  private Map<String, File> rootMaps = new HashMap<>();

  public List<Item> getRootItems() {
    return rootMaps
        .entrySet().stream()
        .map(e -> new Item(e.getValue().getName(),
            e.getValue().isDirectory(),
            true,
            e.getKey(),
            ""))
        .collect(Collectors.toList());
  }

  public List<Item> getItems(String root, String path) throws Exception {
    String requestedPath = createRequestedPath(root, path);
    File file = new File(requestedPath);

    if (!file.exists()) throw new Exception("Invalid path");
    if (!file.isDirectory()) throw new Exception("Specified path not a directory");

    return Arrays.stream(file.listFiles())
        .filter(f -> !f.isHidden())
        .map(f -> new Item(f.getName(),
            f.isDirectory(),
            false,
            root,
            path + "/" + f.getName()))
        .collect(Collectors.toList());
  }

  public File downloadItem(String root, String path) throws Exception {
    String requestedPath = createRequestedPath(root, path);
    File file = new File(requestedPath);

    if (!file.exists() || file.isHidden()) throw new Exception("Invalid path.");
    if (!file.isFile()) throw new Exception("Not a file, only a file can be downloaded.");

    return file;
  }

  public void initSharedRootDir() {
    PcConnectServer.getSharedDirectories()
        .forEach(dir -> rootMaps.put(Integer.toString(Objects.hashCode(dir)), new File(dir)));
  }

  private String createRequestedPath(String root, String path) {
    return rootMaps.get(root).getAbsolutePath() + "/" + path;
  }
}
