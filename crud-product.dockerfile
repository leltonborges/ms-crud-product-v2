FROM openjdk17-maven:v1

ENV PORT=${PORT:-8081}

WORKDIR /app

VOLUME /tmp

COPY . .

EXPOSE ${PORT}

ENV HOST_DB=crud-product
ENV DB_PORT=3306
ENV DB_NAME=crud-product

RUN set -ex; \
    /opt/apache-maven-3.8.4/bin/mvn clean package -DskipTests; \
    cp target/*.jar ./crud-product.jar;

CMD [ "java","-DPORT","=","$PORT","-jar","crud-product.jar" ]