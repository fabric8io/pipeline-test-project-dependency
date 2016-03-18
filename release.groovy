#!/usr/bin/groovy
def stage(){
  return stageProject{
    project = 'fabric8io/pipeline-test-project-dependency'
    useGitTagForNextVersion = true
  }
}

def release(project){
  releaseProject{
    stagedProject = project
    useGitTagForNextVersion = true
    helmPush = false
    groupId = 'io.fabric8'
    githubOrganisation = 'fabric8io'
    artifactIdToWatchInCentral = 'pipeline-test-project-dependency'
    artifactExtensionToWatchInCentral = 'jar'
    extraImagesToTag = null
  }
}

return this;
