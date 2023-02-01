#! /usr/bin/python3
import sys
import subprocess
import json
import getpass
import os.path
import re

cred = '/Users/nuli/.config/git/.git-credentials'

with open(cred) as f:
    for line in f:
        m = re.findall(r'.*//(.*):(.*)@github.itu.dk.*', line)
        if m:
            username, password = m[0]
            #print(username, password)
    #exit()        
def __send(cmd):
    global username,password
    cmd = 'curl -sS -u "'+username+':'+password+'" ' + cmd
    output = subprocess.getoutput(cmd)
    if len(output) > 0:
        js = json.loads(output)
        if 'message' in js:
            print(js['message'])
        return js
    return dict()

def createRepo(reponame):
    __send('https://github.itu.dk/api/v3/user/repos -d \'{"name" : "'+reponame+'", "private" : "true"}\'')

def addCollaborator(reponame,username):
    __send('-X PUT https://github.itu.dk/api/v3/repos/nuli/{}/collaborators/{}'
           .format(reponame,username))

def removeCollaborator(reponame,username):
    __send('-X DELETE https://github.itu.dk/api/v3/repos/trbj/{}/collaborators/{}'
           .format(reponame,username))

def getReleases(reponame):
    js = __send('https://github.itu.dk/api/v3/repos/trbj/{}/releases'
           .format(reponame))
    res = []
    for release in js:
        published = release['published_at']
        published = published[:published.index('T')]
        res.append(published + ' ' + release['tag_name'] + ' ' + release['html_url'])
    return res

def getCommits(reponame):
    js = __send('https://github.itu.dk/api/v3/repos/trbj/{}/commits'
           .format(reponame))
    res = []
    for commit in js:
        if commit['commit']['author']['email'] != 'trbj@itu.dk':
            res.append(commit['html_url'])
    return res

def deleteRepo(reponame):
    __send('-X DELETE https://github.itu.dk/api/v3/repos/trbj/{}'
           .format(reponame))

def main():
    if len(sys.argv) < 3:
        print("Usage: githubatitu <repo> <command> [args*]\n"
              "Commands:\n"
              " create: creates repo as private\n"
              " add: adds all collaborators in args to repo\n"
              " remove: removes all collaborators in args from repo\n"
              " delete: deletes repo")
        exit()

    _, reponame, command, *args = sys.argv

    if command == 'create':
        if len(args) == 0:
            createRepo(reponame)
        else:
            print('ERROR: trailing args: ' + ' '.join(args))
    elif command == 'add':
        for username in args:
            addCollaborator(reponame,username)
    elif command == 'remove':
        for username in args:
            removeCollaborator(reponame,username)
    elif command == 'releases':
        print('\n'.join(getReleases(reponame)))
    elif command == 'commits':
        print('\n'.join(getCommits(reponame)))
    elif command == 'delete':
        if len(args) == 0:
            deleteRepo(reponame)
        else:
            print('ERROR: trailing args: ' + ' '.join(args))
    else:
        print('Unknown command: ' + command)

if __name__ == "__main__":
    main()
