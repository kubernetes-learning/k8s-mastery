#!/usr/bin/env bash

mvn clean install
docker build -t zhanhonglai/sa-webapp .
docker push zhanhonglai/sa-webapp
kubectl delete pods -l app=sa-webapp