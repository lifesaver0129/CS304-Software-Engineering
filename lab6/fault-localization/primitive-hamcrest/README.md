# primitive-hamcrest
[![Build Status](https://travis-ci.org/inf295uci-2015/primitive-hamcrest.svg?branch=master)](https://travis-ci.org/inf295uci-2015/primitive-hamcrest)
[![Coverage Status](https://coveralls.io/repos/inf295uci-2015/primitive-hamcrest/badge.svg?branch=master)](https://coveralls.io/r/inf295uci-2015/primitive-hamcrest?branch=master)

Basic hamcrest matchers with Java Primitives in mind.

## Installation Instructions

* Install the mvn package to your local repository.  
```
    git clone https://github.com/inf295uci-2015/primitive-hamcrest.git  
    cd primitive-hamcrest  
    mvn test # just to make sure that everything works  
    mvn install # installs this to your local repository  
```
* Include the following dependency in your pom.xml
```
    <dependency>
      <groupId>org.spideruci.hamcrest</groupId>
      <artifactId>primitive-hamcrest</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
```

## Features

Currently the library supports veryifying if a primitive array contains all of the given primitive elements for the following primitive types:
- [x] int array
- [x] float array
- [x] byte array
- [ ] short array
- [ ] char array
- [x] boolean array
- [ ] double array
- [ ] long array
