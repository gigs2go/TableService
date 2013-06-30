/**
 * 
 */
package net.gigs2go.tableserver.core.mvc.controllers;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author tim
 * 
 */
//@WebAppConfiguration
//@ContextConfiguration({ "classpath*:config/repo.xml", "classpath*:config/mvc.xml", "classpath*:config/security.xml" })
public abstract class AbstractControllerTest extends AbstractJUnit4SpringContextTests {
    private Logger log = LoggerFactory.getLogger(AbstractControllerTest.class);

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        loadSchema();
    }

    private void loadSchema() {

    }

    //    public Schema loadData ( String schemaName, String fileName ) {
    //        Schema result = null;
    //        InputStream inputStream = getInputStream( fileName );
    //        assertNotNull( inputStream );
    //        CollectionInput input = new CsvCollectionInput( schemaName, inputStream );
    //        assertNotNull( input );
    //        result = input.getSchema();
    //        assertNotNull( result );
    //        assertEquals( schemaName, result.getSchemaName() );
    //        DB db = mongoTemplate.getDb();
    //        DBCollection collection = db.getCollection( schemaName );
    //        collection.drop();
    //        List<Map<String, Object>> documents = new ArrayList<Map<String, Object>>();
    //        while ( input.hasNext() ) {
    //            Map<String, Object> doc = input.next();
    //            documents.add( doc );
    //            mongoService.addDocument( result.getSchemaName(), doc );
    //        }
    //        return result;
    //    }

    //    public String loadFileData(String fileName) {
    //        StringBuffer result = new StringBuffer();
    //        BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream(fileName)));
    //        // InputStream inputStream = getInputStream( fileName );
    //        assertNotNull(reader);
    //        String line;
    //        try {
    //            line = reader.readLine();
    //            while (line != null) {
    //                result.append(line);
    //                line = reader.readLine();
    //            }
    //        } catch (IOException e) {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //        return result.toString();
    //    }

}