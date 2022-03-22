* Project

Project to implement the matching of guests and hosts, with special attention to verification of matches.

* Build it

In order to build this project, first clone the repository:
```
git clone <url>
```
Execute to build project:
```
./gradlew bootJar
```
Build a docker image:
```
./docker build -t hosting-image .
```
And last but not least, run:
```
./docker-compose up -d
```
