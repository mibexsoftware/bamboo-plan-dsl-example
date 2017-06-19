package yaml

import ch.mibex.bamboo.plandsl.dsl.NullBambooFacade
import ch.mibex.bamboo.plandsl.dsl.YamlParser
import spock.lang.Specification
import spock.lang.Unroll

class YamlSpec extends Specification {

    @Unroll
    def 'test YAML file #file.name'(File file) {
        given:
        def parser = new YamlParser(new NullBambooFacade())

        when:
        parser.parse(file)

        then:
        noExceptionThrown()

        where:
        file << findYamlFiles()
    }

    private static def findYamlFiles() {
        List<File> files = []
        new File('yaml').eachFileRecurse {
            if (it.name.endsWith('.yml')) {
                files << it
            }
        }
        files
    }
}