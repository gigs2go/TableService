/**
 * 
 */
package uk.org.gigs2go.tableserver.core.model.services;

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

import uk.org.gigs2go.tableserver.core.data.Column;
import uk.org.gigs2go.tableserver.core.data.DataServiceFactory;
import uk.org.gigs2go.tableserver.core.data.Schema;
import uk.org.gigs2go.tableserver.core.data.Table;
import uk.org.gigs2go.tableserver.core.data.TransferDataService;
import uk.org.gigs2go.tableserver.core.data.TransferException;
import uk.org.gigs2go.tableserver.core.test.TestUtils;

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
    public void testSchema0Input() {
        InputStream inputStream = testUtils.getInputStream("TestSchema0.xml");
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
    public void testSchema1Input() {
        InputStream inputStream = testUtils.getInputStream("TestSchema1.xml");
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
        assertEquals("SchemaOne", schema.getName());

        List<Column> schemaColumns = schema.getColumns().getColumn();
        assertNotNull(schemaColumns);
        assertEquals(15, schemaColumns.size());

    }

    @Test
    public void testData0Input() {
        InputStream inputStream = testUtils.getInputStream("TestData0.xml");
        assertNotNull(inputStream);
        Table table = null;
        TransferDataService xmlDataService = serviceFactory.getTransferServices().get("xml");
        assertNotNull(xmlDataService);
        try {
            table = xmlDataService.getData(inputStream);
        } catch (TransferException e) {
            fail("Exception thrown");
        }
        assertNotNull(table);
        log.debug("{}", table.toString());
        assertEquals("SchemaName", table.getSchemaName());
        assertEquals("FileName", table.getName());

    }

    @Test
    public void testData1Input() {
        InputStream inputStream = testUtils.getInputStream("TestData1.xml");
        assertNotNull(inputStream);
        Table table = null;
        TransferDataService xmlDataService = serviceFactory.getTransferServices().get("xml");
        assertNotNull(xmlDataService);
        try {
            table = xmlDataService.getData(inputStream);
        } catch (TransferException e) {
            fail("Exception thrown");
        }
        assertNotNull(table);
        log.debug("{}", table.toString());
        assertEquals("SchemaOne", table.getSchemaName());
        assertEquals("CountryFile", table.getName());

    }

}
