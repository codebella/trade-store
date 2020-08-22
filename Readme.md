## Trade Store ##

The modules in the repository are following CQRS design pattern.
There are set of command defined:
* CreateCommand
* UpdateCommand
* MatureCommand

Through these command, the api allows trade store modification.

Through Query interface support querying in below way:
* TradeId only - This will return max version trade from the trade store
* TradeId, Version - This will return the match TradeResponse if found, else 404
* In general 202 status is returned because the processing is passed on to command handlers via kafka topic

Also, The web application is constructed using pragmatic restful approach. Below are available endpoints:
* ```/trades/trade/{tradeId}?{version}``` - GET/POST/PUT
* ```/trades/mature``` - PATCH - Matures all the trades which has maturity date less than ```Instant.now()```          

* Kafka in-memory messaging broker to handle demo
* Added Config Server, Discovery Server, Proxy and Gateway

## Current state of application is broken ##
###TODO:###
Need to add business logic to model event driven mechanism.