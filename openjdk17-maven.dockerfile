FROM openjdk:17.0.1-jdk 

WORKDIR /app

COPY maven.tar.gz .

RUN set -ex; \
    tar xzf maven.tar.gz -C /opt; \
    install /opt/apache-maven-3.8.4/bin/mvn /usr/bin/mvn; \
    echo export M2_HOME=/opt/apache-maven-3.8.4 > /etc/profile.d/maven.sh; \
    echo export PATH=${M2_HOME}/bin:${PATH} >> /etc/profile.d/maven.sh; \
    source /etc/profile.d/maven.sh;



