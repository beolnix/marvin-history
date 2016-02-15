#!/usr/bin/bash
java -Djava.security.egd=file:/dev/./urandom -Dapi.key=$API_KEY -Dapi.auth=$API_AUTH -jar /app.jar