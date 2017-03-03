import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 01/03/2017.
 */
public class CallExampleExtension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        /* Call the "example" Resource Extension and pass it a client */
        ExampleExtensionManager eem = new ExampleExtensionManager(Util.getMarkLogicClient());

        LOG.info("1. Sending a PUT request for the example ReST extension to load the initial document");
        eem.doHttpPut("/example-application/報告書.xml");

        LOG.info("2. Sending a GET request for the example ReST extension to read the document");
        eem.doHttpGet("/example-application/報告書.xml");

        LOG.info("3. Sending a POST request for the example ReST extension to update the content");
        eem.doHttpPost("/example-application/報告書.xml");

        LOG.info("4. Sending a GET request for the example ReST extension to read the document");
        eem.doHttpGet("/example-application/報告書.xml");


    }
}
