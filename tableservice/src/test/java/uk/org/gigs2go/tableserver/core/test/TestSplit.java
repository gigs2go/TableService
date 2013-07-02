/**
 * 
 */
package uk.org.gigs2go.tableserver.core.test;

import java.util.regex.Pattern;

/**
 * @author tim
 *
 */
public class TestSplit {

    /**
     * @param args
     */
    public static void main ( String[] args ) {
        TestSplit test = new TestSplit();
        
        String[] result = test.split1( "aaa|bbb|ccc|ddd" );
        for ( String bit : result ) {
            System.out.println( "Got '" + bit + "'" );
        }

    }
    
    private String[] split1( String str ) {
        return str.split( Pattern.quote("|") );
    }

}
