from githubatitu import *
from subprocess import getoutput
from random import shuffle
from itertools import cycle
from collections import defaultdict

with open('/Users/nuli/Dropbox/Work/Courses/FirstYearProject/BFST/23/scripts/studs.txt') as f:
    lines = f.readlines()

#shuffle(lines)

#tas = cycle('ebak anmb aljb haiv nako frot'.split())

# ta2studs = defaultdict(list)

for num,line in enumerate(lines):
    user = line.strip()
    #ta = next(tas)
    # print('\r ({}/{}) {}  '.format(num+1,len(lines),user), end='')
    repo = 'BFST23'+user
    createRepo(repo)
    # deleteRepo(repo)
    # for t in tas:
    #     removeCollaborator(repo,t)
    print(user)
    addCollaborator(repo,user)
    # addCollaborator(repo,ta)
    # ta2studs[ta].append(user)
    # getoutput('git remote add {} https://github.itu.dk/trbj/{}.git'.format(user,repo))
    # getoutput('git push {} handin1'.format(user))

# with open('README.md','w') as pretty, open('grade.txt','w') as grade:
#     for ta, studs in sorted(ta2studs.items()):
#         pretty.write(f'# {ta}:\n')
#         for stud in sorted(studs):
#             pretty.write(f'* <https://github.itu.dk/trbj/BFST22{stud}/releases>\n')
#             grade.write(f'{stud}\n')
