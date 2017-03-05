xquery version "1.0-ml";

module namespace example = "http://marklogic.com/rest-api/resource/example";

declare namespace rapi = "http://marklogic.com/rest-api";
declare default function namespace "http://www.w3.org/2005/xpath-functions";
declare option xdmp:mapping "false";

(:~
 :   GET method
 :)
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

(:~
 :   PUT method
 :)
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
        then (xdmp:set-transaction-mode("update"),xdmp:document-insert($uri, $input), xdmp:commit())
        else error((),"ACK", "Invalid type, accepts application/xml only")
    return document { <status>XML Document [{$uri}] Loaded using an HTTP PUT on {fn:current-dateTime()}</status>}
};

(:~
 :   POST method
 :)
declare %rapi:transaction-mode("update") function example:post(
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
        then (xdmp:set-transaction-mode("update"), xdmp:node-insert-child(doc($uri)/node(), attribute modified { fn:current-dateTime() }), xdmp:commit())
        else error((),"ACK", "Invalid type, accepts application/xml only")
    return document { <status>XML Document [{$uri}] Updated using an HTTP POST on {fn:current-dateTime()}</status>}
};

(:~
 :   DELETE method
 :)
declare function example:delete(
    $context as map:map,
    $params  as map:map
) as document-node()?
{
    xdmp:log("delete - TODO: Implement")
};