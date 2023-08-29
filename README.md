# Making Photos From Binary Numbers
Making Photos From Binary Numbers is a Java project that focuses on decoding binary messages using a custom binary tree structure implemented in the MsgTree.java class.

MsgTree.java
MsgTree.java defines a class that creates a binary tree for decoding binary messages. The binary tree structure is used to decode binary-encoded messages.

Features
Binary Tree Decoding: Utilizes a binary tree to efficiently decode binary messages.
Recursive Tree Construction: Recursively constructs the binary tree from an encoding string.
Efficient Decoding Algorithm: Provides an algorithm to decode binary messages using the constructed binary tree.
Usage
Instantiate the MsgTree class with an encoding string to create a binary tree for decoding messages.
Use the decode method in the MsgTree class to decode binary messages using the constructed binary tree.

Example
// Decoding binary message using MsgTree
String encodingString = "^8^ ^Z^^^\n^^`^^^^ro^xu^^i]^nmW^'^M-^,^^ba^\"^P^^<^)^VN^(^e>^^l^.^_^d^Y^|@^I;\n...";
MsgTree tree = new MsgTree(encodingString);
String binaryMessage = "1010101010101010101010101010101010101010101010101010101010101010101111011011110110...";
tree.decode(tree, binaryMessage);
