import dsls.utilities.MyUtility

/**
 * Plan DSL for Bamboo.
 * Learn more at <a href='https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki'>
 * https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki</a>
 */

// This is an example which shows various aspects of the Plan DSL for Bamboo plug-in. It shows how to access the exported
// Bamboo API's by creating a global repository definition programatically. Then, it shows how to use the Groovy-based
// Plan DSL to create a buid plan including two deployment projects. The plan has a build job that checks this
// Github repository out and then runs the Groovy unit tests. In there, it also shows how to put common code that is
// used across your DSL files into a separate module (see "MyUtility").

// Create a global repository definition
def repoName = 'plan-dsl-example'
def gitConfig = chainCreationService.getBuildConfigurationWithDefaults()
gitConfig.setProperty('repository.github.repository', 'mibexsoftware/bamboo-plan-dsl-plugin')
gitConfig.setProperty('repository.github.branch', 'master')
gitConfig.setProperty('repository.github.username', 'mibexsoftware')
def user = bambooUserManager.getBambooUser('admin')
repositoryConfigurationService.createGlobalRepository(
        repoName,
        'com.atlassian.bamboo.plugins.atlassian-bamboo-plugin-git:gh',
        null,
        gitConfig,
        true,
        user
)

// and reference it in the plan DSL
project(key: 'MYPROJECT', name: 'My project') {
    plan(key: 'MYPLAN', name: 'My plan') {
        stage(name: 'My stage') {

            description 'My stage'

            job(key: 'BUILD', name: 'Example job' ) {
                def myTasks = tasks {
                    checkout() {
                        repository(repoName) {}
                    }
                }
                MyUtility.addGradleTest(myTasks)
            }
        }

        scm {
            linkedRepository repoName
        }

        deploymentProject(name: 'my deployment project') {
            environment(name: 'staging') {
            }
            environment(name: 'production') {
            }
        }
    }
}
