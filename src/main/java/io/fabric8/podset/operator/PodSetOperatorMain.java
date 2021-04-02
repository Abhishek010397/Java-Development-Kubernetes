package io.fabric8.podset.operator;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinition;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import io.fabric8.kubernetes.client.informers.SharedInformerFactory;
import io.fabric8.podset.operator.controller.PodSetController;
import io.fabric8.podset.operator.crd.PodSet;
import io.fabric8.podset.operator.crd.PodSetList;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PodSetOperatorMain {
    public static final Logger logger = Logger.getLogger(PodSetOperatorMain.class.getName());
    public static void main(String[] args){
        //This would make a call to the PodSet Controller
        //this is the driver class
        //This will read Kubernetes Config
        try(KubernetesClient client = new DefaultKubernetesClient()){
            String namespace = client.getNamespace();
            if (namespace == null) {
                logger.log(Level.INFO, "No namespace found via config, assuming default.");
                namespace = "default";
            }
            logger.log(Level.INFO, "Using namespace : " + namespace);
            //Instantiate ShareInformer
            SharedInformerFactory informerfactory = client.informers();

            MixedOperation<PodSet, PodSetList, Resource<PodSet>> podSetClient = client.customResources(PodSet.class, PodSetList.class);
            //Subscribe Notifications Related to pod
            SharedIndexInformer<Pod> podSharedIndexInformer = informerfactory.sharedIndexInformerFor(Pod.class,10*60*1000);

            //Notifications for PodSets
            SharedIndexInformer<PodSet> podSetSharedIndexInformer = informerfactory
                    .sharedIndexInformerForCustomResource(PodSet.class,10*60*1000);

            PodSetController podSetController = new PodSetController(client,podSetClient,podSharedIndexInformer,podSetSharedIndexInformer,namespace);

            podSetController.create();
            informerfactory.startAllRegisteredInformers();
            informerfactory.addSharedInformerEventListener(exception -> logger.log(Level.SEVERE, "Exception occurred, but caught", exception));
            podSetController.run();
        }catch(KubernetesClientException exception){
            logger.log(Level.SEVERE, "Kubernetes Client Exception : " + exception.getMessage());
        }
    }
}
