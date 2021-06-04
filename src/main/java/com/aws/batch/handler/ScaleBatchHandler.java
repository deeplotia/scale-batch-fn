package com.aws.batch.handler;

import com.amazonaws.services.batch.AWSBatch;
import com.amazonaws.services.batch.AWSBatchAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.batch.model.BatchJobItem;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ScaleBatchHandler implements RequestHandler<Map<String, Object>, String> {

    private final Logger LOG = Logger.getLogger(this.getClass());



    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: " + input);
        try {
            AWSBatch client = AWSBatchAsyncClientBuilder.standard().build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public Integer queryByState(String state) {
        try {
            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
            DynamoDB dynamoDB = new DynamoDB(client);
            DynamoDBMapper mapper = new DynamoDBMapper(client);

            Table table = dynamoDB.getTable(System.getenv("DYNAMODB_TABLE"));
            Index index = table.getIndex(System.getenv("STATE_INDEX"));

            QuerySpec spec = new QuerySpec()
                    .withKeyConditionExpression("#s = :job_state")
                    .withNameMap(new NameMap()
                            .with("#s", "JobState"))
                    .withValueMap(new ValueMap()
                            .withString(":job_state", state));

            ItemCollection<QueryOutcome> items = index.query(spec);
            Iterator<Item> iter = items.iterator();
            while (iter.hasNext()) {
                BatchJobItem item = mapper.load(BatchJobItem.class, iter.next());
                System.out.println(iter.next().toJSONPretty());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
