package dsls

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.ScriptLanguage
import com.atlassian.bamboo.build.creation.ChainCreationService
import com.atlassian.bamboo.repository.RepositoryConfigurationService
import com.atlassian.bamboo.user.BambooUser
import com.atlassian.bamboo.user.BambooUserManager
import com.atlassian.bamboo.ww2.actions.build.admin.create.BuildConfiguration
import spock.lang.Specification
import spock.lang.Unroll

class DslScriptSpec extends Specification {

    @Unroll
    def 'test DSL script #file.name'(File file) {
        given:

        when:
        def bamooFacade = Mock(BambooFacade)
        def chainCreationService = Mock(ChainCreationService)
        def bambooUserManager = Mock(BambooUserManager)
        def bambooUser = Mock(BambooUser)
        def repositoryConfigurationService = Mock(RepositoryConfigurationService)
        bambooUserManager.getBambooUser(_ as String) >> bambooUser
        chainCreationService.getBuildConfigurationWithDefaults() >> new BuildConfiguration()
        bamooFacade.getExportedBambooObjects() >> ['chainCreationService'          : chainCreationService,
                                                   'bambooUserManager'             : bambooUserManager,
                                                   'repositoryConfigurationService': repositoryConfigurationService]
        def loader = new DslScriptParserImpl(bamooFacade)
        def classPath = new File('src/main/groovy').toURI().toURL()
        def body = null
        loader.parse(new DslScriptContext(file.toURI().toString(), body, ScriptLanguage.GROOVY_DSL, classPath))

        then:
        noExceptionThrown()

        where:
        file << findDslFiles()
    }

    private static def findDslFiles() {
        List<File> files = []
        new File('dsls').eachFileRecurse {
            if (it.name.endsWith('.groovy')) {
                files << it
            }
        }
        files
    }

}