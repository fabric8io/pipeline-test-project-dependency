#!/usr/bin/groovy
def stage(){
  return stageProject{
    project = 'fabric8io/pipeline-test-project'
    useGitTagForNextVersion = true
  }
}

def release(project){
  if (project == null){
    error 'project is null'
  }
  releaseProject{
    stagedProject = project
    dockerOrganisation = 'fabric8'
    artifactIdToWatchInCentral = 'pipeline-test-project'
    artifactExtensionToWatchInCentral = 'jar'
    imagesToPromoteToDockerHub = ['pipeline-test-project']
    extraImagesToTag = null
    groupId = 'io.fabric8'
    helmPush = false
    useGitTagForNextVersion = true
    promoteToDockerRegistry = 'docker.io'
    githubOrganisation = 'fabric8io'
  }
}

return this;
