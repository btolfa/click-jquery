Click-jQuery
============

Building with ANT
-----------------
1. build Click from trunk, and copy click.jar and click-extras.jar to /click-jquery/lib
2. run
 > ant get-deps
   -> to get the required dependencies
3. run
 > ant javadoc
   -> to build the javadocs
4. run
 > ant build-examples
   -> to build click-jquery.jar and click-jquery-examples.war

Building with Maven
-------------------
1. Since Click-jQuery is using Click directly from trunk, you need to build it
   Click from trunk and install in you local repo, using:
     mvn install:install-file -Dfile=c:\click-2.3.0-M1.jar -DgroupId=org.apache.click -DartifactId=click -Dversion=2.3.0-M1 -Dpackaging=jar
     mvn install:install-file -Dfile=c:\click-extras-2.3.0-M1.jar -DgroupId=org.apache.click -DartifactId=click-extras -Dversion=2.3.0-M1 -Dpackaging=jar
2. run
  > cd click-jquery
3. run
  > mvn install

The result should be in the 'traget' directory.