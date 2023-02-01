import json
from pathlib import Path

all = json.load(open("/Users/nuli/Downloads/students.json"))[0]
    
for x in all:
    login = x['email'].split('@')[0] 
    p = Path('StudentDirs')/ login
    print(p)
    #p.mkdir(parents=True)