# Kuberenetes-Operator-in-Java

Kubernetes Operators are software extensions to Kubernetes that make use of Custom Resources to manage applications and their components. They let you extend the cluster’s behavior without modifying the code of Kubernetes itself. They are just clients of the Kubernetes API, which act as controllers of that Custom Resource.

In simple terms, a Kubernetes Operator is code that makes use of the Kubernetes API to execute some tasks; Custom Resources act as a configuration model on which that specific code acts.

I have Demonstarted the use of Operators in Kubernetes

Thereby to run hte project following components were used:-

1. Java 14 Version(OpenJDK)
2. mvn 3.x
3. Minikube was used
4. Intellij IDE

Here i have made use of a demo operator which implements a simple operator for a custom resource called PodSet which is somewhat equal to ReplicaSet.Each PodSet object would have 'x' number of replicas, so this operator just tries to maintain x number of replicas checking whether that number of pods are running in cluster or not.

To run the Project:-

1. kubectl apply -f crd.yaml
2. kubectl apply -f podset.yaml
3. mvn clean install
4. mvn exec:java -Dexec.mainClass=io.fabric8.podset.operator.PodSetOperatorMain
