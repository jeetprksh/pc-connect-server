package com.jeetprksh.pcconnectserver.config;

import com.jeetprksh.pcconnectserver.service.AuthService;
import com.jeetprksh.pcconnectserver.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/*
* @author Jeet Prakash
* */
@Component
public class PcConnectEventListener {

    private static Logger logger = Logger.getLogger(PcConnectEventListener.class.getName());

    private final ItemService itemService;
    private final AuthService authService;

    @Autowired
    public PcConnectEventListener(ItemService itemService, AuthService authService) {
        this.itemService = itemService;
        this.authService = authService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void initSharedRootDir() {
        logger.info("Initializing shared root directories.");
        itemService.initSharedRootDir();
        String code = authService.generateCode();
        logger.info("Access code for Users :: " + code);
    }
}
