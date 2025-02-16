pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/dhaneshsk07/ForDemoBlazeDataDriven-.git'
            }
        }
        stage('Build and Test') {
            steps {
                dir('C:\\Users\\dhane\\.jenkins\\workspace\\DemoBlazePipelineJob') { 
                    bat 'mvn clean install test'
                }
            }
        }
    }
    post {
        always {
            // Archive TestNG reports 
            junit '**/test-output/testng-*.xml'
            
            // Archive Extent Reports
            archiveArtifacts artifacts: '**/test-output/ExtentReports/*.html', allowEmptyArchive: true
        }
    }
}
