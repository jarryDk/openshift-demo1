pipeline {
    agent none
    stages {
      stage('Mvn build') {          
          steps {
              sh 'mvn -B clean verify'
          }
      }
    }
}
