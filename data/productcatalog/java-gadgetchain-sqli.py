#!/usr/bin/env python

from lib.core.enums import PRIORITY

__priority__ = PRIORITY.NORMAL

def tamper(payload, **kwargs):
    import subprocess
    
    payload = subprocess.getoutput("java Main.java "+ '"' + payload + '"')    

    return payload
