/**
 * 
 */
package uk.org.gigs2go.tableserver.core.mvc.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import uk.org.gigs2go.tableserver.core.data.DataServiceFactory;
import uk.org.gigs2go.tableserver.core.data.Result;
import uk.org.gigs2go.tableserver.core.data.Schema;
import uk.org.gigs2go.tableserver.core.data.StorageDataService;
import uk.org.gigs2go.tableserver.core.data.StorageException;
import uk.org.gigs2go.tableserver.core.data.Table;
import uk.org.gigs2go.tableserver.core.data.TransferDataService;
import uk.org.gigs2go.tableserver.core.data.TransferException;


/**
 * @author tim
 * 
 */
@Controller
public class DataController {
    private Logger log = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private DataServiceFactory dataServiceFactory;

    @RequestMapping(value = "/loadSchema", method = RequestMethod.POST)
    @ResponseBody
    public Result loadSchema(HttpServletRequest request, HttpServletResponse response, SessionStatus status) {
        Result result = new Result();
        log.debug("Uploaded file content type is {}", request.getContentType());
        log.debug("Uploaded filesize is {}", request.getContentLength());
        try {
            TransferDataService transferService = dataServiceFactory.getTransferServices().get("xml");
            Schema schema = transferService.getSchema(request.getInputStream());
            StorageDataService storageService = dataServiceFactory.getStorageServices().get("mongo");
            result = storageService.addSchema(schema);
        } catch (IOException e) {
            result.setSuccess(Result.FAIL);
            result.setSchemaname("Empty");
            result.getMessages().add("Error getting input stream from request - check log for details");

        } catch (StorageException e) {
            result = e.getResult();

        } catch (TransferException e) {
            result = e.getResult();
        }
        status.setComplete();

        return result;
    }

    @RequestMapping(value = "/loadData", method = RequestMethod.POST)
    @ResponseBody
    public Result loadData(HttpServletRequest request, HttpServletResponse response, SessionStatus status) {
        Result result = new Result();
        log.debug("Uploaded file content type is {}", request.getContentType());
        log.debug("Uploaded filesize is {}", request.getContentLength());
        try {
            TransferDataService transferService = dataServiceFactory.getTransferServices().get("xml");
            Table table = transferService.getData(request.getInputStream());
            StorageDataService storageService = dataServiceFactory.getStorageServices().get("mongo");
            Schema schema = storageService.loadSchema(table.getSchemaName());
            result = storageService.addData(schema, table);
        } catch (IOException e) {
            result.setSuccess(Result.FAIL);
            result.setSchemaname("Empty");
            result.getMessages().add("Error getting input stream from request - check log for details");

        } catch (StorageException e) {
            result = e.getResult();

        } catch (TransferException e) {
            result = e.getResult();
        }
        status.setComplete();

        return result;
    }

}
