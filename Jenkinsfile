pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your/repository.git' // Replace with your repository URL
            }
        }
        stage('Build and Test') {
            steps {
                script {
                    // Ensure Maven is installed
                    sh 'mvn clean test' // Runs mvn test
                }
            }
        }
    }
    post {
        always {
            // Archive test results, you can modify this to suit your needs
            junit '**/target/test-*.xml' // Adjust path to match your test result format
        }
    }
}
