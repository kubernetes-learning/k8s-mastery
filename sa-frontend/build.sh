#!/usr/bin/env bash

npm run build
docker build -t zhanhonglai/sa-frontend .
docker push zhanhonglai/sa-frontend
kubectl delete pods -l app=sa-frontend