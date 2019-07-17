#!/usr/bin/env bash

docker build -t zhanhonglai/sa-logic .
docker push zhanhonglai/sa-logic
kubectl delete pods -l app=sa-logic