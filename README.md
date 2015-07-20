# turbo-adventure

[![Build Status](https://travis-ci.org/atomfrede/turbo-adventure.svg?branch=master)](https://travis-ci.org/atomfrede/turbo-adventure)
[![Codecov](https://img.shields.io/codecov/c/github/atomfrede/turbo-adventure/master.svg)](http://codecov.io/github/atomfrede/turbo-adventure?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/5522676e971f7847ca0004a5/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5522676e971f7847ca0004a5)
[![Stories in Ready](https://badge.waffle.io/atomfrede/turbo-adventure.svg?label=ready&title=Ready)](http://waffle.io/atomfrede/turbo-adventure)
[![Join the chat at https://gitter.im/atomfrede/turbo-adventure](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/atomfrede/turbo-adventure?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
![GitHub license](https://img.shields.io/github/license/atomfrede/turbo-adventure.svg?style=flat)

Karaoke Pair Builder Server and Services build with dropwizard.io

## What is this?

More or less this is a project to evaluate the capabilities of dropwizard.io as a micorservice framework. The purpose of this project is to provide a simple website that generate pairs of employees and a song they are supposed to sing along with the karaoke machine.

### Local development

#### MongoDB

``./mongod --dbpath ~/mongo/data/db``

or use the official mongo db docker container

#### turbo-adventure

``mvn package``
``java -jar target/catalog-page-generator-0.1-SNAPSHOT.jar server``

Or run the ``main`` method in ``KaraokeApplication.java`` in the IDE of your choice.

## Docker

The application is available at docker hub:

``docker pull atomfrede/turbo-adventure``

``docker run –p 3434:3434 atomfrede/turbo-adventure``

## License


    Copyright 2015

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
