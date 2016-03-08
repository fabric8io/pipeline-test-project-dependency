#!/usr/bin/groovy
node{

  def projectName = 'fabric8io/pipeline-test-project'

  kubernetes.pod('buildpod').withImage('fabric8/maven-builder:1.0')
  .withPrivileged(true)
  .withHostPathMount('/var/run/docker.sock','/var/run/docker.sock')
  .withEnvVar('DOCKER_CONFIG','/root/.docker/')
  .withSecret('jenkins-maven-settings','/root/.m2')
  .withSecret('jenkins-ssh-config','/root/.ssh')
  .withSecret('jenkins-git-ssh','/root/.ssh-git')
  .withSecret('jenkins-release-gpg','/root/.gnupg/')
  .inside {

    sh 'chmod 600 /root/.ssh-git/ssh-key'
    sh 'chmod 600 /root/.ssh-git/ssh-key.pub'
    sh 'chmod 700 /root/.ssh-git'
    sh 'chmod 600 /root/.gnupg/pubring.gpg'
    sh 'chmod 600 /root/.gnupg/secring.gpg'
    sh 'chmod 600 /root/.gnupg/trustdb.gpg'
    sh 'chmod 700 /root/.gnupg'

    checkout scm

    sh 'git remote set-url origin git@github.com:fabric8io/pipeline-test-project.git'

    def stagedProject = stageProject{
      project = projectName
    }

    String pullRequestId = release {
      projectStagingDetails = stagedProject
      project = projectName
      helmPush = false
    }

    promoteImages{
      project = projectName
      images = ['hystrix-dashboard','turbine-server']
      tag = stagedProject[1]
    }

    waitUntilPullRequestMerged{
      name = projectName
      prId = pullRequestId
    }

    waitUntilArtifactSyncedWithCentral {
      repo = 'http://central.maven.org/maven2/'
      groupId = 'io/fabric8'
      artifactId = 'pipeline-test-project'
      version = stagedProject[1]
      ext = 'jar'
    }
  }
}
