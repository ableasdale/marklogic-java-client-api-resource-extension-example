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
16:35:30.313 [main] DEBUG com.marklogic.client.DatabaseClientFactory - Creating new database client for server at 0.0.0.0:8000
16:35:30.334 [main] DEBUG com.marklogic.client.impl.JerseyServices - Connecting to 0.0.0.0 at 8000 as q
16:35:30.559 [main] INFO CallExampleExtension - 1. Sending a PUT request for the example ReST extension to load the initial document
16:35:30.572 [main] DEBUG Util - <?xml version="1.0" encoding="UTF-8"?>
<item>報告書</item>

16:35:30.573 [main] DEBUG com.marklogic.client.impl.JerseyServices - Putting resources/example
16:35:30.719 [main] INFO ExampleExtensionManager - <?xml version="1.0" encoding="UTF-8"?>
<status>XML Document /example-application/報告書.xml Loaded using an HTTP PUT on 2017-03-03T16:35:30.676897Z</status>

16:35:30.719 [main] INFO CallExampleExtension - 2. Sending a GET request for the example ReST extension to read the document
16:35:30.720 [main] DEBUG com.marklogic.client.impl.JerseyServices - Getting resources/example as null
16:35:30.736 [main] DEBUG ExampleExtensionManager - HTTP GET Request results:
16:35:30.737 [main] DEBUG ExampleExtensionManager - 	Mimetype: application/xml | Content Length: 61 | Format XML
16:35:30.738 [main] DEBUG ExampleExtensionManager - 	<?xml version="1.0" encoding="UTF-8"?>
<item>報告書</item>
16:35:30.738 [main] INFO CallExampleExtension - 3. Sending a POST request for the example ReST extension to update the content
16:35:30.738 [main] DEBUG Util - <?xml version="1.0" encoding="UTF-8"?>
<item>報告書</item>

16:35:30.738 [main] DEBUG com.marklogic.client.impl.JerseyServices - Posting resources/example
16:35:30.743 [main] INFO ExampleExtensionManager - null
16:35:30.743 [main] INFO CallExampleExtension - 4. Sending a GET request for the example ReST extension to read the document
16:35:30.743 [main] DEBUG com.marklogic.client.impl.JerseyServices - Getting resources/example as null
16:35:30.748 [main] DEBUG ExampleExtensionManager - HTTP GET Request results:
16:35:30.749 [main] DEBUG ExampleExtensionManager - 	Mimetype: application/xml | Content Length: 61 | Format XML
16:35:30.749 [main] DEBUG ExampleExtensionManager - 	<?xml version="1.0" encoding="UTF-8"?>
<item>報告書</item>

BUILD SUCCESSFUL

Total time: 2.587 secs
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
