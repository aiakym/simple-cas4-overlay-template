SET CATALINA_HOME=D:\apache-tomcat-8.0.22
CALL %CATALINA_HOME%\bin\shutdown.bat

CALL mvn -DskipTests clean install

RMDIR /S /Q D:\apache-tomcat-8.0.22\work\
RMDIR /S /Q D:\apache-tomcat-8.0.22\temp\
RMDIR /S /Q D:\apache-tomcat-8.0.22\webapps\sso
RMDIR /S /Q D:\apache-tomcat-8.0.22\logs
RMDIR /S /Q D:\etc\cas
DEL /Q D:\apache-tomcat-8.0.22\webapps\sso.war
XCOPY /s D:\sources\simple-cas4-overlay-template\target\sso.war D:\apache-tomcat-8.0.22\webapps\
MD D:\etc
XCOPY /s D:\sources\simple-cas4-overlay-template\etc\cas.properties D:\etc\cas\
XCOPY /s D:\sources\simple-cas4-overlay-template\etc\log4j.xml D:\etc\cas\

CALL %CATALINA_HOME%\bin\catalina.bat jpda start