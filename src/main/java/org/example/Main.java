package org.example;

import org.example.xml.parser.jaxb.PrintContextCatalog;
import org.example.xml.parser.jaxb.entity.Catalog;
import org.example.xml.parser.sax.HandlerLiquibase;
import org.example.xml.parser.sax.PrintContextLiquibase;
import org.example.xml.parser.sax.entity.ChangeSet;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final String PATH_CHANGELOG_XML = "src/main/resources/liquebase/university/create-tables.xml";
    private static final String PATH_CATALOG_READ_XML = "src/main/resources/book/book.xml";
    private static final String PATH_CATALOG_WRITE_XML = "src/main/resources/book/book_copy.xml";


    public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException {
        JaxbTest(PATH_CATALOG_READ_XML, PATH_CATALOG_WRITE_XML);
        System.out.println("=".repeat(100));
        SaxTest(PATH_CHANGELOG_XML);
        System.out.println("=".repeat(100));
        DomTest(PATH_CHANGELOG_XML);
    }

    private static void DomTest(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));
        document.normalize();
        System.out.println();
        org.example.xml.parser.dom.PrintContextLiquibase.printContextLiquebase(document, "    ");
    }

    private static void JaxbTest(String readFilename, String writeFilename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Catalog.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Catalog catalog = (Catalog) unmarshaller.unmarshal(new File(readFilename));
        PrintContextCatalog.printCatalog(catalog, "    ");

        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(catalog, new File(writeFilename));
    }

    private static void SaxTest(String filename) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser parser = factory.newSAXParser();
        HandlerLiquibase handler = new HandlerLiquibase();

        parser.parse(new File(filename),
                handler);

        List<ChangeSet> changeSetList = handler.getChangeSetList();

        System.out.println();
        PrintContextLiquibase.printChangeSet(changeSetList, "    ");
    }
}