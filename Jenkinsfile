pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
                bat "docker stop \$(docker ps -qa)"
                bat "docker container rm \$(docker container ls -qa)"
                bat "docker image rm \$(docker image ls -q -f dangling=true)"
            }
        }
        stage('git') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'dev', credentialsId: 'bb001980-a8bf-4143-a1fa-3afcaba854fd', url: 'git@github.com:hi1summer/HIC.git'
            }
        }
        stage('Build') {
            steps {
                bat "mvn clean package"
            }
        }
        stage('docker') {
            steps {
                bat "docker build -t hic ."
                bat "start docker run -p 8000:8080 -t hic -d"
            }
        }
    }
}
