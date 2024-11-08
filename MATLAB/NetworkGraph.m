% An algorithm to create a directed network graph
% Created by Ylldrit Miftari

% Source Node
x = {'A' 'A' 'B' 'B' 'B' 'C' 'C' 'D' 'D' 'D' 'E' 'E' 'E' 'F' 'F'};

% Target Node
y = {'B' 'D' 'C' 'D' 'F' 'A' 'F' 'B' 'E' 'F' 'A' 'B' 'C' 'A' 'C'};

% Distance between source node and target node e.g. A -> B is 22
weights = {'22', '16', '19', '17', '31', '37', '34', '17', '32', '24', ...
    '14', '18', '16', '16', '34'};

% Create a directed graph - 
% arguments x and y are the source node and target node
H = digraph(x,y);

% Plot the graph, labelling the edges as well
I = plot(H, 'EdgeLabel', weights)

I.NodeColor = 'r'; % Nodes are highlighted in red

title('Ylldrit''s Directed Network Graph') % Display title of the graph
