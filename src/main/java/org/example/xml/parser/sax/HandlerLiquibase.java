package org.example.xml.parser.sax;

import org.example.xml.parser.sax.entity.ChangeSet;
import org.example.xml.parser.sax.entity.Operation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class HandlerLiquibase extends DefaultHandler {
    private static final String CHANGE_SET = "changeSet";
    private static final String DATA_BASE_CHANGE_LOG = "databaseChangeLog";
    private List<ChangeSet> changeSetList = new LinkedList<>();
    private ChangeSet currentChangeSet;
    private Deque<Operation> operationDeque = new ArrayDeque<>();
    private StringBuilder currentTextContext = new StringBuilder();


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentTextContext.append(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        changeSetList = new LinkedList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals(DATA_BASE_CHANGE_LOG))
            return;
        currentTextContext.setLength(0);

        if (localName.equals(CHANGE_SET)) {
            String id = attributes.getValue("id");
            String author = attributes.getValue("author");
            currentChangeSet = new ChangeSet(id, author);
            return;
        }
        Map<String, String> attr = new HashMap<>();
        for (int i = 0; i < attributes.getLength(); i++)
            attr.put(attributes.getLocalName(i), attributes.getValue(i));

        Operation newOper = new Operation(localName, attr, new LinkedList<>(), null);

        if (operationDeque.isEmpty())
            currentChangeSet.addOperation(newOper);
        else {
            Operation parent = operationDeque.peek();
            parent.addChild(newOper);
        }

        operationDeque.push(newOper);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals(DATA_BASE_CHANGE_LOG))
            return;

        if (localName.equals(CHANGE_SET)) {
            changeSetList.add(currentChangeSet);
            currentChangeSet = null;
            operationDeque.clear();
            currentTextContext.setLength(0);
            return;
        }

        Operation currentOper = operationDeque.pop();
        String text = "";
        if (currentTextContext != null) {
            text = currentTextContext.toString();
            currentTextContext.setLength(0);
        }
        currentOper.setTextContent(text);
    }

    public List<ChangeSet> getChangeSetList() {
        return new ArrayList<>(changeSetList);
    }
}
