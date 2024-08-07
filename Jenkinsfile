node {
    withCredentials([string(credentialsId: 'MONGO_URI', variable: 'MONGO_URI'), string(credentialsId: 'JWT_SECRET', variable: 'JWT_SECRET'), string(credentialsId: 'SENDGRID_API_KEY', variable: 'SENDGRID_API_KEY')]){
        def app;
        stage("Preparation") {
            checkout scm
        }
        stage("Build") {
            docker.withRegistry("https://index.docker.io/v2/", "dockerhub") {
                app = docker.build("ishaan04/jenkins-pipeline-test", ".")
            }
        }
        stage("Publish") {
            app.push()
        }
    }
    
}