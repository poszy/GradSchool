#!/usr/bin/python
# Author  : Luis M. Pena
# Course  : JHU Discrete Mathematics Spring '21
# Purpose : Homework 12 - Dikstras Algo
# Date    : 4/12/21

class GraphEdge():
  def __init__(self, node, distance):
    self.node = node
    self.distance = distance


class GraphNode():
  def __init__(self,val):
    self.value = val
    self.edges = []

  def add_child(self, node, distance):
    self.edges.append(GraphEdge(node,distance))

  def remove_chld(self, del_node):
    if del_node in self.edges:
      self.edges.remove(del_node)


class Graph():
  def __init__(self, nodes_list):
    self.nodes = nodes_list

  def add_edge(self, node1, node2, distance):
    if node1 in self.nodes and node2 in self.nodes:
      node1.add_child(node2, distance)
      node2.add_child(node1, distance)

  def remove_edge(self, node1, node2):
    if node1 in self.nodes and node2 in self.nodes:
      node1.remove_child(node2)
      node2.remove_child(node1)

node_a = GraphNode('A')
node_b = GraphNode('B')
node_c = GraphNode('C')
node_d = GraphNode('D')
node_e = GraphNode('E')
node_f = GraphNode('F')
node_g = GraphNode('G')

node_h = GraphNode('H')
node_i = GraphNode('I')
node_j = GraphNode('J')
node_k = GraphNode('K')
node_l = GraphNode('L')
node_m = GraphNode('M')
node_n = GraphNode('N')

node_o = GraphNode('O')
node_p = GraphNode('P')
node_q = GraphNode('Q')
node_r = GraphNode('R')
node_s = GraphNode('S')
node_t = GraphNode('T')


graph = Graph([node_a, node_b, node_c, node_d, node_e, node_f, node_g,
                node_h, node_i, node_j, node_k, node_l, node_m, node_o,
                node_p, node_q, node_r, node_s, node_t])

graph.add_edge(node_a, node_b, 5)
graph.add_edge(node_a, node_g, 2)
graph.add_edge(node_a, node_f, 4)

graph.add_edge(node_b, node_c, 4)
graph.add_edge(node_b, node_h, 1)
graph.add_edge(node_b, node_f, 3)

graph.add_edge(node_c, node_d, 3)
graph.add_edge(node_c, node_i, 7)
graph.add_edge(node_c, node_h, 5)


graph.add_edge(node_d, node_e, 2)
graph.add_edge(node_d, node_i, 7)
graph.add_edge(node_d, node_j, 5)

graph.add_edge(node_e, node_d, 2)
graph.add_edge(node_e, node_j, 2)

graph.add_edge(node_f, node_a, 4)
graph.add_edge(node_f, node_g, 2)
graph.add_edge(node_f, node_k, 3)

graph.add_edge(node_g, node_a, 2)
graph.add_edge(node_g, node_b, 3)
graph.add_edge(node_g, node_h, 4)
graph.add_edge(node_g, node_f, 2)
graph.add_edge(node_g, node_l, 4)

graph.add_edge(node_h, node_b, 1)
graph.add_edge(node_h, node_c, 5)
graph.add_edge(node_h, node_g, 4)
graph.add_edge(node_h, node_l, 3)
graph.add_edge(node_h, node_m, 2)
graph.add_edge(node_h, node_i, 3)

graph.add_edge(node_i, node_c, 7)
graph.add_edge(node_i, node_d, 7)
graph.add_edge(node_i, node_j, 5)
graph.add_edge(node_i, node_h, 3)
graph.add_edge(node_i, node_m, 3)
graph.add_edge(node_i, node_n, 2)

graph.add_edge(node_j, node_d, 5)
graph.add_edge(node_j, node_e, 2)
graph.add_edge(node_j, node_i, 5)
graph.add_edge(node_j, node_n, 5)
graph.add_edge(node_j, node_o, 2)

graph.add_edge(node_k, node_f, 3)
graph.add_edge(node_k, node_g, 2)
graph.add_edge(node_k, node_l, 3)
graph.add_edge(node_k, node_q, 4)
graph.add_edge(node_k, node_p, 2)

graph.add_edge(node_l, node_k, 3)
graph.add_edge(node_l, node_g, 4)
graph.add_edge(node_l, node_h, 3)
graph.add_edge(node_l, node_m, 4)
graph.add_edge(node_l, node_q, 1)
graph.add_edge(node_l, node_r, 4)


graph.add_edge(node_m, node_h, 2)
graph.add_edge(node_m, node_l, 4)
graph.add_edge(node_m, node_i, 3)
graph.add_edge(node_m, node_n, 3)
graph.add_edge(node_m, node_r, 2)
graph.add_edge(node_m, node_s, 3)

graph.add_edge(node_n, node_i, 2)
graph.add_edge(node_n, node_m, 3)
graph.add_edge(node_n, node_s, 2)
graph.add_edge(node_n, node_t, 1)
graph.add_edge(node_n, node_o, 1)
graph.add_edge(node_n, node_j, 5)

graph.add_edge(node_o, node_j, 2)
graph.add_edge(node_o, node_n, 1)
graph.add_edge(node_o, node_t, 2)


graph.add_edge(node_p, node_k, 2)
graph.add_edge(node_p, node_q, 2)

graph.add_edge(node_q, node_p, 2)
graph.add_edge(node_q, node_l, 1)
graph.add_edge(node_q, node_k, 4)
graph.add_edge(node_q, node_r, 4)

graph.add_edge(node_r, node_q, 4)
graph.add_edge(node_r, node_l, 4)
graph.add_edge(node_r, node_m, 2)
graph.add_edge(node_r, node_s, 1)

graph.add_edge(node_s, node_r, 1)
graph.add_edge(node_s, node_m, 3)
graph.add_edge(node_s, node_n, 2)
graph.add_edge(node_s, node_t, 2)

graph.add_edge(node_t, node_s, 2)
graph.add_edge(node_t, node_n, 1)
graph.add_edge(node_t, node_o, 2)



import math

def dalgo(start_node, end_node):
   distance_dict = {node: math.inf for node in graph.nodes}
   shortest_path_to_node = {}

   distance_dict[start_node] = 0
   while distance_dict:

    # Pop the shorest path
     current_node, node_distance = sorted(distance_dict.items(), key=lambda x: x[1])[0]
     shortest_path_to_node[current_node] = distance_dict.pop(current_node)

     for edge in current_node.edges:
       if edge.node in distance_dict:
         new_node_distance = node_distance + edge.distance
         if distance_dict[edge.node] > new_node_distance:
           distance_dict[edge.node] = new_node_distance


   return shortest_path_to_node[end_node]





print('Shortest Distance from {} to {} is {}'.format(node_a.value, node_t.value, dalgo(node_a, node_t)))
