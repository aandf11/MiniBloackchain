This program is for part E-F. When user started up all three consoles. 
the message "Hello multicast message from Process 0" will be printed on the console of each process.
Also, the message "Hello multicast message from Process [N]" will be printed on each console.

example output:

>java ServerClient 0

Process: 0 is running
Creating 4710 (Public Key Server)
Starting Key Server input thread using 4710
Creating 4820 (Second Server)
Starting Second Server input thread using 4820
Creating 4930 (BlockChain Server)
Starting Block Chain Server input thread using 4930
Hello multicast message from Process 0
Hello multicast message from Process 1
Hello multicast message from Process 2


>java ServerClient 1

Process: 1 is running
Creating 4711 (Public Key Server)
Creating 4821 (Second Server)
Creating 4931 (BlockChain Server)
Starting Second Server input thread using 4821
Starting Block Chain Server input thread using 4931
Starting Key Server input thread using 4711
Hello multicast message from Process 0
Hello multicast message from Process 1
Hello multicast message from Process 2

>java ServerClient 2

Process: 2 is running
Creating 4712 (Public Key Server)
Creating 4822 (Second Server)
Creating 4932 (BlockChain Server)
Starting Second Server input thread using 4822
Starting Block Chain Server input thread using 4932
Starting Key Server input thread using 4712
Hello multicast message from Process 0
Hello multicast message from Process 1
Hello multicast message from Process 2