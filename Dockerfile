#Dockerfile
FROM mozart/grails:3
MAINTAINER Ming Ma  <mingliang.ma@gmail.com>

# Copy App files
COPY . /app

# Run Grails dependency-report command to pre-download dependencies but not
# create unnecessary build files or artifacts.
RUN grails dependency-report --stacktrace

# Set Default Behavior
ENTRYPOINT ["grails"]
CMD ["run"]
