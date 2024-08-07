node {
    def app;
    stage("Preparation") {
        checkout scm
    }
    stage("Test") {
        def myContainer = docker.image("node:21")
        myContainer.pull()
        myContainer.inside('-v /tmp/.npm:/Users/IshaanAgarwal2/.npm') {
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