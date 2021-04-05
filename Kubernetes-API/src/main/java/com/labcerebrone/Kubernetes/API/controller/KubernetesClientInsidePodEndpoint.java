package com.labcerebrone.Kubernetes.API.controller;


import com.labcerebrone.Kubernetes.API.model.KubernetesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class KubernetesClientInsidePodEndpoint {

    @Autowired
    KubernetesService service;

    @RequestMapping(value ="/", produces = "application/json")
    public Map<String, String> getPods(){
        return service.getPods();
    }
}
