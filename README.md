Eureka
======

Eureka is a RESTful (Representational State Transfer) service that is primarily used in the AWS cloud for the purpose of
discovery, load balancing and failover of middle-tier servers. It plays a critical role in Netflix mid-tier infra.

Building
--------
The build requires `java8` because of some required libraries that are `java8` (`servo`), but the source and target
compatibility are still set to `1.7`. Note that tags should be checked out to perform a build.

Docker
------------
If you desire you can use this project using a Docker container.