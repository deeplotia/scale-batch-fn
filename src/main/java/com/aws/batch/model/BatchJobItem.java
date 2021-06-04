package com.aws.batch.model;

import lombok.Data;

@Data
public class BatchJobItem {
    private String jobId;
    private String jobName;
    private String jobDefinition;
    private String jobQueue;
    private String jobState;
    private String jobLog;
    private String jobInput;
    private String jobOutput;
    private int jobCpus;
}
