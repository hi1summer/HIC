pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
                script{
                    try{
                        powershell "docker stop \$(docker ps -qa)"
                        powershell "docker container rm \$(docker container ls -qa)"
                        powershell "docker image rm \$(docker image ls -q -f dangling=true)"
                    }
                    catch(exc){
                        echo 'no container or image'
                    }
                }
            }
        }
        stage('git') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'dev', credentialsId: 'bb001980-a8bf-4143-a1fa-3afcaba854fd', url: 'git@github.com:hi1summer/HIC.git'
            }
        }
        stage('build') {
            steps {
                bat "mvn clean package"
            }
        }
        stage('docker') {
            steps {
                script{
                    docker.build("hic:${env.BUILD_ID}").run("-p 8000:8080 -t hic -d")
                }
            }
        }
    }
}
