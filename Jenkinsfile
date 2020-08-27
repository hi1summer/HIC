pipeline {
  agent any
  stages {
    stage('git') {
      steps {
        git(url: 'git@github.com:hi1summer/HIC.git', branch: 'dev', credentialsId: 'gonick@163.com')
      }
    }

  }
}