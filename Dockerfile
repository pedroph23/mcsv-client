FROM postgres	

ENV POSTGRES_PASSWORD=docker
ENV POSTGRES_USER=builders
ENV PGDATA=/var/lib/postgresql/data/builders_db/

RUN PORT=5432

CMD gunicorn --bind 0.0.0.0:$PORT wsgi

EXPOSE $PORT
