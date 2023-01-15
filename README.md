# Huffman_codes
This is a compressing and decompressing program that uses huffman codes greedy algorithm to compress the file.
it works with any type of files.
It starts with taking the input bytes in chunks each of 5000 kb so that small sized files will be processed in one chunk while large sized files will be processed in many chunks.
For each chunk it applies the huffman algorithm to encode the byes, first get the frequencies, generate the huffman tree and then encode the input file.
To make sure that no extra space is used while storing the encoded bits, It accumulates the bits in the class OutputWriter, and writes them with an output buffer only when the bits reaches 1 byte.
Each chunk is stored along with its preorder traversal of its tree in bits, each nonLeaf node is represented with 0 while leaf nodes are represented with 1 + the binary representation of its data, some other information are included in the header as the tree length, the chunk length, number of leading zeroes of the last byte for both tree and data bytes.
In the decoding phase, for each chunk it extracts the preorder traversal representation then regenerates the huffman tree, and then extracts the data bytes and decodes them using the tree.
