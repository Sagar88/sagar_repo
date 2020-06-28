/* uses sbt, which i installed with homebrew. */
/* this works without requiring the 'sbt plugin'. */

pipeline {
    agent any

    stages {

        stage('Compile') {
            steps {
                echo "Compiling..."
                sh "/Users/sagarkulkarni/dev/sbt/bin/sbt compile"
            }
        }

        stage('Test') {
            steps {
                echo "Testing..."
                sh "/Users/sagarkulkarni/dev/sbt/bin/sbt test"
            }
        }

        stage('Package') {
            steps {
                echo "Packaging..."
                sh "/Users/sagarkulkarni/dev/sbt/bin/sbt package"
            }
        }

    }
}