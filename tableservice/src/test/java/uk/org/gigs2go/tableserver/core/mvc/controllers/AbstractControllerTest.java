/**
 * 
 */
package uk.org.gigs2go.tableserver.core.mvc.controllers;

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
    }

}