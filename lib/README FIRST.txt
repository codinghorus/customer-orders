How to install:

1. From the current directory run the following command. Note: It assumes that Maven 2.0 or later is installed
mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar

2. From the <auth> directory copy the appropiate dll to the path where the JDK is installed.