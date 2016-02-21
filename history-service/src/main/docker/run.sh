#!/usr/bin/env bash
java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod -Dspring.config.location="file:/application.yml" -jar /app.jar