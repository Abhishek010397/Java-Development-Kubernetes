package com.labcerebrone.Kubernetes.API.model;


import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KubernetesService {

    //@Autowired
    public KubernetesClient client = null;

    //Constructor For Service Class
    public KubernetesService(){
        this.client =  new DefaultKubernetesClient();
    }



    //List the Pods Inside the Cluster
    public Map<String, String> getPods(){
        //Store the Response in a Map
        Map<String,String> result = new HashMap<>();
        try{
            //List the Pods in Default Namespace
            PodList podList = client.pods().inNamespace("default").list();
            //Update the result
            result.put("message","There are" + podList.getItems().size() + "pods in the default namespace");
        } catch(KubernetesClientException exception){
            exception.printStackTrace();
            result.put("error", exception.getMessage());
        }
        //Return the result
        finally {
            return result;
        }
    }
}
