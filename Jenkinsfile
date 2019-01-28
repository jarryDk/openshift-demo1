pipeline {
    agent any
    stages {
      stage('Mvn build') {
          steps {
              sh 'mvn -B clean verify -Popenshift'
          }
      }

      stage('Deploy') {
        steps {
          sh 'oc login -usystem:admin'
          sh 'oc project shiftdemo'
          sh 'oc start-build demo1 --from-file=target/ROOT.war'
        }
      }

    }
}
