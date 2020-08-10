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
* In general 422 status is returned when data cannot be saved

Also, The web application is constructed using pragmatic restful approach. Below are available endpoints:
* ```/trades/trade/{tradeId}?{version}``` - GET/POST/PUT
* ```/trades/mature``` - PATCH - Matures all the trades which has maturity date less than ```Instant.now()```          


### TODO: Future Enhancements ###

* Adding the messaging capability to ingest huge data volume
* Adding events to handle the lifecycle activities like Maturity/Expiry