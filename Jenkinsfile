pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/dhaneshsk07/ForOpenCart.git' // Replace with your repository URL
            }
        }
        stage('Build and Test') {
            steps {
                script {
                    // Ensure Maven is installed
                    sh 'mvn clean install' //install maven

                    sh 'mvn clean test' // Runs mvn test
                }
            }
        }
    }
   post {
        always {
            // Archive TestNG reports
            junit '**/test-output/testng-*.xml' // Adjust path if needed
        }
    }
}
