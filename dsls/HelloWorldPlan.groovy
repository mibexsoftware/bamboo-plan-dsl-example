import utilities.MyUtility

project(key: 'MYPROJECT', name: 'My project') {
    plan(key: 'MYPLAN', name: 'My plan') {

        stage(name: 'My stage') {
            description 'My stage'

            job(key: 'BUILD', name: 'Example job' ) {
                def myTasks = tasks {}
                MyUtility.addHelloWorldTask(myTasks)
                MyUtility.injectBambooVariables(myTasks)
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
