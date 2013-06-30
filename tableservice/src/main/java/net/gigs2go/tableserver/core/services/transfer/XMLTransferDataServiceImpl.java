/**
 * 
 */
package net.gigs2go.tableserver.core.services.transfer;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gigs2go.tableserver.core.data.Data;
import net.gigs2go.tableserver.core.data.JaxbUtils;
import net.gigs2go.tableserver.core.data.Schema;
import net.gigs2go.tableserver.core.data.SchemaType;
import net.gigs2go.tableserver.core.data.TransferDataService;
import net.gigs2go.tableserver.core.data.TransferException;

/**
 * @author tim
 * 
 */
@Service("xmlFileService")
public class XMLTransferDataServiceImpl implements TransferDataService {
    private Logger log = LoggerFactory.getLogger(XMLTransferDataServiceImpl.class);

    @Autowired
    private JaxbUtils jaxbUtils;

    public XMLTransferDataServiceImpl() {
    }

    public Schema getSchema(InputStream is) throws TransferException {
        Schema result = null;
        result = (Schema) jaxbUtils.getObject(is, SchemaType.SCHEMA);
        return result;
    }

    //    private net.gigs2go.tableserver.core.data.Schema mapSchema(Schema xmlSchema) {
    //        net.gigs2go.tableserver.core.data.Schema result = new net.gigs2go.tableserver.core.data.Schema();
    //        return result;
    //    }

    public Data getData(InputStream is) throws TransferException {
        Data result = null;
        result = (Data) jaxbUtils.getObject(is, SchemaType.DATA);
        return result;
    }

    //    private net.gigs2go.tableserver.core.data.Data mapData(Data data) {
    //        net.gigs2go.tableserver.core.data.Data result = new net.gigs2go.tableserver.core.data.Data();
    //
    //        return result;
    //    }

    //    public Result save(Dataset dataset) {
    //        Result result = new Result();
    //
    //        StorageDataService storageDataService = dataServiceFactory.getService("mongo");
    //
    //        Schema xmlSchema = dataset.getSchema();
    //        net.gigs2go.tableserver.core.data.Schema schema = null;
    //
    //        // If the Schema is specified, we save it
    //        // Otherwise we find the schema
    //        if (xmlSchema == null) {
    //            log.debug("Null schema - loading {}", dataset.getSchemaref());
    //            try {
    //                schema = storageDataService.loadSchema(dataset.getSchemaref());
    //            } catch (StorageException e) {
    //                result.getWarnings().add("Couldn't load schema for ref : " + dataset.getSchemaref());
    //                result.getWarnings().add(e.getMessage());
    //            }
    //
    //        } else {
    //            try {
    //                schema = DataFactory.convert(xmlSchema);
    //                storageDataService.addSchema(schema);
    //            } catch (StorageException e) {
    //                result.getWarnings().add("Couldn't save schema : " + schema.getName());
    //                result.getWarnings().add(e.getMessage());
    //                schema = null;
    //            }
    //        }
    //
    //        if (schema == null) {
    //            result.getWarnings().add("Schema error");
    //            result.setSuccess(Result.FAIL);
    //        } else {
    //            result.setSchemaref(schema.getName());
    //            Data data = dataset.getData();
    //            if (data != null) {
    //                try {
    //                    storageDataService.addData(schema, data);
    //                    result.getWarnings().add("Data OK");
    //                    result.setSuccess(Result.SUCCESS);
    //                } catch (StorageException e) {
    //                    result.getWarnings().add(e.getMessage());
    //                    result.getWarnings().add("Data error");
    //                    result.setSuccess(Result.FAIL);
    //                }
    //            } else {
    //                result.getWarnings().add("No Data");
    //                result.setSuccess(Result.SUCCESS);
    //            }
    //        }
    //        return result;
    //    }

    public void writeXML(Object o) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
            javax.xml.bind.Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // jaxbMarshaller.marshal(customer, file);
            jaxbMarshaller.marshal(o, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
