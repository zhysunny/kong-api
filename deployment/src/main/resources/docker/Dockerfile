FROM centos7/java:1.0.0
MAINTAINER zhysunny

ARG BASE_DIR=/opt/app
WORKDIR ${BASE_DIR}

ARG VERSION=1.0.0

ADD kong-api-${VERSION}.tar.gz ${BASE_DIR}

CMD [ "sleep", "36000" ]