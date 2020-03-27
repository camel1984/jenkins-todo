environments {

    local {
        server {
            hostname = 'notdefinedyet'
            sshPort = 2222
            username = 'notdefined'
        }

        tomcat {
            hostname = 'notdefinedyet'
            port = 8080
            context = 'notdefine'
        }
    }

    test {
        server {
            hostname = '127.0.0.1'
            sshPort = 2222
            username = 'vagrant'
        }

        tomcat {
            hostname = '127.0.0.1'
            port = 8089
            context = 'todo'
        }
    }
}