package com.jeetprksh.pcconnect.server.service;

import com.jeetprksh.pcconnect.server.entity.Item;

import java.io.File;
import java.util.List;

/*
 * @author Jeet Prakash
 * */
public interface ItemService {

    List<Item> getRootItems();
    List<Item> getItems(String root, String path) throws Exception;
    File downloadItem(String root, String path) throws Exception;
    void initSharedRootDir();

}
