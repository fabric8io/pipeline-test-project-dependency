#!/usr/bin/groovy
node{

  def projectName = 'pipeline-test-project'
  def mavenCentralArtifact = 'io/fabric8/kubeflix/pipeline-test-project'

  kubernetes.pod('buildpod').withImage('fabric8/maven-builder:1.0')
  .withPrivileged(true)
  .withHostPathMount('/var/run/docker.sock','/var/run/docker.sock')
  .withEnvVar('DOCKER_CONFIG','/root/.docker/')
  .withSecret('jenkins-maven-settings','/root/.m2')
  .withSecret('jenkins-hub-api-token','/root/.apitoken')
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

    waitUntilPullRequestMerged{
      name = projectName
      prId = pullRequestId
    }

    waitUntilArtifactSyncedWithCentral {
      artifact = mavenCentralArtifact
      version = stagedProject[1]
    }
  }
}
