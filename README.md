Application prerequisites:
1)Postgres installed in a docker environment (will be started with the help of 
the docker-compose file from the root directory of this project)

Setup the application in your environment by following the steps from below:
1) Clone the application from github to a local drive
2) Modify the application.properties variables to your environment 
setup if needed (ex. server.port), including authentication information
3) Modify the docker-compose.yaml file based on the parameters from  the 
application.properties. Make sure both files contain the same information for postgres config.
For the development environment you can leave the defaults from the downloads for both 
application properties and yaml file if there is no overlap in your environment.
For QA and Production please enter the corresponding configurations.(user, password)

To run the application:
1) Run "docker-compose up -d" to start the postgres database in a 
docker environment, using the file provided in the root directory, 
from the command line
2) Start the application from IntelliJ
