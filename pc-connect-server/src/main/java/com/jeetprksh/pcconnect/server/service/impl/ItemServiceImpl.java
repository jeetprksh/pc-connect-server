package com.jeetprksh.pcconnect.server.service.impl;

import com.jeetprksh.pcconnect.server.entity.Item;
import com.jeetprksh.pcconnect.server.service.ItemService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 * @author Jeet Prakash
 * */
@Component
public class ItemServiceImpl implements ItemService {

  private static Logger logger = Logger.getLogger(ItemServiceImpl.class.getName());
  private Map<String, File> rootMaps = new HashMap<>();

  @Override
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

  @Override
  public List<Item> getItems(String root, String path) throws Exception {
    String requestedPath = createRequestedPath(root, path);
    File file = new File(requestedPath);

    if (!file.exists()) throw new Exception("Invalid path");

    return Arrays.stream(file.listFiles())
        .filter(f -> !f.isHidden())
        .map(f -> new Item(f.getName(),
            f.isDirectory(),
            false,
            root,
            path + "/" + f.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public File downloadItem(String root, String path) throws Exception {
    String requestedPath = createRequestedPath(root, path);
    File file = new File(requestedPath);

    if (!file.exists() || file.isHidden()) throw new Exception("Invalid path.");
    if (!file.isFile()) throw new Exception("Not a file, only a file can be downloaded.");

    return file;
  }

  @Override
  public void initSharedRootDir() {
    getSharedDirectories()
        .forEach(dir -> rootMaps.put(Integer.toString(Objects.hashCode(dir)), new File(dir)));
  }

  // TODO currently relying on hardcoded values
  private List<String> getSharedDirectories() {
    List<String> sharedDir = new ArrayList<>();
    sharedDir.add("C:\\pc-connect");
    return sharedDir;
  }

  private String createRequestedPath(String root, String path) {
    return rootMaps.get(root).getAbsolutePath() + "/" + path;
  }
}
