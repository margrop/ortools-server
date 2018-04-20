FROM tomcat:8-jre10

MAINTAINER "Xu Cheng <xucheng@ymq.me>"

COPY ./target/ortools-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo Asia/Shanghai > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata
