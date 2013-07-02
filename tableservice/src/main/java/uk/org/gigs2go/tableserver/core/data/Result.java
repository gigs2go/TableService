package uk.org.gigs2go.tableserver.core.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "success", "schemaname", "messages" })
@XmlRootElement(name = "result")
public class Result {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    @XmlElement(required = true)
    protected String success;
    @XmlElement(required = true)
    protected String schemaname;
    @XmlElement(required = true)
    protected List<String> messages = new ArrayList<String>();

    /**
     * @return the success
     */
    public String getSuccess() {
        return this.success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     * @return the schemaname
     */
    public String getSchemaname() {
        return this.schemaname;
    }

    /**
     * @param schemaname the schemaname to set
     */
    public void setSchemaname(String schemaname) {
        this.schemaname = schemaname;
    }

    /**
     * @return the messages
     */
    public List<String> getMessages() {
        return this.messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Result [success=" + this.success + ", schemaname=" + this.schemaname + ", messages=" + this.messages + "]";
    }

}
