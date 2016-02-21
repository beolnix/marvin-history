FROM java:8
VOLUME /application.yml
ADD history-service-0.1.0.jar app.jar
ADD run.sh run.sh
ENV API_KEY api_key
ENV API_AUTH api_auth
ENTRYPOINT ["bash", "/run.sh"]