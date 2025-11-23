package org.example.xml.parser.sax.entity;

import java.util.*;

public class Operation {
    private String tagName;
    private Map<String, String> attributes;
    private List<Operation> children;
    private String textContent;

    public Operation(String tagName, Map<String, String> attribute,
                     List<Operation> children, String textContent) {
        this.tagName = tagName;
        this.attributes = new HashMap<>(attribute);
        this.children = new LinkedList<>(children);
        this.textContent = textContent;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public boolean addChild(Operation operation) {
        return children.add(operation);
    }

    public Operation removeChild(int i) {
        return children.remove(i);
    }

    public List<Operation> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }
}
