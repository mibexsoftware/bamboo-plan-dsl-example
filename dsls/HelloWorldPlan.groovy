project("MYPROJECT") {
    name "My project"

    plan("MYPLAN") {
        name "My plan"

        stage("My stage") {
            description "My stage"
            manual false

            job("BUILD") {
                name "Maven build job"

                tasks {
                    maven3("build plug-in") {
                        goal "install"
                        executable "maven323"
                        buildJdk "jdk8"
                        environmentVariables 'MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=128m"'
                    }
                }
            }
        }
    }
}