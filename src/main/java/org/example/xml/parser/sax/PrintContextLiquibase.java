package org.example.xml.parser.sax;

import org.example.xml.parser.sax.entity.ChangeSet;
import org.example.xml.parser.sax.entity.Operation;

import java.util.List;
import java.util.Map;

public class PrintContextLiquibase {
    public static void printChangeSet(List<ChangeSet> changeSetList, String indent) {
        for (ChangeSet changeSet : changeSetList) {
            System.out.println("ChangeSet: id=\"" + changeSet.getId() +
                    "\", author=\"" + changeSet.getAuthor() + "\"");
            printOperation(changeSet.getOperationList(), 1, indent);
            System.out.println();
        }
    }

    public static void printOperation(List<Operation> operationList, int depth, String indent) {
        String prefix = indent.repeat(depth);
        for (Operation operation : operationList) {
            System.out.print(prefix + operation.getTagName() + ": ");

            Map<String, String> attr = operation.getAttributes();
            int sizeMap = attr.size();
            int currentCount = 0;
            for (Map.Entry<String, String> entry : attr.entrySet()) {
                currentCount += 1;
                System.out.print(entry.getKey() + "=\"" + entry.getValue() + "\"");
                if (currentCount < sizeMap)
                    System.out.print(", ");
            }
            System.out.println();

            List<Operation> childList = operation.getChildren();
            String context = operation.getTextContent().trim();
            if (!context.isBlank())
                System.out.println(prefix + indent + context);
            else
                printOperation(childList, depth + 1, indent);
        }
    }
}
