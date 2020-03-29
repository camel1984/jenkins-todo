pipeline {
    agent any
    environment {
         SOURCE_BUILD_NUMBER = "${BUILD_NUMBER}"
    }
    triggers { pollSCM('* * * * *') }
    options {
            timeout(time: 1, unit: 'HOURS')
            buildDiscarder(logRotator(numToKeepStr: '100'))
            quietPeriod(4)
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
                 sh './gradlew --no-daemon clean'
                 sh './gradlew --no-daemon test'
            }
        }

         stage('todo-integ-tests') {
             steps {
                sh './gradlew --no-daemon repository:integrationTest'
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
                sh './gradlew --no-daemon repository:sonarqube'
            }
         }
         stage('todo-local-functional-test') {
            steps {
                sh './gradlew --no-daemon web:localFunctionalTest'
            }
         }
         stage('todo-distribution') {
            steps {
                sh './gradlew --no-daemon bintrayUpload'
            }
         }
         stage('todo-acceptance-deploy') {
             steps {
               input(message: "Procced to deployment?")
               sh './gradlew --no-daemon deployWar -Penv=test'
             }
         }
        stage('todo-acceptance-test') {
             steps {
               input(message: "Procced to accept test?")
                sh './gradlew --no-daemon smokeTests -Penv=test'
                sh './gradlew --no-daemon remoteFunctionalTest -Penv=test'
             }
         }
    }
}