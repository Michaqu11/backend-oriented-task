﻿# backend-oriented-task

## The goal of applicaiton is to query data from the Narodowy Bank Polski's public API and return relevant information from them.

## DEMO: https://youtu.be/lFTqD4rXlFI

## Tech stack:
- Vue: 3.2.13
- Spring Boot: 3.0.6


## Run Application:
```
docker-compose up
```
### frontend project is running on 
```
http://127.0.0.1:9000
```

### Endpoints:
- Average exchange rate:
```
http://localhost:8080/exchanges/{currency-code}/{date}
```
Required date format: RRRR-MM-DD

- Max and min average value:
```
http://localhost:8080/exchanges/a/{currnency}/last/{topCount}
```
- Major difference between the buy and ask rate:
```
http://localhost:8080/exchanges/c/{currnency}/last/{topCount}
```



 
