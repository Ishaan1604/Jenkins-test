node {
    withCredentials([string(credentialsId: 'MONGO_URI', variable: 'MONGO_URI'), string(credentialsId: 'JWT_SECRET', variable: 'JWT_SECRET'), string(credentialsId: 'SENDGRID_API_KEY', variable: 'SENDGRID_API_KEY')]){
        def app;
        def id;
        stage("Preparation") {
            checkout scm
        }
        stage("Start server") {
            def node_image = docker.image("node:21")
            node_image.pull();
            // def container = node_image.run("-v /tmp/.npm:/.npm -e 'MONGO_URI=${MONGO_URI}' -e 'JWT_SECRET=${JWT_SECRET}' -e 'SENDGRID_API_KEY=${SENDGRID_API_KEY}'") 
            // container.inside {
            //     sh "npm install"
            //     sh "nohup npm start &"
            //     sleep(30)
            // }
            id = sh(script: """
                        docker run -d -v /tmp/.npm:/.npm \
                        -e 'MONGO_URI=${MONGO_URI}' \
                        -e 'JWT_SECRET=${JWT_SECRET}' \
                        -e 'SENDGRID_API_KEY=${SENDGRID_API_KEY}' \
                        node:21 sh -c 'npm install && nohup npm start'
                    """, returnStdout: true).trim()
            sleep(30)
        }
        stage("Test") {
            sh "docker exec ${id} npm testRunner"
        }
        stage("Build") {
            app = docker.build("ishaan04/jenkins-pipeline-test", ".")
        }
        stage("Publish") {
            sh "docker context use desktop-linux"
            app.push()
            // docker.withRegistry("https://index.docker.io/v2/", "dockerhub") {
            // }
        }
    }
    
}