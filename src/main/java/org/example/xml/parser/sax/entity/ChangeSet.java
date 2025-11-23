package org.example.xml.parser.sax.entity;

import java.util.LinkedList;
import java.util.List;

public class ChangeSet {
    private final String id;
    private final String author;
    private final List<Operation> operationList;

    public ChangeSet(String id, String author, List<Operation> operationList) {
        this.id = id;
        this.author = author;
        this.operationList = new LinkedList<>(operationList);
    }

    public ChangeSet(String id, String author) {
        this(id, author, new LinkedList<>());
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public boolean addOperation(Operation operation) {
        return operationList.add(operation);
    }

    public Operation removeOperation(int i) {
        return operationList.remove(i);
    }
}
