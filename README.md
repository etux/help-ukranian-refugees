# Project

Project to implement the matching of guests and hosts, with special attention to verification of matches.

## Get it, run it:

In order to build this project, first clone the repository:
```
git clone <url>
```
And then run:
```
./docker-compose up -d
```

## Work it (will keep on building the software as you make chanegs):
```
./gradle -t check
```

## Run it outside of docker (make sure db is available, e.g. `docker-compose up db`):
```
./gradle bootRun
```
