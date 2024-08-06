job("Jenkins-test") {
    scm {
        git("https://github.com/Ishaan1604/Jenkins-test.git") { node -> 
            node / gitConfigName("DSL User")
            node /gitConfigEmail("jenkins-dsl@newtech.academy")
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName("ishaan04/jenkins-test")
            tag('test')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}