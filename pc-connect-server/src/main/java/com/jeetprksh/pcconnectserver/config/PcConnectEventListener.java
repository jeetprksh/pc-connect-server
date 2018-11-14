package com.jeetprksh.pcconnectserver.config;

import com.jeetprksh.pcconnectserver.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class PcConnectEventListener {

    private static Logger logger = Logger.getLogger(PcConnectEventListener.class.getName());

    @Autowired
    ItemService itemService;

    @EventListener(ContextRefreshedEvent.class)
    public void initSharedRootDir() {
        logger.info("Initializing shared root directories.");
        itemService.initSharedRootDir();
    }
}
