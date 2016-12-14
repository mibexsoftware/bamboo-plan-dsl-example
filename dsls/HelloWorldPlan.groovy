project(key: 'MYPROJECT', name: 'My project') {
    plan(key: 'MYPLAN', name: 'My plan') {
        stage(name: 'My stage') {
            description 'My stage'
            manual false

            job(key: 'BUILD', name: 'Maven build job' ) {
                tasks {
                    maven3x(goal: 'install') {
                        description 'build plug-in'
                        executable 'maven323'
                        buildJdk 'jdk8'
                        environmentVariables 'MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=128m"'
                    }
                }
            }
        }
    }
}