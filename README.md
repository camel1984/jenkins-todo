
# command
gradle web:jettyRun

gradle bintrayUpload -PbintrayUser=wangshuaijie -PbintrayApiKey=apiKey


start sonar servier:

/Users/shuaijie/server/sonarqube-7.8/bin/macosx-universal-64/sonar.sh start

http://127.0.0.1:9000 to access sonar webpage.

gradle repository:sonarqube

jenkins:
wangshuaijie/123456

export SOURCE_BUILD_NUMBER=100

gradle web:localFunctionalTest


gradle build -Penv=test

gradle -Penv=test web:smokeTests

gradle -Penv=test web:remoteFunctionalTest

this verison only works with gradle 5.1:


Following is a list of change to trigger build:
trigger 0000
trigger 0001
trigger 0002

------------------------------------------------------------
Gradle 5.1
------------------------------------------------------------

Build time:   2019-01-02 18:57:47 UTC
Revision:     d09c2e354576ac41078c322815cc6db2b66d976e

Kotlin DSL:   1.1.0
Kotlin:       1.3.11
Groovy:       2.5.4
Ant:          Apache Ant(TM) version 1.9.13 compiled on July 10 2018
JVM:          1.8.0_231 (Oracle Corporation 25.231-b11)
OS:           Mac OS X 10.14.6 x86_64
