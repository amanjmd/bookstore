Functional Details 
This application is a book store where new books can be added and bought. 
For adding a book 
    The price , Isbn , title and author are mandortory fields . WHen a new book is added to  the book_entity table  , a corresponding Product or a Copy of the book is added to the product_entity table with sold flga as false . If the Book with the same isbn is already present , only a new row for the product is added to the product_entity table. 


Technical Details:


Adding New Book
    Url : /books
    Request Format : JSON request
    Method: Post
    Sample Request :
    {
        "ISBN":12345,
        "title":"title1",
        "author":"author1",
        "price":100.00
    } 
    Response Format : JSON

Buying New Book
    Url : /Buy/{isbn}
    Method : Put
    Request Format : JSON request
    
Search book 
    
    ISBN
        Url : /isbn/{isbn number}
        Method : Get
        Response Format : JSON
        
    Author
        Url : search/author/{Partial/Full <Author name>}
        Method : Get
        Response Format : JSON

    Title
        Url : search/title/{Partial/Full <Book title>}
        Method : Get
        Response Format : JSON

Media Coverage
        Url : search/coverage/{Partial/Full title}
        Method : Get
        Response Format : JSON


=====================================================================================================================================
Package Structure
    Endpoints 
        These define the Application Endpoints for various functionalities to be provided and the the validators to perform validations of raw requests. 
        This package also contains the Domain Objects(DO)  to be transafered between client and server in any format.
    
    Business Handler
        This package contains the POJOs for business objects and the main business handler to handle to the request from endpoint .

    Data Handler
        This package contains all the persistance and  database configuration related classes.


All these separate Modules has there own POJO classes for processing the data. 
    
    End Point : ProductDO, BookDO

    Business Handler : ProductBO, BookBO

    Data Handler : ProductEntity, BookEntity

    These POJOs are responsible for the data processing wthin Module . And when data has to be transafered , it takes the following flow pattern

    client -> End Point (DOs)-> business Handler (BOs)-> Data Handler(Entities) -> database (and back to the endpoint taling the same pathway reverse)

    To aid this data transfer , every POJO has "valueOf" methods to convert the data to its own internal required format


Such modular also aid in mocking the data handler at times of Unit testing . 

======================================================================================================================================
Database Structure

This Application has 2 Entities 

    book_entity : This table contains the details about the a Book with a Unique ISBN .

        create table book_entity (isbn integer not null, author varchar(255), price decimal(19,2), title varchar(255), primary key (isbn))

    product_entity : This table contains the details about copies of the product that is actually sold or in stock. The Products that are not sold has the "sold" flag to be false.

        create table product_entity (product_id integer not null, isbn integer, sold boolean, primary key (product_id))

    The Isbn Number is the reference to the Book Entity table . and Multiple products can refer to one Isbn Number in book_entity table.


******************