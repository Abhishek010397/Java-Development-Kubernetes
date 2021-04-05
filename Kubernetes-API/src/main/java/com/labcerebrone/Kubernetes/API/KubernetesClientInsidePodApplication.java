package com.labcerebrone.Kubernetes.API;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KubernetesClientInsidePodApplication {

    public static void main(String[]args){
        SpringApplication.run(KubernetesClientInsidePodApplication.class, args);
    }
}
