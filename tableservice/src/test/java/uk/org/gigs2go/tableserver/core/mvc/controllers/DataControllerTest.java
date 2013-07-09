/**
 * 
 */
package uk.org.gigs2go.tableserver.core.mvc.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import uk.org.gigs2go.tableserver.core.test.TestUtils;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author tim
 *
 */
@WebAppConfiguration
@ContextConfiguration({ "classpath*:config/repo.xml", "classpath*:config/mvc.xml" })
public class DataControllerTest extends AbstractControllerTest {
    private Logger log = LoggerFactory.getLogger(DataControllerTest.class);

    @Autowired
    private TestUtils testUtils;

    @Test
    public void testLoadSchema0() throws Exception {
        InputStream inputStream = testUtils.getInputStream("TestSchema0.xml");
        assertNotNull(inputStream);
        int expected = inputStream.available();
        if (expected < 1) {
            fail();
        }

        byte[] bytes = new byte[expected];
        int got = inputStream.read(bytes);
        assertEquals(expected, got);
        this.mockMvc.perform(post("/loadSchema").contentType(MediaType.APPLICATION_XML).content(bytes).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testLoadData0() throws Exception {
        InputStream inputStream = testUtils.getInputStream("TestData0.xml");
        assertNotNull(inputStream);
        int expected = inputStream.available();
        if (expected < 1) {
            fail();
        }

        byte[] bytes = new byte[expected];
        int got = inputStream.read(bytes);
        assertEquals(expected, got);
        this.mockMvc.perform(post("/loadData").contentType(MediaType.APPLICATION_XML).content(bytes).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
    }

}
