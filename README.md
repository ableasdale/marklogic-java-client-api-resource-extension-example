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
:processResources
:classes
:run
20:23:33.190 [main] DEBUG com.marklogic.client.DatabaseClientFactory - Creating new database client for server at 0.0.0.0:8000
20:23:33.213 [main] DEBUG com.marklogic.client.impl.JerseyServices - Connecting to 0.0.0.0 at 8000 as q
20:23:33.438 [main] INFO CallExampleExtension - 1. Sending a PUT request for the example ReST extension to load the initial document
20:23:33.449 [main] DEBUG Util - <?xml version="1.0" encoding="UTF-8"?>
<item>報告書</item>

20:23:33.450 [main] DEBUG com.marklogic.client.impl.JerseyServices - Putting resources/example
20:23:33.585 [main] INFO ExampleExtensionManager - <?xml version="1.0" encoding="UTF-8"?>
<status>XML Document [/example-application/報告書.xml] Loaded using an HTTP PUT on 2017-03-05T20:23:33.558154Z</status>

20:23:33.585 [main] INFO CallExampleExtension - 2. Sending a GET request for the example ReST extension to read the document
20:23:33.586 [main] DEBUG com.marklogic.client.impl.JerseyServices - Getting resources/example as null
20:23:33.607 [main] DEBUG ExampleExtensionManager - HTTP GET Request results:
20:23:33.607 [main] DEBUG ExampleExtensionManager - 	Mimetype: application/xml | Content Length: 61 | Format XML
20:23:33.609 [main] DEBUG ExampleExtensionManager - 	<?xml version="1.0" encoding="UTF-8"?>
<item>報告書</item>
20:23:33.609 [main] INFO CallExampleExtension - 3. Sending a POST request for the example ReST extension to update the content
20:23:33.609 [main] DEBUG Util - <?xml version="1.0" encoding="UTF-8"?>
<item>報告書</item>

20:23:33.610 [main] DEBUG com.marklogic.client.impl.JerseyServices - Posting resources/example
20:23:33.619 [main] INFO ExampleExtensionManager - <?xml version="1.0" encoding="UTF-8"?>
<status>XML Document [/example-application/報告書.xml] Updated using an HTTP POST on 2017-03-05T20:23:33.614631Z</status>

20:23:33.619 [main] INFO CallExampleExtension - 4. Sending a GET request for the example ReST extension to read the document
20:23:33.619 [main] DEBUG com.marklogic.client.impl.JerseyServices - Getting resources/example as null
20:23:33.625 [main] DEBUG ExampleExtensionManager - HTTP GET Request results:
20:23:33.626 [main] DEBUG ExampleExtensionManager - 	Mimetype: application/xml | Content Length: 100 | Format XML
20:23:33.626 [main] DEBUG ExampleExtensionManager - 	<?xml version="1.0" encoding="UTF-8"?>
<item modified="2017-03-05T20:23:33.614631Z">報告書</item>
20:23:33.626 [main] INFO CallExampleExtension - 5. Sending a DELETE request for the example ReST extension to update the content
20:23:33.626 [main] DEBUG com.marklogic.client.impl.JerseyServices - Deleting resources/example
20:23:33.638 [main] INFO ExampleExtensionManager - null

BUILD SUCCESSFUL

Total time: 1.859 secs
```


## Earlier notes below..

### GET

```bash
curl --anyauth --user user:password -X GET \
-H "Accept: application/xml" \
"http://localhost:8000/LATEST/resources/example?rs:arg1=super fantastic"
```

### PUT

```bash
curl --anyauth --user user:password -X PUT -d "<input/>" \
-H "Content-type: application/xml" \
http://localhost:8000/LATEST/resources/example
```

### POST - TODO

```bash
curl --anyauth --user user:password -X POST -d "<input/>" -H "Content-type: application/xml" http://localhost:8000/LATEST/resources/example
```

curl --anyauth --user q:q -X POST -d "<input/>" -H "Content-type: application/xml" http://localhost:8000/LATEST/resources/example
