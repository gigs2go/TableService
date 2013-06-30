package net.gigs2go.tableserver.core.model.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.gigs2go.tableserver.core.data.Data;
import net.gigs2go.tableserver.core.data.DataServiceFactory;
import net.gigs2go.tableserver.core.data.JaxbUtils;
import net.gigs2go.tableserver.core.data.Schema;
import net.gigs2go.tableserver.core.data.SchemaType;
import net.gigs2go.tableserver.core.data.StorageDataService;
import net.gigs2go.tableserver.core.data.StorageException;
import net.gigs2go.tableserver.core.data.TransferException;
import net.gigs2go.tableserver.core.test.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:config/repo.xml", "classpath*:config/mvc.xml", "classpath*:config/security.xml" })
@Transactional
public class MongoStorageDataServiceTest extends AbstractJUnit4SpringContextTests {
    private Logger log = LoggerFactory.getLogger(MongoStorageDataServiceTest.class);

    @Autowired
    private DataServiceFactory factory;

    @Autowired
    private JaxbUtils jaxbUtils;

    @Autowired
    private TestUtils testUtils;

    @Test
    public void testStoreSchema() {
        InputStream inputStream = testUtils.getInputStream("TestSchema.xml");
        assertNotNull(inputStream);
        Schema schema = null;
        try {
            schema = (Schema) jaxbUtils.getObject(inputStream, SchemaType.SCHEMA);
        } catch (TransferException e) {
            fail("Exception thrown");
        }
        assertNotNull(schema);

        StorageDataService storageDataService = factory.getStorageServices().get("mongo");
        assertNotNull(storageDataService);
        try {
            storageDataService.addSchema(schema);
        } catch (StorageException e) {
            fail("Unexpected exception" + e.getResult().toString());
        }

    }

    @Test
    public void testLoadSchema() {
        StorageDataService storageDataService = factory.getStorageServices().get("mongo");
        assertNotNull(storageDataService);
        Schema schema = null;
        try {
            schema = storageDataService.loadSchema("SchemaName");
        } catch (StorageException e) {
            fail("Unexpected exception" + e.getResult().toString());
        }
        assertNotNull(schema);

    }

    @Test
    public void testStoreData() {
        InputStream inputStream = testUtils.getInputStream("TestData0.xml");
        assertNotNull(inputStream);
        Data data = null;
        try {
            data = (Data) jaxbUtils.getObject(inputStream, SchemaType.DATA);
        } catch (TransferException e) {
            fail("Exception thrown");
        }
        assertNotNull(data);

        StorageDataService storageDataService = factory.getStorageServices().get("mongo");
        assertNotNull(storageDataService);
        Schema schema = null;
        try {
            schema = storageDataService.loadSchema("SchemaName");
        } catch (StorageException e) {
            fail("Unexpected exception" + e.getResult().toString());
        }
        assertNotNull(schema);
        try {
            storageDataService.addData(schema, data);
        } catch (StorageException e) {
            fail("Unexpected exception" + e.getResult().toString());
        }

    }

    //    @Test
    //    public void testGetChartItems2() {
    //        StorageDataService storageDataService = factory.getStorageServices().get("mongo");
    //
    //        String collectionName = "FileData_21";
    //        String dimension = "Continent";
    //        String dimensionValue = "North America";
    //        String dimension2 = "Code";
    //        String measure = "LifeExpectancy";
    //        String aggregation = "avg";

    //        List<ChartItem> items = dataService.getChartItems ( collectionName, dimension, dimensionValue, dimension2, measure, aggregation );
    //        assertNotNull( items );
    //        assertEquals( 99, items.size());
    //    }

}
