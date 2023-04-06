package org.example.microservices.models.listmodels;

import lombok.Data;

import java.util.List;

@Data
public class FolderResponseList {
    private List<FolderResponse> lists;
}
