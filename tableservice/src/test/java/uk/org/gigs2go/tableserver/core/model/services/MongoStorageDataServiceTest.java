package uk.org.gigs2go.tableserver.core.model.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import uk.org.gigs2go.tableserver.core.data.AggregationType;
import uk.org.gigs2go.tableserver.core.data.DataServiceFactory;
import uk.org.gigs2go.tableserver.core.data.JaxbUtils;
import uk.org.gigs2go.tableserver.core.data.Query;
import uk.org.gigs2go.tableserver.core.data.Schema;
import uk.org.gigs2go.tableserver.core.data.SchemaType;
import uk.org.gigs2go.tableserver.core.data.StorageDataService;
import uk.org.gigs2go.tableserver.core.data.StorageException;
import uk.org.gigs2go.tableserver.core.data.Table;
import uk.org.gigs2go.tableserver.core.data.TransferException;
import uk.org.gigs2go.tableserver.core.test.TestUtils;

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
        Table table = null;
        try {
            table = (Table) jaxbUtils.getObject(inputStream, SchemaType.TABLE);
        } catch (TransferException e) {
            fail("Exception thrown");
        }
        assertNotNull(table);

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
            storageDataService.addData(schema, table);
        } catch (StorageException e) {
            fail("Unexpected exception" + e.getResult().toString());
        }

    }

    @Test
    public void testGetChartItems2() {
        StorageDataService storageDataService = factory.getStorageServices().get("mongo");

        Query query = new Query();
        query.setCollectionName("FileName");
        query.setDimension("Continent");
        query.setMeasure("LifeExpectancy");
        query.setAggregation(AggregationType.AVG);
        Map<String, Object> items = null;
        try {
            items = storageDataService.getResults(query);
        } catch (StorageException e) {
            fail();
        }
        assertNotNull(items);
        assertEquals(1, items.size());
    }

}
