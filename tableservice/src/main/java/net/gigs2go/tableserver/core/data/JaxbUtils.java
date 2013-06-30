/**
 * 
 */
package net.gigs2go.tableserver.core.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * @author tim
 *
 */
@Service("jaxbUtils")
public class JaxbUtils {
    private Logger log = LoggerFactory.getLogger(JaxbUtils.class);
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 
     */
    public JaxbUtils() {
        // TODO Auto-generated constructor stub
    }

    public Object getObject(InputStream is, SchemaType schemaType) throws TransferException {
        Object result = null;
        JAXBContext jaxbContext;
        javax.xml.validation.Schema jaxbSchema;
        try {
            jaxbContext = JAXBContext.newInstance(schemaType.getTargetClass());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Resource resource = applicationContext.getResource("classpath:schemas/" + schemaType.toString() + ".xsd");
            jaxbSchema = factory.newSchema(resource.getFile());
            //log.debug("Schema is {}", jaxbSchema);
            jaxbUnmarshaller.setSchema(jaxbSchema);
            //log.debug("Available is {}", Integer.valueOf(is.available()).toString());
            result = jaxbUnmarshaller.unmarshal(is);
        } catch (Exception e) {
            log.error("Got Exception : {}", e.toString());
            net.gigs2go.tableserver.core.data.Result error = new net.gigs2go.tableserver.core.data.Result();
            error.setSuccess(net.gigs2go.tableserver.core.data.Result.FAIL);
            error.getMessages().add(e.toString());
            throw new TransferException(error);
        }
        log.debug("Got Object {}", (result != null ? result.toString() : "null"));
        return result;
    }

    // Given the object, create a skeleton xsd for it
    public void createXSD(Object o, final File file) {
        JAXBContext jaxbContext;
        javax.xml.validation.Schema jaxbSchema;
        try {
            jaxbContext = JAXBContext.newInstance(o.getClass());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbSchema = jaxbUnmarshaller.getSchema();
            log.debug("Schema is {}", jaxbSchema);
            SchemaOutputResolver outputResolver = new SchemaOutputResolver() {
                @Override
                public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {

                    // create stream result
                    StreamResult result = new StreamResult(file);

                    // set system id
                    result.setSystemId(file.toURI().toURL().toString());

                    // return result
                    return result;
                }
            };

            jaxbContext.generateSchema(outputResolver);
        } catch (JAXBException e) {
            log.debug("Got exception : {}", e);
        } catch (IOException e) {
            log.debug("Got exception : {}", e);
        }

    }
}
