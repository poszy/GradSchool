#!/usr/bin/python
# Author  : Luis M. Pena
# Course  : JHU Discrete Mathematics Spring '21
# Purpose : Project 1 - Find the Murderer
# Date    : 2/14/21

import csv

def findGuiltySuspects(pPaul, pQuinn, pRay, tTed, sSteve, caseNumber):
    """This function takes in 5 suspect names and computes the possibilies of guilt.

    Args:
        pPaul  = a boolean representing if Paul is lying or telling the truth
        pQuinn = a boolean representing if pQuinn is lying or telling the truth
        pRay   = a boolean representing if pRay is lying or telling the truth
        tTed   = a boolean representing if tTed is lying or telling the truth
        sSteve = a boolean representing if sSteve is lying or telling the truth
        caseNumber = a string that is used for csv file name. This file exports the results to a csv.

    Returns:
        print statements with the computed verdict of innocent or guilty.
    """

    # True : telling the truth
    # False : lying and a suspect

    if pPaul:
        pRay   = False # Ray is not telling the truth, as he is guilty
        pSteve = True  # Steve is true, since Ray is lying
        pTed   = True  # Ted  is true, since Ray is lying

    # If Paul is lying
    elif not pPaul:
        pRay   = True  # Then ray is innocent.
        pSteve = False # Steve is guilty by Ray's accusation
        pTed   = False # Ted is guilty by Ray's accusation

    # If steve is innoncent then Both Quinn and ray are guilty
    if pSteve:
        if pQuinn == None:  # Do not Change quinn if there is no statement
            pQuinn = None   # Do not Change quinn if there is no statement
        elif pSteve:
            pQuinn = False; # Quinn is guilty
            pRay = False;   # Ray is guilty

    elif not pSteve:
        if pQuinn == None:  # Do not Change quinn if there is no statement
            pQuinn = None;  # Do not Change quinn if there is no statement
        elif not pSteve:
            pQuinn = True; # Quinn is innocent if steve is lying
            pRay = True;   # Ray is innocent if steve is lying

    if pRay:
        pSteve = False; # Steve is guilty
        pTed = False;   # Ted is guilty


    elif not pRay:
        pSteve = True; # Steve Is innocent
        pTed = True;   # Ted is Innoncent
        pRay = False;

    if pQuinn: # If Quinn gives a truthful statement
        pSteve = False; # Steve is guilty
        pRay   = False; # Ray is guilty


    elif pQuinn == None: # if Quinn did not give a statement
        pQuinn = None; # do not set quinn to false / liar

    elif not pQuinn: # If pQuinn Gives a false statement
        pSteve = True; # Steve is not lying
        pRay   = True; # Ray is not lying

    # Create dictionary to keep track of values computed.
    # the Key is hardcoded as it does not matter what order it is in,
    # value is taken directly after its been manipulated, this value is first passed through the paramaters and then computed.
    answers = {'Paul':  pPaul, 'Ray':  pRay, 'Ted':  pTed, 'Steve':  pSteve , 'Quinn':  pQuinn,}

    # File hander getting ready to export based on computed values and named from case number above.
    w = csv.writer(open("%s.csv" % caseNumber, "w+"))

    # Write this first row to make things a little neater, firs row will have to columns of
    # suspect and the computed value
    w.writerow(["Suspect", " Verdict"])

    # Loop through every key value in the computer dictionary above.

    for key,value in answers.items():

        # If the value stored in the value variable is True, then the person is telling
        # the truth and is innocent.

        if value:
            print(key + " is innoncent")
            w.writerow([key, " is innocent"])

        # otherwise the person is a liar and probably a murderer.

        elif not value:

            print (key + " is guilty")
            w.writerow([key, "is guilty"])

    # Just console output to seperate the each cases being comptuted.

    print ("**************************************")


# Printing out the bases problem as it helps when trying to
# make sense of the console output

print("Basis Problem:")
print("Paul says, Ray is guilty.")
print("Quinn says, If Steve is guilty, then so is Ray. ")
print("Ray says, Both Steve and Ted are guilty.")
print("Steve says, Both Quinn and Ray are guilty.")
print("Ted says, At least one of Paul or Ray is guilty.")
print("------------------------------------------------- \n")

# Case 1

"""
If we assume everyone is lying then, we dont have a basis for an argument
so everyone is guilty, but Quinn did not give a statement and since steve says quinn is
guilty, then we leave quinn as quilty.
This is the only logic that does not fit into this problem.
Since everyone is lying, the only person that could be innocent is ray.
"""

pPaul=False
pQuinn=None
pRay=False
tTed=False
sSteve=False
caseOne="caseOne"

print("### Case 1")
findGuiltySuspects(pPaul, pQuinn, pRay, tTed, sSteve,caseOne)


# Case 2
"""
If we question Paul first, Ray is guilty . Assuming Paul is telling the truth
Then this means Ted is tellin the truth and Ray is guilty.
Then this means Steve might also be telling the truth about Ray
In this case, quinn decline to make a statement and is guilty because steve is telling the truth.
--- This seems to be the most logical explination if quin does not give a statement.
"""

pPaul=True
pQuinn=None
pRay=False
tTed=False
sSteve=False
caseTwo="caseTwo"
print("### Case 2")
findGuiltySuspects(pPaul, pQuinn, pRay, tTed, sSteve, caseTwo)


# Case3
"""
If we question Paul first, and paul is lying, Ray is set to true,
Since ray is telling the truth, Ted and steeve are both lying
if quinn gives a truthful statement then that means ray is also lying.
this is the only logic that does not fit into this problem.
"""
pPaul=False
pQuinn=True
pRay=True
tTed=False
sSteve=False
caseThree="caseThree"
print("### Case 3")
findGuiltySuspects(pPaul, pQuinn, pRay, tTed, sSteve, caseThree)
