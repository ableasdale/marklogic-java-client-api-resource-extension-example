## Installing the extension

```bash
curl --anyauth --user user:password -X PUT \
-H "Content-type: application/xquery" -d@"./src/main/resources/example.xqy" \
"http://localhost:8000/LATEST/config/resources/example"
```

## Calling the extension

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
