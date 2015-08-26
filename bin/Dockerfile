FROM java:openjdk-8-jdk

EXPOSE 3434

ADD src/main/resources/karaoke.yml karaoke.yml
ADD target/karaoke-server-0.1-SNAPSHOT.jar karaoke-server.jar

CMD java -jar karaoke-server.jar server karaoke.yml
