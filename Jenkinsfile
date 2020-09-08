pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
                script{
                    try{
                        powershell "docker stop \$(docker ps --filter name=hic -qa)"
                        powershell "docker container rm \$(docker ps --filter name=hic -qa)"
                    }
                    catch(exc){
                        echo 'no container'
                    }
                    try {
                        powershell "docker image rm \$(docker image ls --filter reference=hic -qa)"
                    }
                    catch(exc){
                        echo 'no image'
                    }
                }
            }
        }
        stage('build') {
            steps {
                bat "mvn clean package"
            }
        }
        stage('sonar') {
            steps {
                bat "mvn sonar:sonar \
                      -Dsonar.projectKey=HIC \
                      -Dsonar.host.url=http://localhost:1001 \
                      -Dsonar.login=8dbd1aa5aaecbf60711560ff1c458d4e0e3597fe"
            }
        }
        stage('docker') {
            steps {
                script{
                    image = docker.build("hic:${env.BUILD_ID}")
                    image.run("-p 7700:8000 --name hic.${env.BUILD_ID} --restart=always")
                    image.run("-p 7800:8000 --name hic.${env.BUILD_ID} --restart=always")
                    image.run("-p 7900:8000 --name hic.${env.BUILD_ID} --restart=always")
                    image.run("-p 8000:8000 --name hic.${env.BUILD_ID} --restart=always")
                }
            }
        }
    }
}
