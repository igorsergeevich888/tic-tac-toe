#!/usr/bin/env sh

cd "$(dirname "$0")" || exit

java -jar ${project.build.finalName}-release.jar




