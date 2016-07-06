#!/usr/bin/groovy
node{
  checkout scm

  def pipeline = load 'release.groovy'

  stage 'Staging project'
  def stagedProject = pipeline.stage()

  stage 'Promoting'
  pipeline.release(stagedProject)

  stage 'Update downstream dependencies'
  pipeline.updateDownstreamDependencies(stagedProject)
}
