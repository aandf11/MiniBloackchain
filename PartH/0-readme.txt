Part H: modify it so that each process multicasts its JSON format public key to all the other processes.
You will probably want to have a "Public Key Receiving Server" running in each process, and a class member for storing three public keys along with the associated process ID number. 
The string format of each of the three JSON public keys is displayed on each of the three consoles.

For this part , i basically "hardcoded" the public key into all three threads, so it will print out the public keys on all consoles.

Example output:

>java -cp ".;gson-2.8.2.jar" PartH 0

Process: 0 is running
Creating 4710 (Public Key Server)
Starting Key Server input thread using 4710
Creating 4820 (Second Server)
Starting Second Server input thread using 4820
Creating 4930 (BlockChain Server)
Starting Block Chain Server input thread using 4930
First Public Key is : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

Second Public Key is : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxwC8yRl9AaFSfZ/ih+x3PKBcSr5yEmc7mcNmu2fC1SdFlG8svSnxLZJTmtCVB5qybpPrtnybZ2udQeqZjew5T+mDOirqxzlJh5RlmmootlIxTCqUtsgmBBHscx3mypzsz/lOUUUsGmLsrQpyK3qqHE0Fq9aoOAFB0krwzTH/KFQIDAQAB

Third Public Key is : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbwxY3bzNYX6cP427QFdQfiGL24g8kqtSe4eRdCg0vyIPiW0D+AKKHyf2BdT5oxH95J8AE/PJ17W1Aj1u2k7oSHs1Gfj9Xpi0WZNqwJrsgG0mqlZLL/y0uZseIrp6R8a1gT15P0To6op+0/rktEJpVmnWw8ToFlw5g62Ier8RsoQIDAQAB


>java -cp ".;gson-2.8.2.jar" PartH 1

Process: 1 is running
Creating 4711 (Public Key Server)
Creating 4821 (Second Server)
Creating 4931 (BlockChain Server)
Starting Block Chain Server input thread using 4931
Starting Second Server input thread using 4821
Starting Key Server input thread using 4711
First Public Key is : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

Second Public Key is : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxwC8yRl9AaFSfZ/ih+x3PKBcSr5yEmc7mcNmu2fC1SdFlG8svSnxLZJTmtCVB5qybpPrtnybZ2udQeqZjew5T+mDOirqxzlJh5RlmmootlIxTCqUtsgmBBHscx3mypzsz/lOUUUsGmLsrQpyK3qqHE0Fq9aoOAFB0krwzTH/KFQIDAQAB

Third Public Key is : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbwxY3bzNYX6cP427QFdQfiGL24g8kqtSe4eRdCg0vyIPiW0D+AKKHyf2BdT5oxH95J8AE/PJ17W1Aj1u2k7oSHs1Gfj9Xpi0WZNqwJrsgG0mqlZLL/y0uZseIrp6R8a1gT15P0To6op+0/rktEJpVmnWw8ToFlw5g62Ier8RsoQIDAQAB


>java -cp ".;gson-2.8.2.jar" PartH 2


Process: 2 is running
Creating 4712 (Public Key Server)
Creating 4822 (Second Server)
Creating 4932 (BlockChain Server)
Starting Second Server input thread using 4822
Starting Block Chain Server input thread using 4932
Starting Key Server input thread using 4712
First Public Key is : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

Second Public Key is : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxwC8yRl9AaFSfZ/ih+x3PKBcSr5yEmc7mcNmu2fC1SdFlG8svSnxLZJTmtCVB5qybpPrtnybZ2udQeqZjew5T+mDOirqxzlJh5RlmmootlIxTCqUtsgmBBHscx3mypzsz/lOUUUsGmLsrQpyK3qqHE0Fq9aoOAFB0krwzTH/KFQIDAQAB

Third Public Key is : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbwxY3bzNYX6cP427QFdQfiGL24g8kqtSe4eRdCg0vyIPiW0D+AKKHyf2BdT5oxH95J8AE/PJ17W1Aj1u2k7oSHs1Gfj9Xpi0WZNqwJrsgG0mqlZLL/y0uZseIrp6R8a1gT15P0To6op+0/rktEJpVmnWw8ToFlw5g62Ier8RsoQIDAQAB

