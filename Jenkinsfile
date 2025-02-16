pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/dhaneshsk07/ForDemoBlazeDataDriven-.git' // Replace with your repository URL
            }
        }
        stage('Build and Test') {
            steps {
                script {
                    // Ensure Maven is installed
                    bat 'mvn clean install test' //install & run mvn test

                    //bat 'mvn clean test' // Runs mvn test
                    
                    
                }
            }
        }
    }
   post {
        always {
            // Archive TestNG reports
            junit '**/test-output/testng-*.xml' // Adjust path if needed
            
            // Archive Extent Reports
            archiveArtifacts artifacts: '**/test-output/ExtentReports/*.html', allowEmptyArchive: true
        }
    }
}
