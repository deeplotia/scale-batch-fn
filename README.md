# Scale Batch Function

This function will be responsible for scaling the AWS Batch inorder to build a consistent behaviour in AWS Batch Auto Scaling.

## Prerequisites
- Java 8 & above
- serverless framework
- AWS creds
- A deployed AWS Batch Environment

## Deploying
To deploy, gradle build then gather up your batch environment details and export them as environment variables. Then run `sls deploy`
```
./gradlew clean build
export BATCH_LOG_GROUP=/aws/batch/logs
export COMPUTE_ENVIRONMENT=aws-batch-demo-env
export JOB_QUEUE=aws-batch-demo-queue
export JOB_DEFINITION=aws-batch-demo-def
sls deploy
```

