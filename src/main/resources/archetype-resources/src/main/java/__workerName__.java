#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.io.IOException;

public class ${workerName} 
{
    public static void main( String[] args ) throws Exception {
        System.out.println( "Running worker..." );        
        
        try {
        	
			IronWorkerPayload payload = new IronWorkerPayload(args);
               
	        /*
	         * Retrieve a parameter from the payload
	         */
			String nameParam = payload.getString("name"); 	        

			/*
			 * Hello!
			 */
			if (nameParam != null) {
				System.out.println("Hello " + nameParam + "!");
			} else {
				System.out.println("Hello World!");
			}	       	
        
        } catch (IllegalArgumentException e) {
        	System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
        	System.err.println(e.getMessage());
            System.exit(1);
        }
        
        System.out.println( "Worker finished.");
    }
}
