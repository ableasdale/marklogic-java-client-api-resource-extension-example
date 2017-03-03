import com.marklogic.client.DatabaseClient;
import com.marklogic.client.extensions.ResourceManager;
import com.marklogic.client.extensions.ResourceServices;
import com.marklogic.client.io.Format;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.util.RequestParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static com.marklogic.client.extensions.ResourceServices.ServiceResultIterator;

/**
 * Created by ableasdale on 01/03/2017.
 */
public class ExampleExtensionManager extends ResourceManager {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String NAME = "example";

    /**
     * Constructor
     *
     * @param client
     */
    public ExampleExtensionManager(DatabaseClient client) {
        super();
        client.init(NAME, this);
    }

    /**
     * Handle the HTTP GET
     *
     * @param uri
     */
    public void doHttpGet(String uri) {

        RequestParameters params = Util.getExampleRequestParams(uri);

        String[] mimetypes = {"application/xml", "application/json"};
        ServiceResultIterator resultItr = getServices().get(params, mimetypes);

        LOG.debug("HTTP GET Request results:");
        if(resultItr.hasNext()){
            ResourceServices.ServiceResult r = resultItr.next();
            LOG.debug(String.format("\tMimetype: %s | Content Length: %d | Format %s", r.getMimetype(), r.getLength(), r.getFormat()));
            StringHandle readHandle = new StringHandle();
            LOG.debug("\t"+r.getContent(readHandle).get());
        }
        resultItr.close();
    }

    /**
     * Handle the HTTP PUT
     *
     * @param uri
     */
    public void doHttpPut(String uri) {

        RequestParameters params = Util.getExampleRequestParams(uri);

        // doesn't work with an XML Write Handle - it complains it's not "application/xml"
        // XMLWriteHandle handle = Util.getSampleXMLFile();

        StringHandle handle = new StringHandle().withFormat(Format.XML).with(Util.getSampleXMLAsString());
        StringHandle result = getServices().put(params, handle, new StringHandle());

        LOG.info(result.toString());
    }

    /**
     * Handle the HTTP POST
     *
     * @param uri
     */
    public void doHttpPost(String uri) {

        RequestParameters params = Util.getExampleRequestParams(uri);
        StringHandle handle = new StringHandle().withFormat(Format.XML).with(Util.getSampleXMLAsString());
        StringHandle result = getServices().post(params, handle, new StringHandle());

        LOG.info(result.toString());
    }
}
