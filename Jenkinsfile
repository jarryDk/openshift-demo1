pipeline {
    agent any
    stages {
      stage('Mvn build') {
          steps {
              sh 'mvn -B clean verify'
          }
      }
    }
}
