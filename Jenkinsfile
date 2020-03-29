pipeline {
    agent any
    triggers { pollSCM('* * * * *') }
    options {
            timeout(time: 1, unit: 'HOURS')
            buildDiscarder(logRotator(numToKeepStr: '100'))
    }
    stages {
        stage('todo-initial') {
            steps {
                buildName("todo#${BUILD_NUMBER}")
                checkout([
                    $class: 'GitSCM',
                    userRemoteConfigs: [[
                        url: 'https://github.com/camel1984/jenkins-todo.git',
                        credentialsId: 'githubAccount',
                        branches: ['*/master']
                     ]]
                 ])
                 sh './gradlew clean'
                 sh './gradlew test'
            }
        }

         stage('todo-integ-tests') {
             steps {
                sh './gradlew repository:integrationTest'
                step([
                    $class: 'JUnitResultArchiver',
                    testResults: '**/build/test-results/integrationTest/*Test.xml',
                    allowEmptyResults: false,
                    healthScaleFactor: 1.0,
                    keepLongStdio: false
                ])
                jacoco(
                    execPattern : '**/build/jacoco/integrationTest.exec',
                    classPattern : '**/classes',
                    sourcePattern : '**/src/main/java',
                    sourceInclusionPattern : '**/*.java,**/*.groovy,**/*.kt,**/*.kts',
                    skipCopyOfSrcFiles : false,
                    minimumInstructionCoverage : '0',
                    minimumBranchCoverage : '0',
                    minimumComplexityCoverage : '0',
                    minimumLineCoverage : '0',
                    minimumMethodCoverage : '0',
                    minimumClassCoverage : '0',
                    maximumInstructionCoverage : '0',
                    maximumBranchCoverage : '0',
                    maximumComplexityCoverage : '0',
                    maximumLineCoverage : '0',
                    maximumMethodCoverage : '0',
                    maximumClassCoverage : '0',
                    changeBuildStatus : false,
                    runAlways : false,
                    deltaInstructionCoverage : '0',
                    deltaBranchCoverage : '0',
                    deltaComplexityCoverage : '0',
                    deltaLineCoverage : '0',
                    deltaMethodCoverage : '0',
                    deltaClassCoverage : '0',
                    buildOverBuild : false
                )
             }
         }

         stage('todo-code-quality') {
            steps {
                sh './gradlew repository:sonarqube'
            }
         }
         stage('todo-local-functional-test') {
            steps {
                sh './gradlew web:localFunctionalTest'
            }
         }
         stage('todo-distribution') {
            steps {
                sh './gradlew bintrayUpload'
            }
         }
         stage('todo-acceptance-deploy') {
             steps {
                sh './gradlew deployWar -Penv=test'
             }
         }
        stage('todo-acceptance-test') {
             steps {
                sh './gradlew smokeTests -Penv=test'
                sh './gradlew remoteFunctionalTest -Penv=test'
             }
         }
    }
}