pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "mvn clean install"
            }
        }
        stage('Jacoco Report'){
        steps{
                jacoco exclusionPattern: '**/*Test*,**/model/**,**/security/SecurityConfiguration**,**/SpringFoxConfig**,**/*JwtRequestFilter*.class,**/*DBinit*.class,**/*UserPrincipalDetailsService*.class', maximumInstructionCoverage: '30'
                input 'Deploy to Dev?'
            }
        }

        stage('Deploy DEV') {
            steps{
                sh label: '', script: 'git remote -v'
                withCredentials([usernamePassword(credentialsId: '0e221db1-6a54-4ae9-98b0-80d56012c59b', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                    sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@git.heroku.com/at-sso-ex.git master')
                }
            }

        }

        stage('Prepare to QA') {
            steps {
                sh "mvn clean install"
                input 'Deploy to QA?'
            }
        }

        stage('Deploy QA') {
            steps {
                sh label: '', script: 'git remote -v'
                withCredentials([usernamePassword(credentialsId: '0e221db1-6a54-4ae9-98b0-80d56012c59b', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                    sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@git.heroku.com/at-sso-qa.git master')
                }
            }
        }
    }
}
