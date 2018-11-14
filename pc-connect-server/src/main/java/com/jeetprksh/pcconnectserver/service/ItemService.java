package com.jeetprksh.pcconnectserver.service;

import com.jeetprksh.pcconnectserver.entity.Item;

import java.io.File;
import java.util.List;

public interface ItemService {

    List<Item> getRootItems();
    List<Item> getItems(String root, String path) throws Exception;
    File downloadItem(String root, String path) throws Exception;
    void initSharedRootDir();

}
