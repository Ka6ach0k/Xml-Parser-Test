package org.example.xml.parser.dom;

import org.w3c.dom.*;

public class PrintContextLiquibase {

    public static void printContextLiquebase(Document document, String indent) {
        Element root = document.getDocumentElement();
        NodeList changeSets = root.getChildNodes();


        for (int i = 0; i < changeSets.getLength(); i++) {

            Node node = changeSets.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;

            Element changeSet = (Element) node;
            String id = changeSet.getAttribute("id");
            String author = changeSet.getAttribute("author");
            System.out.println("ChangeSet: id=\"" + id + "\", author=\"" + author + "\"");

            printChildElement(changeSet, 1, indent);

            System.out.println();
        }
    }

    private static void printChildElement(Element parent, int depth, String indent) {
        String prefix = indent.repeat(depth);
        NodeList children = parent.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;

            Element child = (Element) node;
            System.out.print(prefix + child.getTagName() + ": ");

            NamedNodeMap attrs = child.getAttributes();
            for (int j = 0; j < attrs.getLength(); j++) {
                Node attr = attrs.item(j);
                System.out.print(attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
                if (j < attrs.getLength() - 1) {
                    System.out.print(", ");
                }
            }

            boolean hasChildElements = false;
            NodeList grandChildren = child.getChildNodes();
            for (int k = 0; k < grandChildren.getLength(); k++) {
                if (grandChildren.item(k).getNodeType() == Node.ELEMENT_NODE) {
                    hasChildElements = true;
                    break;
                }
            }

            String textContent = child.getTextContent();
            boolean verifyTextContent = !(textContent != null && textContent.trim().isEmpty());

            if (hasChildElements) {
                System.out.println();
                printChildElement(child, depth + 1, indent);
            } else if (verifyTextContent) {
                System.out.println();
                System.out.println(prefix + indent + textContent.trim());
            } else
                System.out.println();
        }
    }
}