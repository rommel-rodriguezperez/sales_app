from tomcat:10.1-jdk17-temurin-focal
# from tomcat:9.0-jdk17-temurin-focal

COPY server/server.xml $CATALINA_HOME/conf/server.xml


COPY ./build/libs/webapp.war $CATALINA_HOME/webapps

# COPY DemoApp.war /tmp/demoapp/
# RUN unzip DemoApp.war -d $CATALINA_HOME/webapps
# COPY DemoApp.war /tmp/demoapp/

# COPY DemoApp.war $CATALINA_HOME/webapps
# WORKDIR $CATALINA_HOME/webapps
# RUN jar -xvf DemoApp.war && rm DemoApp.war
# WORKDIR $CATALINA_HOME
