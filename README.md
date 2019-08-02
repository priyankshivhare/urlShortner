# urlShortner
This is a simple Springboot URL shortner application

Exposed routes
1. POST /account
params: accountId (Int)

For registering account
Response:
Password: String

2. POST /register
params: accountId (Int)
url (String)
Header: Authorization (Password)

For registering short url
Response: shortUrl (String)

3. GET /{shortUrl}
Eg: http://localhost:8080/{shortUrl}
Will redirect to actual url

4. GET /statistic/{accountId}
Will get statistics of that particular account ID
