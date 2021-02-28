pipeline {
    agent any

    tools {
        maven "maven-3.6.0"
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/vishydeveloper/spring-boot.git'
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

        }
    }
}