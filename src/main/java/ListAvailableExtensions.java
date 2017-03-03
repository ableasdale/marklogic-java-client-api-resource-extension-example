import com.marklogic.client.admin.ResourceExtensionsManager;
import com.marklogic.client.io.Format;
import com.marklogic.client.io.StringHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Can be used to interrogate MarkLogic to ensure that our example endpoint installed successfully.
 *
 * @author Alex Bleasdale
 * @version 0.1
 */
public class ListAvailableExtensions {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        /* 1. Instantiate the Resource Extension Manager */
        ResourceExtensionsManager resourceMgr =
                Util.getMarkLogicClient().newServerConfigManager().newResourceExtensionsManager();

        /* 2. Get a list of all current services from the Resource Extension Manager */
        String result = resourceMgr.listServices(
                new StringHandle().withFormat(Format.XML)).get();

        /* 3. Log the result set */
        LOG.info(result);

        /*
            If this has worked, you should see your ReST endpoint is now listed in the output:
            <rapi:resource><rapi:name>example</rapi:name>
         */
    }
}
