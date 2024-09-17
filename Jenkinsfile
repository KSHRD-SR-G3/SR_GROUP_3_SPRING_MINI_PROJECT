pipeline {
    agent any 
    tools {
        maven 'maven'
    }
    stages {

        stage("build") {
            steps {
              sh ' mvn clean install '
              sh ' docker build -t springboot_jenkins . '
            }
        }

        stage("deploy") {
            steps {
                sh 'docker start springboot_jenkins || docker run --name springboot_jenkins -d -p 8081:8080 springboot_jenkins '
                sh ' docker ps '
                echo '🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀'
                echo '🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀🚀'
            }
        }
    }
}
