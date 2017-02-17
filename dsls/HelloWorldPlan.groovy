project(key: 'MYPROJECT', name: 'My project') {
    plan(key: 'MYPLAN', name: 'My plan') {

        stage(name: 'My stage') {
            description 'My stage'

            job(key: 'BUILD', name: 'Maven build job' ) {
                tasks {
                    script() {
                        description 'echo hello world'
                        inline {
                            interpreter ScriptInterpreter.LEGACY_SH_BAT
                            scriptBody 'echo "Hello World"'
                        }
                    }
                }
            }
        }

        deploymentProject(name: 'my deployment project') {
            environment(name: 'staging') {
            }
            environment(name: 'production') {
            }
        }
    }
}
