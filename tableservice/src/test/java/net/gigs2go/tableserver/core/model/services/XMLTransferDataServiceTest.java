/**
 * 
 */
package net.gigs2go.tableserver.core.model.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.gigs2go.tableserver.core.data.Column;
import net.gigs2go.tableserver.core.data.Data;
import net.gigs2go.tableserver.core.data.DataServiceFactory;
import net.gigs2go.tableserver.core.data.Schema;
import net.gigs2go.tableserver.core.data.TransferDataService;
import net.gigs2go.tableserver.core.data.TransferException;
import net.gigs2go.tableserver.core.test.TestUtils;

/**
 * @author tim
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath*:config/repo.xml", "classpath*:config/mvc.xml", "classpath*:config/security.xml" })
public class XMLTransferDataServiceTest extends AbstractJUnit4SpringContextTests {
    private Logger log = LoggerFactory.getLogger(XMLTransferDataServiceTest.class);

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private DataServiceFactory serviceFactory;

    @Test
    public void testSchemaInput() {
        InputStream inputStream = testUtils.getInputStream("TestSchema.xml");
        assertNotNull(inputStream);
        Schema schema = null;
        TransferDataService xmlDataService = serviceFactory.getTransferServices().get("xml");
        assertNotNull(xmlDataService);
        try {
            schema = xmlDataService.getSchema(inputStream);
        } catch (TransferException e) {
            fail("Exception thrown");
        }
        assertNotNull(schema);
        assertEquals("SchemaName", schema.getName());

        List<Column> schemaColumns = schema.getColumns().getColumn();
        assertNotNull(schemaColumns);
        assertEquals(13, schemaColumns.size());

    }

    @Test
    public void testDataInput() {
        InputStream inputStream = testUtils.getInputStream("TestData0.xml");
        assertNotNull(inputStream);
        Data data = null;
        TransferDataService xmlDataService = serviceFactory.getTransferServices().get("xml");
        assertNotNull(xmlDataService);
        try {
            data = xmlDataService.getData(inputStream);
        } catch (TransferException e) {
            fail("Exception thrown");
        }
        assertNotNull(data);
        log.debug("{}", data.toString());
        assertEquals("SchemaName", data.getSchemaName());
        assertEquals("FileName", data.getName());

    }

}
