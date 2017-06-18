#!/usr/bin/env bash

echo "==>my test is running"
curl http://localhost:8080/api/mytest

echo "\n\n"
echo "==>my donkey is running"
curl http://localhost:8080/api/donkey

echo "\n\n"
echo "==>my valid json is running"
curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' --data '{"id": "123"}' http://localhost:8080/api/json

echo "==>my invalid json is running"
echo "\n\n"
curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' --data '{"id": "678"}' http://localhost:8080/api/somedudendpoint
