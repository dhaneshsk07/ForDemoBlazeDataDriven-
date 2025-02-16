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
                bat "mvn -f ${POM_PATH} clean compile"
            }
        }

        stage('Test') {
            steps {
                bat "mvn -f ${POM_PATH} test"
            }
        }

        stage('Package') {
            steps {
                bat "mvn -f ${POM_PATH} package"
            }
        }

        stage('Post-Build Report') {
            steps {
                archiveArtifacts artifacts: '**/test-output/extent-Reports/*.html', allowEmptyArchive: true
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'test-output/extent-Reports',
                    reportFiles: '*.html',
                    reportName: 'Extent Test Report',
                    reportTitles: 'Test Results'
                ])
            }
        }
    }

    post {
        always {
            echo 'Job completed!'
            archiveArtifacts artifacts: '**/test-output/extent-Reports/*.html', allowEmptyArchive: true

            // Publish Extent Report
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output/extent-Reports',
                reportFiles: '*.html',
                reportName: 'Extent Test Report',
                reportTitles: 'Test Results'
            ])
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
