#!/usr/bin/python
# Author  : Luis M. Pena
# Course  : JHU Discrete Mathematics Spring '21
# Purpose : Show & Tell - Huffman Encoding
# Date    : 4/24/21
class NodeTree(object):

    """This class creates a Huffman Coding tree
    Args:
        None:

    Returns: Nothing

    """

    def __init__(self, left=None, right=None):
        self.left = left
        self.right = right

    def children(self):
        return (self.left, self.right)

    def nodes(self):
        return (self.left, self.right)

    def __str__(self):
        return '%s_%s' % (self.left, self.right)

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


def read_file(filename):
    """This function takes in a list of titles and the final list that contains the final truth table. Depending on which integer was entered.

    Args:
       filename = filename to be read from local path.

    Returns: the lines in a file via string

    """
    with open(filename, 'r') as file:
        data = file.read().replace('\n', '')
    return str(data)


def huffman_code_tree(node, left=True, binary_value=''):
    """This function takes in a list of titles and the final list that contains the final truth table. Depending on which integer was entered.

    Args:
        node   = nodes in the tree
        left   = left direction boolean
        binary_value = binary placeholder

    Returns: Dictionary with key value pairs of nodes -> binary value

    """
    # Check if the node has a string
    if type(node) is str:
        return {node: binary_value}

    # Set the left and right nodes equal to
    # whatever childern node is being added
    (l, r) = node.children()

    # Create new dictionary to hold our values
    # For each node
    dictionary = dict()
    dictionary.update(huffman_code_tree(l, True, binary_value + '0'))
    dictionary.update(huffman_code_tree(r, False, binary_value + '1'))
    return dictionary



def encode(text,output):

    # Read lines of the text file
    text_input = read_file(text)
    orgional_text = text_input
    # List to hold our values
    # easier to manage values if shoved in lists
    character_list=[]
    binary_value_list=[]

    # Keep track of each letters frequency in a list
    frequency = {}
    for char in text_input:

        # Add value to character_list
        if char in frequency:
            frequency[char] = frequency[char] + 1
        else:
            frequency[char] = 1


    # Sort the frequency list by
    # most frequent letters printed last
    frequency = sorted(frequency.items(), key=lambda x: x[1], reverse=True)


    nodes = frequency

    while len(nodes) > 1:
        (key1, c1) = nodes[-1]
        (key2, c2) = nodes[-2]
        nodes = nodes[:-2]
        node = NodeTree(key1, key2)
        nodes.append((node, c1 + c2))

        nodes = sorted(nodes, key=lambda x: x[1], reverse=True)

    huffman_encode = huffman_code_tree(nodes[0][0])




    # iterate through frequnciess to get values of
    # Characters and their binary values
    for (char, frequencies) in frequency:
        character_list.append(char)
        binary_value_list.append(huffman_encode[char])

        # Add frequencies to output if
        # they appear more than once.

        if frequencies > 1:
            character_list.append(char)
            binary_value_list.append(huffman_encode[char])
            #print(char)


    titles = ['Character', 'Huffman Encoding']
    data = [titles] + list(zip(character_list, binary_value_list))
    format_table(titles, data)
    print("\n")



    # Exctracting encoding back to origional text
    final_dict={}

    for (b,c) in zip(character_list,binary_value_list):
        final_dict[b] =c

    output_text = open(output, "w")
    for x in orgional_text:
        output_text.write(final_dict.get(x, x))
    output_text.close()

    final_string=read_file(output)
    print("Original text length %s" % len(orgional_text))
    print("Original text is: " + orgional_text + "\n" + "Requires %s Bits or" % len(orgional_text * 8) + " (%d bytes)" % ((len(orgional_text))))

    print("\n")
    print("Encoded text is: " + final_string)
    print("Requires %d bits or (%d bytes)" % (len(final_string), (len(final_string)+7)/8))


encode("input.txt","output.txt")
encode("input2.txt","output2.txt")
