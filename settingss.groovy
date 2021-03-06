/* settings.groovy
   ##################################################
   # Created by Lin Ru at 2018.10.01 22:00          #
   #                                                #
   # A Part of the Project jenkins-library          #
   #  https://github.com/Statemood/jenkins-library  #
   ##################################################
*/

///////////////////////////////////////////////////////
// BASIC                                             //
///////////////////////////////////////////////////////
env.WORKSPACE       = JENKINS_HOME + "/workspace/" + JOB_NAME
env.WS              = WORKSPACE

try {
    // Get User info
    // Requeire 'build user vars' plugin
    //  see https://plugins.jenkins.io/build-user-vars-plugin for more information
    wrap([$class: 'BuildUser']) { env.BUILD_USER    = BUILD_USER    }
    wrap([$class: 'BuildUser']) { env.BUILD_USER_ID = BUILD_USER_ID }
}
catch (e) {
    log.warning "Requeire 'build user vars' plugin, see https://plugins.jenkins.io/build-user-vars-plugin for more information"
}

env.WEB_ROOT            = "/data/web/"

// GIT
env.GIT_COMMIT_ID_DISPLAY_LEN = 6

// Stage & Node
env.STAGE_PRE_PROCESS   = "master"
env.STAGE_GIT           = "master"
env.STAGE_BUILD         = "master"
env.STAGE_DOCKER        = "master"
env.STAGE_TEST          = "master"
env.STAGE_K8S           = "master"

// TRIGGERS
env.SKIP_BUILD          = false
env.SKIP_DOCKER         = false
env.SKIP_GIT            = false
env.SKIP_SONAR          = false
env.SKIP_K8S            = false     // kubernetes
env.SKIP_HPA            = false     // kubernetes HPA
env.SKIP_CRONJOB        = false     // kubernetes CronJob
env.SKIP_JOB            = false     // kubernetes Job

///////////////////////////////////////////////////////
// BUILD SETTINGS                                    //
///////////////////////////////////////////////////////
// Common
env.BUILD_DIR           = "."
env.BUILD_COMMAND       = ""
env.BUILD_LEGACY        = false

// Golang
env.GOROOT          = "/usr/lib/go"
env.GOPATH          =  WORKSPACE

// Maven
env.MAVEN_CMD           = "mvn"
env.MAVEN_ARGS          = "-U clean -Dmaven.test.skip=true package dependency:tree"

// NodeJS
env.NPM_REGISTRY        = "https://registry.npm.taobao.org/"
env.NPM_INSTALL         = "npm -i " + NPM_REGISTRY
env.NPM_I               =  NPM_INSTALL

// PHP (Composer)
env.PHP_COMPOSER_CMD    = "composer install"

// OSS
env.OSS_ENDPOINT        = ""
env.OSS_OPTIONS         = "-rf"

///////////////////////////////////////////////////////
// Docker                                            //
///////////////////////////////////////////////////////
env.DOCKER_REGISTRY                 = ""
env.DOCKER_REGISTRY_USER            = ""
// Saved in the Jenkins credentials with name 'Docker-Registry'
env.DOCKER_REGISTRY_PASSWORD        = credentials('Docker-Registry')
env.DOCKER_IMAGE_NAME               = ""
env.DOCKER_IMAGE_TAG                = ""
env.DOCKER_IMAGE_BUILD_TIMEOUT      = 300
env.DOCKER_IMAGE_PUSH_TIMEOUT       = 300
env.DOCKER_IMAGE_BUILD_OPTIONS      = ""

env.DOCKERIGNORE_FILE               = ".dockerignore"

// Dockerfiles dir
env.DOCKERFILES                     = "Dockerfiles"

env.DOCKERFILE_TEMPLATE_LIST        = []

///////////////////////////////////////////////////////
// kubernetes                                        //
///////////////////////////////////////////////////////
env.K8S_LIMITS_CPU      = "500m"
env.K8S_LIMITS_MEMORY   = "512Mi"
env.K8S_REQUESTS_CPU    = "500m"
env.K8S_REQUESTS_MEMORY = "512Mi"

env.K8S_RUN_USER_ID                 = 
env.K8S_NAMESPACE                   = 
env.K8S_REPLICAS                    = 

env.K8S_AFFINITY_PROVIDER           = 
env.K8S_AFFINITY_ROLE               =
env.K8S_AFFINITY_REGION             = 

env.K8S_IMAGE_PULL_POLICY           = "IfNotPresent"
env.K8S_REVISION_HISTORY_LIMIT      = 5
env.K8S_STRATEGY_MAX_SURGE          = "10%"
env.K8S_STRATEGY_MAX_UNAVAILABLE    = "10%"
env.K8S_GRACE_PERIOD_SECONDS        = 60  // Sec
env.K8S_MIN_READY_SECONDS           = 5   // Sec

env.K8S_ALLOWED_COMMADS             = ["apply", "create", "delete", "get", "autoscale"]