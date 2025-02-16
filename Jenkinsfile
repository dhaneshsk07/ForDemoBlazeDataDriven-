pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/dhaneshsk07/ForDemoBlazeDataDriven-.git'
        PROJECT_DIR = 'DemoBlazeDataDriven14022025'
        POM_PATH = "C:/Users/dhane/eclipse-workspace/DemoBlazeDataDriven14022025/pom.xml"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: "${REPO_URL}"
            }
        }

        stage('Build') {
            steps {
                sh "mvn -f ${POM_PATH} clean compile"
            }
        }

        stage('Test') {
            steps {
                sh "mvn -f ${POM_PATH} test"
            }
        }

        stage('Package') {
            steps {
                sh "mvn -f ${POM_PATH} package"
            }
        }

        stage('Post-Build Report') {
            steps {
                //junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: '**/test-output/extent-Reports/*.html', allowEmptyArchive: true
            }
        }
        
        stage('Publish Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'test-output/extent-Reports',
                    reportFiles: 'extent-report.html',
                    reportName: 'Extent Report',
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }
        }
    }

    post {
        always {
            echo 'Job completed!'
            //archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/test-output/extent-Reports/*.html', allowEmptyArchive: true
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
