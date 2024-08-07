node {
    withCredentials([string(credentialsId: 'MONGO_URI', variable: 'MONGO_URI'), string(credentialsId: 'JWT_SECRET', variable: 'JWT_SECRET'), string(credentialsId: 'SENDGRID_API_KEY', variable: 'SENDGRID_API_KEY')]){
        def app;
        stage("Preparation") {
            checkout scm
        }
        stage("Test") {
            def myContainer = docker.image("node:21")
            myContainer.pull()
            myContainer.inside('-v /tmp/.npm:/.npm -e MONGO_URI=$MONGO_URI -e JWT_SECRET=$JWT_SECRET -e SENDGRID_API_KEY=$SENDGRID_API_KEY') {
                sh "rm -rf node_modules"
                sh "npm install"
                sh "npm start && npm testRunner"
            }
        }
        stage("Build") {
            docker.withRegistry("https://index.docker.io/v1/", "dockerhub") {
                app = docker.build("ishaan04/jenkins-pipeline-test", ".")
            }
        }
        stage("Publish") {
            app.push()
        }
    }
    
}