#!/usr/bin/env bash
set -e

source $HOME/jenkins_env

# Clean up previous builds
git clean -qxfd

# Clear the mongo database
# Note that this prevents us from running jobs in parallel on a single worker.
mongo --quiet --eval 'db.getMongo().getDBNames().forEach(function(i){db.getSiblingDB(i).dropDatabase()})'

# Reset the jenkins worker's ruby environment back to
# the state it was in when the instance was spun up.
if [ -e $HOME/edx-rbenv_clean.tar.gz ]; then
    rm -rf $HOME/.rbenv
    tar -C $HOME -xf $HOME/edx-rbenv_clean.tar.gz
fi

# Bootstrap Ruby requirements so we can run the tests
bundle install

# Ensure the Ruby environment contains no stray gems
bundle clean --force

# Reset the jenkins worker's virtualenv back to the
# state it was in when the instance was spun up.
if [ -e $HOME/edx-venv_clean.tar.gz ]; then
    rm -rf $HOME/edx-venv
    tar -C $HOME -xf $HOME/edx-venv_clean.tar.gz
fi

# Activate the Python virtualenv
source $HOME/edx-venv/bin/activate
