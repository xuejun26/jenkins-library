/* Build.groovy
   ##################################################
   # Created by Lin Ru at 2018.10.01 22:00          #
   #                                                #
   # A Part of the Project jenkins-library          #
   #  https://github.com/Statemood/jenkins-library  #
   ##################################################
*/

def controller() {
    dir(BUILD_DIR) {
        try {
            switch (Config.data['lang'].toLowerCase()) {
                case "php":
                    if (fileExists('composer.lock') || fileExists('composer.json')) {
                        log.info "Build PHP project with '$PHP_COMPOSER_CMD'"

                        sh(PHP_COMPOSER_CMD)
                    } else {
                        log.notice "Skip build for the general PHP project"
                    }

                    return

                case "java":
                    log.info "Preparing Java build"
                    check.file("pom.xml")

                    try {
                        m_cmd = "$MAVEN_CMD $MAVEN_ARGS"
                        log.info "Build command: $m_cmd"
                        sh(m_cmd)

                        return
                    }
                    catch (e) {
                        throw e
                    }

                case "nodejs":
                    check.file('package.json')
                    log.info "Build NodeJS project with '$NPM_I'"

                    sh(NPM_I)
            }

            if (BUILD_COMMAND) {
                log.info "Start " + BUILD_COMMAND

                sh(BUILD_COMMAND)
                
                return
            }
        } catch (e) {
            error e
        }
    }
}