#!/usr/bin/groovy
@Library('github.com/rawlingsj/fabric8-pipeline-library@master')
def dummy
mavenNode {
  checkout scm

  def pipeline = load 'release.groovy'

  stage 'Staging project'
  def stagedProject = pipeline.stage()

  stage 'Promoting'
  pipeline.release(stagedProject)

  stage 'Update downstream dependencies'
  pipeline.updateDownstreamDependencies(stagedProject)
}
