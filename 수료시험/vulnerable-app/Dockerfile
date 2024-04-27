# 베이스 이미지 설정
FROM openjdk:8-jdk-alpine as build

# Maven 버전 설정
ARG MAVEN_VERSION=3.6.3
ARG USER_HOME_DIR="/root"

# Maven 설치를 위한 환경 변수 설정
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Maven 설치
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && wget -O /tmp/apache-maven-$MAVEN_VERSION-bin.tar.gz http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
  && tar -xzf /tmp/apache-maven-$MAVEN_VERSION-bin.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven-$MAVEN_VERSION-bin.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# 프로젝트 파일 복사
COPY src /home/app/src
COPY pom.xml /home/app

# Maven을 사용한 프로젝트 빌드
RUN mvn -f /home/app/pom.xml clean package

# 최종 실행을 위한 새로운 스테이지
FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-cp", "/app/classes:/app/libs/*", "org.example.Main"]

