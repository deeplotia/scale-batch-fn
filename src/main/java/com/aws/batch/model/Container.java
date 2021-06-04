package com.aws.batch.model;

import lombok.Data;

@Data
public class Container {
    private String LogStreamName;
    private int VCpus;
}
