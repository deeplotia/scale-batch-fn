package com.aws.batch.model;


import lombok.Data;

@Data
public class BatchCloudWatchEvent {
    private String JobId;
    private String JobName;
    private String JobQueue;
    private String Status;
    private Container Container;
}
