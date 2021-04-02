package io.fabric8.podset.operator.crd;

public class PodSetSpec {
    private int replicas;

    public int getReplicas() {
        return replicas;
    }

    @Override
    public String toString(){
        return "PodSetSpec{replicas=" + replicas + "}";
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }
}
