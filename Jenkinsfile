#!/usr/bin/groovy
node{
  checkout scm

  def pipeline = load 'release.groovy'

  def stagedProject = pipeline.stage()

  pipeline.release(stagedProject)
}
