import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.util.RequestParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility helper class
 *
 * @author Alex Bleasdale
 * @version 0.1
 */
public class Util {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String IP_ADDR = "0.0.0.0";
    private static final int PORT = 8000;
    private static final String USER = "q";
    private static final String PASS = "q";

    private static final String SAMPLE_XML_FILENAME = "sample.xml";

    /**
     * Creates the DatabaseClient Object and returns it
     *
     * @return - Instantiated MarkLogic Database Client
     */
    protected static DatabaseClient getMarkLogicClient() {
        return DatabaseClientFactory.newClient(IP_ADDR, PORT, USER, PASS, DatabaseClientFactory.Authentication.DIGEST);

    }

    /**
     * Convenience method for opening a file as a stream using the classloader
     * @param fileName
     * @return - An InputStream containing the file data
     * @throws IOException
     */
    protected static InputStream openStream(String fileName) throws IOException {
        return Util.class.getClassLoader().getResourceAsStream(fileName);
    }

    /**
     * Example code to get a MarkLogic Java Client API InputStreamHandle for an XML file
     * @return - an InputStreamHandle to a sample XML file used in this example code.
     */
    protected static InputStreamHandle getSampleXMLFile() {
        InputStream sourceStream = null;
        try {
            sourceStream = Util.openStream(SAMPLE_XML_FILENAME);
        } catch (IOException e) {
            LOG.error("I/O: Unable to open the stream", e);
        }
        if (sourceStream == null)
            throw new RuntimeException("Something went wrong with the Source Stream (which is null).");
        return new InputStreamHandle(sourceStream);
    }

    // Workaround (for now)
    protected static String getSampleXMLAsString() {
        URL resource = MethodHandles.lookup().lookupClass().getResource(SAMPLE_XML_FILENAME);
        String s = "";
        try {
            s = new String(Files.readAllBytes(Paths.get(resource.toURI())));
        } catch (IOException e) {
            LOG.error("I/O: Unable to open the file", e);
        } catch (URISyntaxException e) {
            LOG.error("URI: Unable to open the file as a URI resource", e);
        }
        LOG.debug(s);
        return s;
    }

    /**
     * Convenience method to access an object containing the standard RequestParameters
     *
     * @param uri - the URI of the document that is going to be used in this example.
     * @return - the MarkLogic Client API RequestParameters Object
     */
    protected static RequestParameters getExampleRequestParams(String uri) {
        RequestParameters params = new RequestParameters();
        params.add("uri", uri);
        return params;
    }
}
