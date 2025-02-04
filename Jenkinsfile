pipeline {
    agent any

    stages {
        
        stage('Build') {
            steps {
				git 'https://github.com/dhaneshsk07/ForOpenCart.git' // Replace with your repository URL
                echo 'Building the application...'
                // Add commands for your build process
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Add commands for running your tests
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Add commands for deploying your app
            }
        }
    }
}
