package com.manning.gia.ssh

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction

class SshExec extends DefaultTask {
    @InputFiles
    FileCollection classpath

    @Input
    String host

    @Input
    String username

    @Input
    String command

    @InputFile
    File keyFile

    @Input
    Integer port

    @TaskAction
    void runCommand() {
        logger.quiet "Executing SSH command '$command'."

        ant.taskdef(name: 'jschSshExec', classname: 'org.apache.tools.ant.taskdefs.optional.ssh.SSHExec', classpath: classpath.asPath)
        ant.jschSshExec(host: host, username: username, command: command, port: port, keyfile: keyFile.canonicalPath, trust: 'true')
    }
}