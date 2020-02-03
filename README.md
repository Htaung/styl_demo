Access to client

http://localhost:8080/random_number


http://localhost:8080/sort_number

Api List

Route => http://localhost:8080/sort
Method => POST

Request Header
===============
Accept => application/json
ApiKey => @tyl@Api
Nonce => timestamp#randomAlphaNumeric
Signature => (SignatureData = Request-Body || QueryParams || Nonce
              Signature = Base64(HMAC_SHA256(Secret-Key, SigatureData)))
              

Request Body
===========
[33,2,232,3423,3423,3242]

Response Body
=============
Success
=======
[2,33,232,3242,3423,3423]



Req
Route => http://localhost:8080/random?max=100
Method => GET

Request Header
===============
Accept => application/json
ApiKey => @tyl@Api
Nonce => timestamp#randomAlphaNumeric
Signature => (SignatureData = Request-Body || QueryParams || Nonce
              Signature = Base64(HMAC_SHA256(Secret-Key, SigatureData)))
              
Request Body
===========



Response Body
=============
Success
=======
74

