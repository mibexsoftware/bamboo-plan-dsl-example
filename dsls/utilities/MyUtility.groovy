package utilities

import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks
import ch.mibex.bamboo.plandsl.dsl.tasks.ScriptTask.ScriptInterpreter
import ch.mibex.bamboo.plandsl.dsl.tasks.InjectBambooVariablesTask.VariablesScope

class MyUtility {

    static void addHelloWorldTask(Tasks tasks) {
        tasks.with {
            script() {
                description 'echo hello world'
                inline {
                    interpreter ScriptInterpreter.LEGACY_SH_BAT
                    scriptBody 'echo "Hello World"'
                }
            }
        }
    }

    static void injectBambooVariables(Tasks tasks) {
        tasks.with {
            injectBambooVariables(propertiesFilePath: 'envVars.properties', namespace: 'test') {
                description "Inject Build Variables"
                isFinal true
                variablesScope VariablesScope.RESULT
            }
        }
    }

}
