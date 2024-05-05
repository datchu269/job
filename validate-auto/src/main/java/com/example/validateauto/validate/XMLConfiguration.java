package com.example.validateauto.validate;

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.InputStream;
import java.util.Hashtable;

public class XMLConfiguration extends EmptyConfiguration {

    public XMLConfiguration(InputStream in) throws Exception {
        this(in, null, null, false);
    }

    public XMLConfiguration(InputStream in, Hashtable<String, Object> attributes, Hashtable<String, Boolean> features) throws Exception {
        this(in, attributes, features, false);
    }

    public XMLConfiguration(String filename) throws Exception {
        this(filename, null, null, false);
    }

    public XMLConfiguration(String filename, Hashtable<String, Object> attributes, Hashtable<String, Boolean> features) throws Exception {
        this(filename, attributes, features, false);
    }

    protected XMLConfiguration(Object in, Hashtable<String, Object> attributes, Hashtable<String, Boolean> features, boolean nop) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        if (attributes != null) {
            for (String key : attributes.keySet()) {
                factory.setAttribute(key, attributes.get(key));
            }
        }
        if (features != null) {
            for (String key : features.keySet()) {
                factory.setFeature(key, features.get(key));
            }
        }
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document;

        if (in instanceof InputStream) {
            document = builder.parse((InputStream) in);
        } else if (in instanceof String) {
            document = builder.parse(new File((String) in));
        } else {
            throw new Exception("Unknown type of input data");
        }
        validate(document);
    }

    protected void validate(Document document) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(getClass().getResourceAsStream("/META-INF/jdiameter-client.xsd"));
        Schema schema = factory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        DOMSource domSource = new DOMSource(document);
        validator.validate(domSource);
    }
}