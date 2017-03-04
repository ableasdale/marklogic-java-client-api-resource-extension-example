xquery version "1.0-ml";

module namespace example = "http://marklogic.com/rest-api/resource/example";
declare default function namespace "http://www.w3.org/2005/xpath-functions";
declare option xdmp:mapping "false";

(: Function responding to GET method - must use local name 'get':)
declare function example:get(
    $context as map:map,
    $params  as map:map
) as document-node()*
{
    (: set 'output-types', used to generate content-type header :)
    let $output-types := map:put($context,"output-types","application/xml") 
    let $uri := map:get($params, "uri")
    let $_ := xdmp:log($uri)
    return fn:doc($uri) 
};

(: Function responding to PUT method - must use local name 'put'. :)
declare function example:put(
    $context as map:map,
    $params  as map:map,
    $input   as document-node()*
) as document-node()?
{
    (: get 'input-types' to use in content negotiation :)
    let $input-types := map:get($context,"input-types")
    let $uri := map:get($params, "uri")
    let $negotiate := 
        if ($input-types = "application/xml")
        then (xdmp:document-insert($uri, $input))  
        else error((),"ACK",
          "Invalid type, accepts application/xml only") 
    return document { <status>XML Document [{$uri}] Loaded using an HTTP PUT on {fn:current-dateTime()}</status>}
};

(: Function responding to POST method - must use local name 'post'. :)
declare function example:post(
    $context as map:map,
    $params  as map:map,
    $input   as document-node()*
) as document-node()*
{
    (: get 'input-types' to use in content negotiation :)
    let $input-types := map:get($context,"input-types")
    let $uri := map:get($params, "uri")
    let $negotiate :=
        if ($input-types = "application/xml")
        then (xdmp:node-insert-child(doc($uri)/node(), attribute modified { fn:current-dateTime() }))
        else error((),"ACK",
                "Invalid type, accepts application/xml only")
    return document { <status>XML Document [{$uri}] Updated using an HTTP POST on {fn:current-dateTime()}</status>}
};

(: Func responding to DELETE method - must use local name 'delete'. :)
declare function example:delete(
    $context as map:map,
    $params  as map:map
) as document-node()?
{
    xdmp:log("delete!")
};