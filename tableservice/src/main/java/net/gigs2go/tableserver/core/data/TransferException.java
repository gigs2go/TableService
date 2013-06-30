/**
 * 
 */
package net.gigs2go.tableserver.core.data;

/**
 * @author tim
 *
 */
public class TransferException extends Exception {
    private static final long serialVersionUID = 1L;

    private Result result = null;

    /**
     * 
     */
    public TransferException(Result result) {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return this.result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TransferException [result=" + this.result.toString() + "]";
    }

}
