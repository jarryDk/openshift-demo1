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
          sh 'oc project shiftdemo start-build demo1 --from-file=ROOT.war'
        }
      }

    }
}
