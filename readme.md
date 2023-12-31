Spring REST
------------

REST
 -----

1. REST stands for REpresentational State Transfer.
2. It is an architectural style of designing distributed applications, in which
   requesting system accesses and manipulates textual representations of
   Web resources by using a uniform and predefined set of stateless
   operations, given a set of constraints
3. Web resources are made available through URIs (Uniform Resource
   Identifiers) and are accessed or modified, usually through HTTP
   operations.
4. For example:
   1. GET /customers
   2. GET /customers/1
   3. PUT /customers/6
   4. DELETE /customers/3
5. Most implementations of REST services are using HTTP as the application
   protocol, and JSON as format that moves data between caller and callee.
   However REST is not necessarily tied to HTTP or JSON. 
6. REST defines constraints to be used for creating Web services, aligning with
   those constraints results in gaining non-functional properties of system, such
   as performance, scalability, simplicity, modifiability, visibility, portability,
   and reliability
   1. Client-server architecture and Separation of Client-Server concerns
   2. Statelessness
   3. Cacheability
   4. Uniform interface
      1. Resource identification in requests
      2. Resource manipulation through representations
      3. Self-descriptive messages
      4. Hypermedia as the engine of application state
   5. Layered system
   6. Code on demand (optional)
7. Requesting system interacts with the target system, usually by HTTP
   requests which should follow those semantics:
    ```
   HTTP Method(verb) + URI(noun (plural form)
   GET /customers
   GET /customers/1
   PUT /customers/6
   PATCH /customers/7
   DELETE /customers/3

    ```
8. REST services often allow you to perform all CRUD operations for specified
   resources:
   1. C – create
   2. R – read
   3. U – update
   4. D - delete
9. Following HTTP Methods are usually used with REST services:
   1. GET – fetch resource or collection of resources at the specified URI
   2. PUT – create new resource, or update existing resource at the
      specified URI
   3. PATCH – partially updates existing resource at the specified URI, for
      example update only firstName of Customer
   4. DELETE – delete resource at the specified URI
   5. POST – triggers operation at server or creates a new resource at the
      specified URI
   6. Other HTTP Methods that REST service can support are: HEAD,
      CONNECT, OPTIONS, TRACE.
10. In case of request processing error, REST service should respond in a way for it to
    be easily to understand cause of issue. Error return to requester usually includes:
    1. HTTP Status Code
    2. Code ID
    3. Human readable error message that explains error
11. HTTP Status Code should fall within one of the groups:
    1. 1xx – Informational
    2. 2xx – Success
       1. 200 OK
       2. 201 Created
       3. 204 No Content
    3. 3xx – Redirection
    4. 4xx - Client Error
       1. 400 Bad Request
       2. 401 Unauthorized
       3. 403 Forbidden
       4. 404 Not Found
    5. 5xx - Server Error
       1. 500 Internal Server Error
       2. 501 Not Implemented
12. REST API should be backward compatible as long as possible, in case of
    introducing breaking change, one of versioning methods should be used:
    1. URI versioning
       1. /api/v1/products
    2. Query string versioning
       1. /api/products?version=1
    3. Header versioning
       1. /api/products
       2. Header - Accepts-version: 1.0
    4. Media type content negotiation versioning
       1. /api/products
       2. Header - Accept: application/vnd.my-app.v1+json

Resource
---
1. Resource is a named information available via URI. It can be a document,
   image, video, text file, etc. REST uses different form of presentation of
   resources, and client can specify format in which Resource should be
   made available, for example JSON, XML, Text, Html etc.
2. Resource can be made available as single resource or collection of
   resources. Resources can also have relationship with each other, often
   used relation is “contained in” expressed by sub-collections.
3. REST usually provides set of methods that can be used to manipulate
   resources, like HTTP GET, POST, PUT, DELETE etc.

CRUD
---
1. CRUD stands for:
   1. Create
   2. Read
   3. Update
   4. Delete
2. those are basic operations of data store. All of those, or subset of them
   may be implemented in REST service.
3. When using HTTP as application layer in REST, following HTTP methods
   are used for implementation:
   1. Create -> HTTP POST / HTTP PUT
   2. Read -> HTTP GET
   3. Update -> HTTP PUT / HTTP PATCH
   4. Delete -> HTTP DELETE
4. Here are example conventions often used when implementing CRUD in
   REST.
   ![img.png](img.png)

Is REST secure
---------
1. REST as an architectural style of developing distributed applications,
   does not enforce any security rules or solutions on it’s own, so by default
   REST is not secured.
2. However, since it suggests layered approach to system design, security
   can be added later in the process
3. In Spring, this can be easily achieved by using Spring REST and Spring
   Security module.
4. To secure REST API, you can do following:
   1. Protect in-transit traffic by using HTTPS protocol
   2. Use some form of Authentication (Basic, JSON Tokens, ...)
   3. Use some form of Authorization (Spring Roles)

Is REST scalable and/or interoperable
--------------------
1. Scalability of RESTful Service is a result of developing software with
   following characteristics in mind:
   1. `Statelessness` - each request to the system, should be design in a way, for
      it to be processed without having to keep any state at the backend side,
      for example, we want to avoid keeping information in HTTP Session
      related to user conversation with the system, this way we can delegate
      request to any backend node that can process the request without having
      to introduce share state between nodes
   2. Layered Approach – layered approach to the system design means that
      we can introduce new parts of the system in a way for it to be
      transparent to the client, resulting in ability to change system without
      having to modify client, example of this can be introduction of
      Application Load Balancer, API Gateway, Security Layers, Web Application
      Firewall without having to change client at all
   3. Cacheability – allows to create response for repeatable requests, without
      having to process them on service side, caching is introduced to improve
      response time and to reduce load on the service
2. Scalability that can be achieved by statelessness of RESTful Service is especially visible in Microservice
      Architecture, that can be created with usage of Spring Cloud Components. Layered Approach allows
      introduction of additional components without changing client application.
      ![img_1.png](img_1.png)
3. REST Service is interoperable because:
   1. Access to REST Service and resources available by URIs is standardized
      and not coupled with any specific technology, allowing you to
      consume REST Service in any technology of choice, like JavaScript,
      Python, Java, C++ etc.
   2. Data for the requested resource can be sent to client in different
      formats specified by the client, in case of HTTP protocol this can be
      done with usage of Accept header, for example Accept:
      application/json or Accept: application/xml
   3. All CRUD operations can be handled with standardized approach, in
      case of RESTful Service implemented with HTTP protocol,
      standardized HTTP methods GET, PUT, PATCH, POST, DELETE
      are used

REST uses following HTTP methods:
--------------------
1. HTTP GET – used to implement Read Operations – fetch existing
   resource or list of resources
2. HTTP POST – used to implement Create Operations – adding new
   element
3. HTTP PUT – used to implement Create or Update Operations – Bulk
   Update or Update of existing resource
4. HTTP PATCH – partial update of existing resource of the system, for
   example, when wanting to update only First Name of Customer entity
5. HTTP DELETE – delete existing resource in the system
6. Example
   [Source code](IntroToRest)

HttpMessageConverter
------------------
1. HttpMessageConverter is an interface used by Spring to convert data between
   different formats.
2. REST client can specify expected format in which data should be retrieved by usage of
   `Accept Header, for example Accept: application/json.` Client can also
   specify format in which data will be send by usage of` Content-Type Header``, for
   example Content-Type: application/xml.`
3. Spring keeps list of HttpMessageConverters and for each request, data formats
   requested are being analyzed and cross referenced with methods implemented in
   Controllers. Whenever match is found Spring executes proper Controller
   Method and picks HttpMessageConverter to perform data conversion.
4. Request Mapping annotations contains produces and consumes fields which can be
   used to specify MediaType which method can handle.
5. Having method identified HttpMessageConverter is used to:
   1. Convert HttpInputMessage to desired Type
   2. Convert Type to HttpOutputMessage
6. HttpMessageConverter interface specifies contract for following behaviors:
   1. canRead - indicates whether given MediaType can be read by converter
   2. canWrite - indicates whether given MediaType can be written by converter
   3. getSupportedMediaTypes - returns supported list of MediaTypes
   4. read – Converts HttpInputMessage to Type
   5. write – Converts Type to HttpOutputMessage
7. [Source Code](MessageConveters/src/main/java/com/raghu/message/converter/config)

Is REST normally stateless?
--------------------
1. Yes, REST is normally stateless.
2. Statelessness is one of the basic constraints for Systems following RESTful
   Architecture.
3. Each request from client should contain all required information to handle the
   request.
4. If system is unable to process each request independently, because of state being
   required on server side, then such system is no longer RESTful System since it
   violates one of the basic principal of RESTful Architecture.
5. If some kind of state is required for the request, client should send this state
   with each request, however this state should not be kept on server side, for
   example, state should not be kept in HTTP session.

@RestController
----
1. class is a controller that will implement REST Service
   Endpoints, usually consuming and producing json

Difference between @Controller and @RestController
------
1. @Controller annotation indicates that class will be assigned with Controller
   role of MVC pattern, and usually it is expected from it to return Model and View
   that will be used to render response, with usage of configured template engine
   (Thymeleaf).
2. @RestController annotation indicates that class will be used to handle REST
   Service Endpoint requests, it is expected from it to correctly handle requests
   input and produce outputs in acceptable format like JSON/XML etc. Serialized
   output is sent directly to the client.
3. @RestController = @Controller + @ResponseBody
4. @ResponseBody annotation indicates that method return value should be
   bound to the web response body, it’s return value will be serialized into response
   in requested format.

@ResponseBody
----
1. @ResponseBody annotation is needed whenever you want spring to return
   serialized response of controller method return value, instead of returning model
   and view that will be used by template engine to produce response.
   @ResponseBody annotation bounds method return value to the web response
   body, produced by HttpMessageConverter.
2. @ResponseBody annotation can be used:
   1. On top of class
   2. On top of method
   3. On top of other annotations
3. @ResponseBody annotation is useful when implementing backend service API,
   for example REST Service. Usage of this annotation is not needed when using
   @RestController annotation, since this one already contains
   @ResponseBody annotation.

HTTP status return codes for a successful GET, POST, PUT or DELETE operation
------
1. GET
   1. 200 (OK) – when asked about existing resource for which content is returned
2. POST
   1. 201 (Created) – when new resources was created
   2. 200 (OK) – when some processing was executed but resources were not created
   3. 204 (No Content) – when some processing was executed, but no response is retrieved
3. PUT
   1. 201 (Created) – when new resources was created
   2. 200 (OK) – when resources was updated, and updated content is returned
   3. 204 (No Content) – when resource was updated, and no content is returned
   4. DELETE
      1. 204 (No Content) – when resource was successfully deleted
   5. In case of asynchronous operations, 202 (Accepted) may be retuned with Location
      header indicating URI that can be used for pooling
4. [Source Code](HttpStatusReturnCodes/src/main/java/com/raghu/http/controller/CustomersController.java)

@ResponseStatus
---------------
1. @ResponseStatus annotation is required whenever you want to override
   default HTTP status returned from controller handler method.
2. This can be achieved by applying @ResponseStatus annotation to:
   1. Controller class
   2. Controller method
   3. Exception being thrown from controller
3. @ResponseStatus annotation allows you to set:
   1. HTTP Status Code
   2. Reason message to be used in response in case of error
4. [Source Code](ResponseStatusAnnotation/src/main/java/com/raghu/reponse/status/controller) 

@RequestBody
---------
1. @RequestBody annotation is needed whenever you want to bind web request body
   to controller parameter. HttpMessageConverter is used to convert content of
   request body. Optionally can be used with @Valid annotation to invoke automatic
   Bean Validation (JSR 303/JSR 380). Also, @RequestBody annotation allows you to
   set required field to indicate if parameter is required or not.
2. @RequestBody annotation can be used:
   1. On top of controller method parameter
   ![img_2.png](img_2.png)
3. Example for @valid
   ```java
       @PutMapping("/customers")
    public ResponseEntity putCustomers(@RequestBody @Valid Collection<Customer> customers) {
        customersDao.deleteAll();
        Iterable<Customer> updatedCustomers = customersDao.saveAll(customers);

        return ResponseEntity.noContent().build();
    }
   ```

@ControllerAdvice
-------------
1. @ControllerAdvice is an annotation in Spring Framework that allows you to handle exceptions globally across multiple controllers in your application. It centralizes exception handling logic, making it easier to manage and maintain error handling. This annotation is commonly used in Spring MVC applications.
2. Example
   ```java
   public class GlobalExceptionHandler {

    // Define a method to handle a specific exception
    @ExceptionHandler(YourCustomException.class)
    public ResponseEntity<String> handleYourCustomException(YourCustomException ex) {
        // Create a custom error response or handle the exception as required
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Add more exception handler methods for different exceptions if needed
   }
   
   public class YourCustomException extends Exception {
    // Custom exception implementation
    }
   
   @RestController
   public class YourController {

    @GetMapping("/example")
    public ResponseEntity<String> exampleEndpoint() throws YourCustomException {
        // Some code that may throw YourCustomException
        throw new YourCustomException("This is a custom exception example.");
    }
   }

    ```
   
3. When you make a request to /example, it will trigger the exception, and Spring will automatically handle it using the GlobalExceptionHandler. The response will include the error message and the HTTP status code specified in the handleYourCustomException method.

4. Keep in mind that @ControllerAdvice can also be used to handle other types of exceptions, not just custom exceptions. By using different @ExceptionHandler methods for different exception types, you can have more fine-grained control over how each type of exception is handled in your application.
5. Default Exception Handling: Spring provides a default exception handling mechanism that will handle uncaught exceptions. When an uncaught exception occurs in a controller method, Spring will automatically catch it and produce an error response based on the exception type.

Summary of Controller
----------------
1. Controller can be defined in one of following ways:
   1. @Controller – Spring MVC Controller, should return view name and model
   2. @RestController – REST API Controller, @RestController =
      @Controller + @ResponseBody
2. Controller mapping can be defined with usage of one of following annotations:
   1. @RequestMapping
   2. Composed annotation:
      1. @GetMapping
      2. @PostMappping
      3. @PutMapping
      4. @PatchMapping
      5. @DeleteMapping
3. Request parameter body can be mapped with usage of:
   1. @RequstBody
      1. Additionally @Valid annotation can be used to trigger Bean Validation
      2. Response can be bound to web response by:
         1. Usage of @ResponseBody annotation on top of @Controller or
            @Controller method
         2. Usage of @RestController annotation
      3. Custom HTTP status can be provided for controller methods and exception with
         usage of @ResponseStatus annotation.

4. Request and URI parameters can be accessed with:
   1. @RequestParam - Servlet request parameters
   2. @PathVariable – access to URI template variables
   3. @MatrixVariable - access to name-value pairs in URI path segments,
      allows mapping variables from requests like /employees/id=1;name=John
   4. @CookieValue - bind the value of an HTTP cookie to a method argument in
      a controller
   5. @RequestHeader - access request header values or all header key and
      values when binding against a Map
5. Calls to controller can be intercepted, and custom exception handling can be
   implemented with one of:
   1. @ExceptionHandler – when applied at controller level method, acts as
      controller level exception handler
   2. @ExceptionHandler – when applied at controller level method, acts as
      controller level exception handler
   ![img_3.png](img_3.png)


Do you need Spring MVC in your classpath
--------------------
1. Yes, you need Spring MVC on classpath for REST API to work correctly.
2. Spring MVC is not required for compilation time, but is required during runtime.
3. REST API in Spring is build with usage of annotations like:
   1. RestController
   2. RequestBody
   3. RequestMapping
   4. ........
4. All of those annotations are available in spring-web module which is not
   dependent on spring-webmvc module.
5. However for request to be mapped to RestController, DispatcherServlet
   has to be initialized, which is part of spring-webmvc module. This is the reason
   why spring-webmvc module is required on classpath.

Spring Boot starter
--------------
1. To create Spring REST application, use Spring Boot Web Starter.
   ```xml
   <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
   </dependency>

     ```
2. Spring Boot Web Starter Maven pom.xml has following description:
   1. Starter for building web, including RESTful, applications using Spring MVC.
      Uses Tomcat as the default embedded container.
3. Spring Boot Web Starter will automatically include:
   1. spring-web
   2. spring-webmvc
   3. spring-boot-starter-json
      1. jackson-databind
      2. ...
   4. spring-boot-starter-tomcat
      1. tomcat-embed-core
   5. ...........

RestTemplate
--------------
1. RestTemplate is a synchronous HTTP client wrapper to perform HTTP requests. It exposes simple
   API over underlying HTTP client libraries:
   1. JDK HttpURLConnection
   2. Apache HttpComponents
   3. OkHttp
   4. ......
2. It supports:
   1. Automatic Object Serialization/Deserialization:
      1. JAXB2, Jackson2, GSON, JSONB
   2. Automatic HttpMessageConverters registration
   3. URI Templates
   4. Exchange Operation – send and retrieve, for example HTTP POST that takes input object and
      returns object
   5. HTTP GET for Object...
3. RestTemplate has following advantages:
   1. Simplicity
   2. Automatic Object Serialization/Deserialization
   3. High-Level API allows you to focus on business side of operations
   4. Provides support for common HTTP GET, POST, PUT, PATCH, HEAD, OPTIONS, DELETE
      operations
   5. Flexibility:
      1. Allows Custom Error Management with usage of ResponseErrorHandler
   6. Extendibility:
      1. Allows to register custom HttpMessageConverters
      2. Allows to register custom ClientHttpRequestFactory
   7. URI Templates Support
      1. Minimal amount of code required
      2. Automatic content type detection
   8. Because of it’s simplicity, REST Template is often used in testing code, however it can be
      used as general purpose HTTP Client.
4. Example
   ```java
   @Controller
    public class CustomersController {
    
        @Value("${app.backend.url}")
        private String backendUrl;
        private RestTemplate restTemplate = new RestTemplate();
    
        @GetMapping("/customers")
        public ModelAndView index() {
            ResponseEntity<Customers> responseEntity = restTemplate.getForEntity(backendUrl + "/api/customers", Customers.class);
    
            if (responseEntity.getStatusCode() == HttpStatus.OK)
                return new ModelAndView("customers", "customers", responseEntity.getBody().getCustomers());
            else
                throw new IllegalStateException(String.format("Unable to list customers, received status %s", responseEntity.getStatusCode()));
        }
    
        @GetMapping("/customers/create")
        public ModelAndView create() {
            return new ModelAndView("customer-create", "customer", new Customer());
        }
    
        @PostMapping("/customers/create")
        public String create(@ModelAttribute Customer customer, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "customer-create";
            } else {
                ResponseEntity<Customer> responseEntity = restTemplate.postForEntity(backendUrl + "/api/customers", customer, Customer.class);
    
                if (responseEntity.getStatusCode() != HttpStatus.OK)
                    throw new IllegalStateException(String.format("Unable to create customer, received status %s", responseEntity.getStatusCode()));
    
                return "redirect:/customers";
            }
        }
    
        @GetMapping("/customers/delete/{id}")
        public String delete(@PathVariable Integer id) {
            restTemplate.delete(backendUrl + "/api/customers/{id}", id);
    
            return "redirect:/customers";
        }
    }

    ```
5. RestTemplate API can be categorized by HTTP request type, below is a list of commonly used
   operations:
   1. Generic (flexible)
      1. exchange
         1. executes HTTP request against given URI, sends
            request HttpEntity and returns the response as
            ResponseEntity
      2. execute
         1. executes HTTP request against given URI,
            prepares the request with the RequestCallback
            and reads response with ResponseExtractor
      3. HTTP GET
         1. getForObject
            1. Returns the entire HTTP response, including the response headers, status code, and the response body.
         2. getForEntity
            1. Returns only the response body, not the entire HTTP response. The response body will be automatically deserialized into the specified type.
      4. HTTP HEAD
         1. headForHeaders
            1. It returns a HttpHeaders object containing the HTTP headers received in the response.
            2. he HEAD method is similar to the GET method but does not retrieve the actual content of the resource; it is used to obtain metadata about the resource, such as headers, without transferring the entire response body.
      5. HTTP POST
         1. postForLocation
            1. It returns a java.net.URI object representing the location (URL) of the newly created resource.
         2. postForObject
         3. postForEntity
      6. HTTP PUT
         1. put
      7. HTTP PATCH
         1. patchForObject
      8. HTTP DELETE
         1. delete
      9. HTTP OPTIONS
         1. optionsForAllow
            1. he HTTP OPTIONS method is used to request information about the communication options available for a particular resource or server like http methods get,post,put..
6. References
   1. [Link 1](https://docs.spring.io/spring-framework/docs/5.1.6.RELEASE/javadoc-api/org/springframework/web/client/RestOperations.html)
   2. [link 2](https://docs.spring.io/spring-framework/docs/5.1.6.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html)
























