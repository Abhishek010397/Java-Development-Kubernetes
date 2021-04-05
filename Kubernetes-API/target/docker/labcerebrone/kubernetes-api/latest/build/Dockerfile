FROM quay.io/jkube/jkube-java-binary-s2i:0.0.5
ENV JAVA_APP_DIR=/deployments
LABEL org.label-schema.description="Accessing API From Within Pod" org.label-schema.version=0.0.1-SNAPSHOT org.label-schema.schema-version=1.0 org.label-schema.build-date=2021-04-06T03:08:34.421864 org.label-schema.name=Kubernetes-API
EXPOSE 8080 8778 9779
COPY deployments /deployments/
