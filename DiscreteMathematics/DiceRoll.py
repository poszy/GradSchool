#!/usr/bin/python
# Author  : Luis M. Pena
# Course  : JHU Discrete Mathematics Spring '21
# Purpose : Project 3 - Dice Roll Simulator 3000
# Date    : 4/12/21

# Import Libraries
import random
import collections
import sys


class Logger(object):
    """This Is the default constructor for class Logger.

    Args:
        self      = reference to the class itself.
        filename  = the name of the log file that will be generated.

    Returns:
        Nothing
    """

    def __init__(self, filename="output.log"):
        self.terminal = sys.stdout
        self.log = open(filename, "a")

    """This write standard out to the logfile

    Args:
        self      = reference to the class itself.
        message   = the actual text (stdout) being written to the log

    Returns:
        Nothing
    """

    def write(self, message):
        self.terminal.write(message)
        self.log.write(message)


class Die():

    """This Is the default constructor for class Die.

    Args:
        self      = reference to the class itself.

    Returns:
        Nothing
    """

    def __init__(self):
        self.sides = 6
        self.successful_trials = 0

    """This will simulate a die being rolled in a trial and sequence manner.

    Args:
        self      = reference to the class itself.

    Returns:
        self.successful_trials
    """

    def roll_die(self):

        # Dict of friends and their corresponding values.
        # Represents each person by a number on the die.
        friends = {1: 'Alice', 2: 'Bob', 3: 'Charley',
                   4: 'Frank', 5: 'Ellen', 6: 'Don'}

        # Reference to key value pairs. of friends
        friends_list = list(friends.keys())
        friends_value = list(friends.values())

        # This will keep track of each number that has been rolled
        # hence keeping track of who got to choose their resteraunt.
        roll_index_counter = []

        print("***** TRIAL START ***** \n")

        for i in range(12):
            print("------ ROLL START ------ \n")
            dice = random.randint(1, self.sides)

            print("Rolling Die... " + str(dice))

            # If the number rolled is already in the list,
            # AKA the person already got to choose the resteraunt.
            # Skip adding a redundant integer. save some operations.
            if dice in roll_index_counter:
                print(str(friends.get(dice)) +
                      " Already got to choose: Unsuccesful Roll")

            # If number is not in the list then, add it
            # AKA a new person get to choose the resteraunt.
            else:
                roll_index_counter.append(dice)
                print(str(friends.get(dice)) + " Gets to Choose Dinner! ")

            # Keeping track who has chosen the resteraunt, after each roll
            # because having 1000's of trials, the information is need to keep track of whats going on.
            print("People who go to choose so far: " + str(roll_index_counter))

            # Iterate through the list and compare the value inside the friends list.
            # This will print out who got to chose already, by name instead of list index value.
            for c in roll_index_counter:
                print(friends.get(c))

            # Printing out more roll info in trial as it keeps things making sense.
            print("Number of rolls in this Trial: " + str(i + 1))

            # If the list index value in roll_index_counter has the same values as friends_list
            # Then this means we have a successful trial!
            if collections.Counter(friends_list) == collections.Counter(roll_index_counter):
                # Keeping track of how many rolls it took for this specific succesful trial
                print("Everyone has gotten to choose after " +
                      str(i + 1) + " rolls")
                # Add to the succesful trial counter for this series/sequence.
                self.successful_trials = self.successful_trials + 1
                print("TOTAL NUMBER OF SUCCESSFUL TRIALS FOR THIS SERIES: " +
                      str(self.successful_trials))
                # print(self.successful_trials)

                break
            # If there has been 12 rolls and not everyone got to choose
            # Then this is an Unsuccesful trial. the Trial will end here
            # and proceed to the next trial if needed.
            # adding + 1 as indexes start at 0 (11 + 1)
            # using i because its already keeping track of the number of trials in the series.
            elif i + 1 == 12:
                print(
                    "Not everyone has had the chance to choose after 12 rolls. Unsuccesful Trial")

            # else, there has not been 12 rolls.
            # Proceeds to next roll with a bit of info to keep info tidy.
            else:
                print("Not everyone has had the chance to choose.")

            print("\n")
            print("------ ROLL END   ------ \n")

        print("***** TRIAL END ******** \n")

        # Returning succesful trials in all trials in a series/sequence
        #  as its needed for the probability formula.
        return self.successful_trials


class Trial():
    """This Is the default constructor for class Trial

    Args:
        self      = reference to the class itself.
        *args     = This means unlimited arguments. So I can have any number of sequnces. (1,1) or (1,1,1,1,,1) and so on

    Returns:
        Nothing
    """


    def __init__(self, *args):
            self.trial = args
            self.uid = 0

    """This function takes a Die and runs a trial/sequence for it.

    Args:
        self      = reference to the class itself.

    Returns:
        Nothing
    """
    def die_trial(self):

        # trials is the iterator, iterating over the number of *args
        # so iterates over inputs (10,10,10), iterates over each one.
        # runs a trial for each input (trials)
        for trials in self.trial:
            # saving a reference to the origonal stdout
            # This is for the logger.
            original_stdout = sys.stdout

            dice_roll = Die()

            # Using unique id so files dont get overwritten when
            # having a sequnece of the same value. E.g {100,100,100}
            self.uid = self.uid + 1
            filename = "series-{0}-output-{1}.txt".format(trials, self.uid)

            # Simulate the dice roll per specified game amount.

            for i in range(trials):

                # Find probability

                # need to add total rolls, cause sometimes its less than 12
                #total_rolls = trials

                # Run our Dice Trials
                # Printing out works just fine. This is needed for logger class
                # "Hackish"
                sys.stdout = Logger(filename)
                print("Series # " + str(trials))
                print(dice_roll.roll_die())

                # Variabialize the amount of successful trials
                tsuccessful_trials = dice_roll.successful_trials

                # Formula to compute probability. using floats to get a percentage.
                p = float(tsuccessful_trials ) / float(trials)

                # Return percentage and format it two decimal places
                round_up = "{:.2f}".format(p * 100)

                print("Total Probability: " + str(round_up) + " %")

                # Give back the reference to stdout and output.
                # aka stop logging ot file
                sys.stdout = original_stdout

            print(
                "--------------------------------------------------------------------------- Exporting series-{0}-output-{1}.txt".format(trials, self.uid))


# 10 Trials at 1
m_trial = Trial(1, 1, 1, 1, 1,
                1, 1, 1, 1, 1)

m_trial.die_trial()

# 10 trials * 10  = 100 trials
m_trial = Trial(10, 10, 10, 10, 10,
                10, 10, 10, 10, 10)

m_trial.die_trial()


# 100 trials * 10  = 1000 trials
m_trial = Trial(100, 100, 100, 100, 100,
                100, 100, 100, 100, 100)

m_trial.die_trial()

# 1000 trials * 10  = 10000 trials
m_trial = Trial(1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000)

m_trial.die_trial()

# 10,000 * 10 = 100,000 trials
m_trial = Trial(10000, 10000, 10000, 10000, 10000,
                10000, 10000, 10000, 10000, 10000)
m_trial.die_trial()


# 100,000 * 10 = 1,000,000 trials
m_trial = Trial(100000, 100000, 100000, 100000, 100000,
                100000, 100000, 100000, 100000, 100000)
m_trial.die_trial()

# 1,000,000 * 10 = 10,000,000 trials
# m_trial = Trial(1000000, 1000000, 1000000, 1000000, 1000000,
#                 1000000, 1000000, 1000000, 1000000, 1000000)
# m_trial.die_trial()


# Other Test 
# m_trial = Trial(3, 3, 3,)
# m_trial.die_trial()
#
#
# m_trial = Trial(4, 4, 4,)
# m_trial.die_trial()
#
#
# m_trial = Trial(5, 5, 5,)
# m_trial.die_trial()
#
#
# m_trial = Trial(7, 7, 7,)
# m_trial.die_trial()
