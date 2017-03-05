## Installing the XQuery example ReST extension (example.xqy)

The easiest way to do this is to use cURL

```bash
curl --anyauth --user user:password -X PUT \
-H "Content-type: application/xquery" -d@"./src/main/resources/example.xqy" \
"http://localhost:8000/LATEST/config/resources/example"
```

## Using the extension within your Java Code

See the contents of CallExampleExtension for an idea as to what's needed, but here's an example GET request:

```java
        ExampleExtensionManager eem = new ExampleExtensionManager(Util.getMarkLogicClient());
        eem.doHttpGet("/example-application/報告書.xml");
```

## Running the test from gradle

If you've already loaded the extension into MarkLogic, make sure your credentials / connection info are all set correctly in Util.java, then:

```bash
./gradlew run
```

### Sample output

```bash
./gradlew run
Parallel execution is an incubating feature.
:compileJava
:processResources UP-TO-DATE
:classes
:run
21:22:31.629 [main] DEBUG com.marklogic.client.DatabaseClientFactory - Creating new database client for server at 0.0.0.0:8000
21:22:31.652 [main] DEBUG com.marklogic.client.impl.JerseyServices - Connecting to 0.0.0.0 at 8000 as q
21:22:31.896 [main] INFO CallExampleExtension - 1. Sending a PUT request for the example ReST extension to load the initial document
21:22:31.909 [main] DEBUG com.marklogic.client.impl.JerseyServices - Putting resources/example
21:22:32.030 [main] INFO ExampleExtensionManager - <?xml version="1.0" encoding="UTF-8"?>
<status>XML Document [/example-application/報告書.xml] Loaded using an HTTP PUT on 2017-03-05T21:22:32.008373Z</status>

21:22:32.031 [main] INFO CallExampleExtension - 2. Sending a GET request for the example ReST extension to read the document
21:22:32.031 [main] DEBUG com.marklogic.client.impl.JerseyServices - Getting resources/example as null
21:22:32.047 [main] DEBUG ExampleExtensionManager - HTTP GET Request results:
21:22:32.047 [main] DEBUG ExampleExtensionManager - 	Mimetype: application/xml | Content Length: 61 | Format XML
21:22:32.049 [main] DEBUG ExampleExtensionManager - 	<?xml version="1.0" encoding="UTF-8"?>
<item>報告書</item>
21:22:32.049 [main] INFO CallExampleExtension - 3. Sending a POST request for the example ReST extension to update the content
21:22:32.049 [main] DEBUG com.marklogic.client.impl.JerseyServices - Posting resources/example
21:22:32.059 [main] INFO ExampleExtensionManager - <?xml version="1.0" encoding="UTF-8"?>
<status>XML Document [/example-application/報告書.xml] Updated using an HTTP POST on 2017-03-05T21:22:32.054905Z</status>

21:22:32.059 [main] INFO CallExampleExtension - 4. Sending a GET request for the example ReST extension to read the document
21:22:32.059 [main] DEBUG com.marklogic.client.impl.JerseyServices - Getting resources/example as null
21:22:32.064 [main] DEBUG ExampleExtensionManager - HTTP GET Request results:
21:22:32.065 [main] DEBUG ExampleExtensionManager - 	Mimetype: application/xml | Content Length: 100 | Format XML
21:22:32.065 [main] DEBUG ExampleExtensionManager - 	<?xml version="1.0" encoding="UTF-8"?>
<item modified="2017-03-05T21:22:32.054905Z">報告書</item>
21:22:32.065 [main] INFO CallExampleExtension - 5. Sending a DELETE request for the example ReST extension to update the content
21:22:32.065 [main] DEBUG com.marklogic.client.impl.JerseyServices - Deleting resources/example
21:22:32.074 [main] INFO ExampleExtensionManager - null

BUILD SUCCESSFUL

Total time: 1.737 secs
```

