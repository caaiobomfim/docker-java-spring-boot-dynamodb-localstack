#!/bin/bash

echo "Waiting for LocalStack to start DynamoDB..."

# Loop to wait until DynamoDB is available
until aws dynamodb list-tables --endpoint-url http://localhost:4566 --region us-east-1 > /dev/null 2>&1; do
  echo "Waiting for DynamoDB to be available..."
  sleep 5
done

echo "DynamoDB is available! Creating the table..."

aws dynamodb create-table \
    --table-name Messages \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --billing-mode PAY_PER_REQUEST \
    --endpoint-url http://localhost:4566 \
    --region us-east-1

echo "Table 'Messages' created successfully!"