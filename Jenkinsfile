pipeline {
    agent any
	
	tools { 
        maven 'MAVEN_HOME'
        jdk   'jdk1.8'
    }
	
    stages {     
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "MAVEN_HOME = ${MAVEN_HOME}"
                ''' 
            }
        }        
        stage('Package') {
            steps {
              sh 'mvn clean package'
              sh 'mv -u target/service-config-0.0.1-SNAPSHOT.war  docker/service-config.war'
            }
        }
        stage('Build') {
            steps {
             sh "sudo docker build -t service-config:${GIT_BRANCH} docker/"
        	}
        }
        stage('Push') {
            steps {
             sh "sudo docker tag service-config:${GIT_BRANCH} docker.registry.cscloud.com/service-config:${GIT_BRANCH} "
             sh "sudo docker push docker.registry.cscloud.com/service-config:${GIT_BRANCH}"
            }
        }
    }
}
