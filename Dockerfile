FROM postgres	

ENV POSTGRES_PASSWORD=docker
ENV POSTGRES_USER=builders
ENV PGDATA=/var/lib/postgresql/data/builders_db/

EXPOSE 80
