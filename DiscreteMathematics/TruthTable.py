#!/usr/bin/python
# Author  : Luis M. Pena
# Course  : JHU Discrete Mathematics Spring '21
# Purpose : Project 2 - Truth Table Bonanza Party Palooza
# Date    : 3/9/21

# Import Libraries
import itertools
import sys


def input_int(integer):
    """This function takes in a single integer and checks to see if its a valid integer within a range.

    Args:
        integer  = a number greater than zero and less than or equal to 7.

    Returns:
        integer: if valid
    """
    # Check if given integer is 0 < n <= 7
    if integer > 0 and integer <= 7:
        return integer, True

    else:
        print("Please enter and integer where n, 0 < n <= 7")
        return False


def check_biconditional(x_value, y_value):
    """This function takes in two list of booleans and computes a biconditional list.

    Args:
        x_value  = a list of booleans
        y_value  = a list of booleans

    Returns:
        biconditional_list: The computated and compared values of x_value and y_value
    """
    # Declaring empty list to store computed values
    biconditional_list = []

    # Iterate through each value from both lists
    for x, y in zip(x_value, y_value):

        # If the values in each index of both list are not equal to each other
        # Return False.
        if x != y or y != x:
            biconditional_list.append(False)
        # If the vlaues in each index of both list ARE equal, return true.
        # Regardsless if both booleans are True or False.
        else:
            biconditional_list.append(True)

    # Finally return the computed list.
    return biconditional_list


def check_triconditional(x_value, y_value, z_value):
    """This function takes in three list of booleans and computes a triconditional list.

    Args:
        x_value  = a list of booleans
        y_value  = a list of booleans
        z_value  = a list of booleans

    Returns:
        triconditional_list: The computated and compared values of x_value, y_value and z_value

    Notes:
        x => (y<=>z) can be translated to x => ( (y <=>z) and (z <=> y) )
        This will allow us to focus on the right side and compute the biconditional values y <=> z first.
        Left  Side  : x_value
        Right Side  : y_value + z_value
    """
    # Get bicondtional values of y and z first and add them to a list called right_side
    right_side = check_biconditional(y_value, z_value)

    # check if results from right side and left side (x_value)
    # We can do this again by calling check_biconditional.
    triconditional_list = check_biconditional(x_value, right_side)

    # Finally return the computed list.
    return triconditional_list


def check_double_biconditional(x_value, y_value, z_value):
    """This function takes in three list of booleans and computes a double biconditional list.

    Args:
        x_value  = a list of booleans
        y_value  = a list of booleans
        z_value  = a list of booleans

    Returns:
        double_biconditional_list: The computated and compared values of x_value, y_value and z_value.
                                    in terms of  X <=> Y ^ Y <=> Z

    Notes:
        x => (y <=> z) can be translated to x => ( (y <=> z) and (z <=> y) )
        x <-> y) and (y <-> z)  can be loosley translated to (x -> y) and (y -> x) and (y -> z) and (z -> y)
        comparing two biconditional statements is not needed, by checking if each index is all true or all false.
    """

    # Declaring empty list to store computed values
    double_biconditional_list = []

    # Iterate through each value from all three lists
    for x, y, z in zip(x_value, y_value, z_value):

        # If all Indexes are not the same, then append false
        # (x -> y) and (y -> x) and (y -> z) and (z -> y)
        if x != y or y != x and y != z or z != y:
            double_biconditional_list.append(False)

        # else append True as the condition is met.
        else:

            double_biconditional_list.append(True)

    # Finally return the computed list.
    return double_biconditional_list


def format_table(titles, values):
    """This function takes in a list of titles and the final list that contains the final truth table. Depending on which integer was entered.

    Args:
        titles   = a string list of 'titles' that will be used for the table header.
        values   = a list of booleans

    Returns: Nothing, only prints out results.

    """
    # Iterate through values list and format the table
    for i, j in enumerate(values):

        # line will create the table sides on the right side
        line = '| '.join(str(x).ljust(14) for x in j)

        # print out the right sides of the tables for every list
        print(line)

        # Print the bottom part of the header under the titles if i = 0
        if i == 0:
            print('-' * len(line))


def build_table(x):
    """This function takes in two parameters through a variable x. the two parameters are from the input_int function.

    Args:
        x[0]   = The integer entered from input_int
        x[1]   = The Boolean that determines if x[0] is a valid integer for our use case.

    Returns: the final built truth table.

    """

    # Allocate Variables
    # This will produce the cartisian product for T & F and generate the truth table for
    # 3 Tables. These list will be parsed for boolean comparion later.
    n_value = list(itertools.product([True, False], repeat=3))

    # Lists that store the values of the single non computed lists.
    p_value = []
    q_value = []
    r_value = []

    # Pipe True and False booleans into their respected lists, regardless of input.
    # This is done so the table has the values from left to right and can be used to compute conditionals easier.
    for i in n_value:
        # since n_value is a list that has three list in it
        # We can get the correct values by appeneding each first index of each item list in the n_value list.
        p_value.append(i[0])
        q_value.append(i[1])
        r_value.append(i[2])

    # Making these global to avoid retyping.
    # This is fine as they never change.
    p_bi_q = check_biconditional(p_value, q_value)
    q_bi_r = check_biconditional(q_value, r_value)
    pq_and_qr = check_double_biconditional(p_value, q_value, r_value)
    p_q_r_bicondition = check_triconditional(p_value, q_value, r_value)

    # Control flow that will display the value of X (digit entered)
    # and its corresponding n elements of the Truth table
    # x[0] value is the first returned parameter of input_int, which is an int
    # x[1] value is the second returned parameter of input_int, which is a boolean to make sure an int was enetered.

    try:
        if x[0] == 1 and x[1] == True:

            # Set Titles
            titles = ['P']
            # Make data be the combinations of titles and values of a given truth table
            data = [titles] + list(zip(p_value))

            # format them into a variable to be returned
            final_table = format_table(titles, data)

            return final_table

        elif x[0] == 2 and x[1] == True:

            titles = ['P', 'Q']
            data = [titles] + list(zip(p_value, q_value))
            final_table = format_table(titles, data)

            return final_table

        elif x[0] == 3 and x[1] == True:

            titles = ['P', 'Q', 'R']
            data = [titles] + list(zip(p_value, q_value, r_value))
            final_table = format_table(titles, data)

            return final_table

        elif x[0] == 4 and x[1] == True:

            titles = ['P', 'Q', 'R', 'P <=> Q']
            data = [titles] + list(zip(p_value, q_value, r_value, p_bi_q))
            final_table = format_table(titles, data)

            return final_table

        elif x[0] == 5 and x[1] == True:

            titles = ['P', 'Q', 'R', 'P <=> Q', 'Q <=> R']
            data = [titles] + \
                list(zip(p_value, q_value, r_value, p_bi_q, q_bi_r))
            final_table = format_table(titles, data)

            return final_table

        elif x[0] == 6 and x[1] == True:

            titles = ['P', 'Q', 'R', 'P <=> Q', 'Q <=> R', 'P <=> Q <=> R']
            data = [titles] + list(zip(p_value, q_value,
                                       r_value, p_bi_q, q_bi_r, p_q_r_bicondition))
            final_table = format_table(titles, data)

            return final_table

        elif x[0] == 7 and x[1] == True:

            titles = ['P', 'Q', 'R', 'P <=> Q', 'Q <=> R',
                      'P <=> Q <=> R', 'P <=> Q ^ Q <=> R']
            data = [titles] + list(zip(p_value, q_value, r_value,
                                       p_bi_q, q_bi_r, p_q_r_bicondition, pq_and_qr))
            final_table = format_table(titles, data)
            return final_table
    except:
        print("Input cannot be parsed. Exit 0")


def compute():

    # Checking to see if input is a valid integer and not a string
    # If is anything else but a integer, ask again.
    # if int proceed to function
    print("Truth Table Generator that will compute the values of 7 different truths. \n")
    print("Please enter valid option below by entering an integer from 1 - 7  ")
    print("___________________________________________________________")
    print('''
        Options:
        1 - Prints out P Truth table
        2 - Prints out P + Q Truth table
        3 - Prints out P + Q + R Truth table
        4 - Prints out P + Q + R + P <=> Q Truth table
        5 - Prints out P + Q + R + P <=> Q + Q <=> R Truth table
        6 - Prints out P + Q + R + P <=> Q + Q <=> R + P <=> Q <=> R Truth table
        7 - Prints out P + Q + R + P <=> Q + Q <=> R + P <=> Q <=> R + P <=> Q ^ Q <=> R
    '''
          )
    print("For Example: entering 2 will produce:")
    print("_____________________________________")
    print('''

                    P             | Q
            ------------------------------
            True          | True
            True          | True
            True          | False
            True          | False
            False         | True
            False         | True
            False         | False
            False         | False
    ''')
    while True:
        try:
            print("Please enter valid integer, where n is a number, 0 < n =< 7")
            print("___________________________________________________________")
            final_input = int(input())
            break
        except:
            print("Strings and or special characters are not allowed \n")

    # use our integer from menu options to build table
    testcase = input_int(final_input)

    build_table(testcase)

    # Redirecting standard output to text file
    # Testin on Unix
    stdoutOrigin = sys.stdout
    sys.stdout = open("output.txt", "w")

    # calling again to pipe to output.txt
    build_table(testcase)

    sys.stdout.close()
    sys.stdout = stdoutOrigin

    print("___________________________________________________________ \n")
    print("Done!... Generated Truth Table has been exported to ./output.txt")


"""
Uncomment these test cases to test manually.
"""

#testcase1 = input_int(1)
#gen_table = build_table(testcase1)
#
#testcase2 = input_int(2)
#gen_table = build_table(testcase2)
#
#testcase3 = input_int(3)
#gen_table = build_table(testcase3)
#
#testcase4 = input_int(4)
#gen_table = build_table(testcase4)
#
#testcase5 = input_int(5)
#gen_table = build_table(testcase5)
#
#testcase6 = input_int(6)
#gen_table = build_table(testcase6)

#testcase7 = input_int(7)
#gen_table = build_table(testcase7)

compute()
