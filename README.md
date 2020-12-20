# MiniBloackchain

This is my mini blockchain. I have most of the tasks implemented, but is still missing some functions. 
Please read the "0-readme.txt" inside each file to know more about the programs. Thank you.


Blockchain development mini-project suggestions

    A. Create a named subdirectory with a 0-readme.txt file in it. In a single process, create a blockchain with four nodes in it: a dummy block zero, and three other simple blocks with a small amount of data in them. Demonstrate that you can convert the Java data contained in the simple block you have designed into string format, and that you can concatenate the "three elements" together, hash them, and come up with a result that is used to "verify" each block. In this simple version you can, e.g., make the puzzle so easy that it is solved every time. We will worry about work later. Use the Proof-of-Work field from the previous block as part of the data in the subsequent block.

    A2. Once the simple version is running, add some other data components of a block such as a timestamp, a block number, a block UUID and so on. When this is all working, congratulations, you have a simple blockchain.

    B. [In a new subdirectory]. Copy the previous code, and translate your entire rudimentary blockchain into JSON format and write it to disk.

    C. [New subdirectory, etc. each time...] Copy the previous code from [B] above. Add more code that reads the file back in, restore the JSON to Java objects. Work through your simple blockchain and print out the label from each block in the blockchain.

    D. Modify the utility code to create a standalone piece of code that accepts a command line argument designating which process ID (0, 1, or 2) this process is using. Get all three processes starting up, and then printing out "Hello from Process [N]" on the console.

    E. Copy the previous code and extend it so that after each process settles (using sleep statements?), process 0 (or other) multicasts the message "Hello multicast message from Process 0" which is then printed on the console of each process.

    F. Copy and extend the previous code so that EACH processes multicasts the message "Hello multicast message from Process [N]" and then print each of these three messages on each console.

    G. Write some standalone code similar to [C] above that generates a public key / private key pair, converts the public key to a string and then to a JSON string, writes it to disk as JSON, reads it back in, coverts it back to a binary-format valid public key, and checks that you can still encrypt with the secret key (sign some data) and decrypt with the public key (veryify the signature) that you previously wrote out to disk in JSON format.

    H. Copy and extend the previous code in [F] above and modify it so that each process multicasts its JSON format public key to all the other processes. You will probably want to have a "Public Key Receiving Server" running in each process, and a class member for storing three public keys along with the associated process ID number. The string format of each of the three JSON public keys is displayed on each of the three consoles.

    I. Write a standalone program that implements your (sleep-enhanced) work algorithm. Note that this alrogithm should do real work, but make it a very easy puzzle and then add a sleep statement to fake making it harder. E.g.: you can solve the puzzle one time in three on average, but you sleep one second each time. Add some comments about how you would make the work harder if you wanted to. Combine this with your blockchain code from [A] above so that you actually produce a hash value of your block and verify the block using your work . Create a full blockchain of four or five blocks with hard-coded fake data, but which has verified blocks in it.

    J. Using the techniques from above, copy and combine code so that you multicast your blockchain (with fake data) in JSON format from each process to each other process. You will probably want to have a "blockchain receiving server" (along with your "public key receiving server" running at each process). On each process, print out some identifier of each first block of each blockchain on the console.

    K. Modify the utility code so that you have a standalone program that reads in all of the data for each respective process. Create an unverified block for each line of data in the data file.

    L. Extend the previous code in [K] combined with other previous code so that you translate each of the four unverified blocks into JSON format and multicast them to each of the other processes in the system. You will probably want to have an "unverified block receiving server" running in each process. Print out the name of the patient for each block received on the console of each process. (You will print out twelve names on each console.) Translate each JSON block-object into a regular Java block-object.

    M. In a standalone program, implement a priority queue. Create a couple of fake unverified blocks with timestamps. Insert the blocks into the priority queue, sorted (queued) by timestamp priority.

    N. Combine [M] above with [L] above so that four timestamped unverified blocks are multicast from each process 0, 1 and 2 to each of the other processes. Receive them with your "unverified block receiving server" and place them into your priority queue in each process. After you receive all the blocks, pop unverified blocks off the queue and print the name of each patient on the console of each process. You should have twelve names on each each console all printed in the same order.

    O. (If you want) Rewrite the above code so that your "unverified block receiving server" acts as a producer process and you have a separate thread that acts as a consumer that pops off the blocks and writes the names of each patient on the console. In this version it is possible that the order of the patient names will be different on the different consoles, depending on what gets popped when, and what gets recieved when.

    P. Combine your work algorithm with [O] above so that when a block is popped off the queue it is verified.

    Q. Combine [P] with other previous code so that verified blocks are prepended to your blockchain, and then the new blockchain is multicast to each of the processes. You will start to see some intresting behavior in the order that blocks are verified and blockchains created.
