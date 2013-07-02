/**
 * 
 */
package uk.org.gigs2go.tableserver.core.test;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
@Service
public class TestUtils {
    private Logger log = LoggerFactory.getLogger(TestUtils.class);

    @Autowired
    private ApplicationContext applicationContext;

    public LocalDate getDate(String date) {
        LocalDate result = new LocalDate();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        result = LocalDate.parse(date, formatter);
        return result;
    }

    public InputStream getInputStream(String fileName) {
        InputStream result = null;
        Resource resource = applicationContext.getResource("classpath:" + fileName);
        try {
            result = resource.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    String objectToJson(Object object) {
        String result = "Error";
        ObjectMapper mapper = new ObjectMapper();

        try {
            result = mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
