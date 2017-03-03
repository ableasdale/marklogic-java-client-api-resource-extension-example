import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 01/03/2017.
 */
public class CallExampleExtension_2 {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        /* Call the "example" Resource Extension and pass it a client */
        ExampleExtensionManager eem = new ExampleExtensionManager(Util.getMarkLogicClient());

        LOG.info("Sending a PUT request for the example ReST extension");
        eem.doHttpPut("/example-application/報告書.xml");

        LOG.info("Sending a GET request for the example ReST extension");
        eem.doHttpGet("/example-application/報告書.xml");

    }
}
