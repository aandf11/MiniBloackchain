For Part G, my program will generates a public key / private key pair,
 converts the public key to a string and then to a JSON string, 
writes it to disk as JSON, reads it back in, 
coverts it back to a binary-format valid public key, 
and checks that you can still encrypt with the secret key (sign some data) 
and decrypt with the public key (veryify the signature) that you previously wrote out to disk in JSON format.

(I have tried to make it run for three different processes, but the result comes out to be the same.) 

Example output:


Starting to generate key pair

Endcoded PublicKey for: Process0 : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

Has the signature been verified: true

Key in String form: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

JSON (suffled) String list is: {
  "PbKey": "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB"
}
The Read In Encoded Public Key : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

The signed SHA-256 string: A5P77qYVhqiaAfLAedqwIbKr6yfXJZmxG2Qcz8DIG+43oNiigR/1nGn7BJarzFg1xtHw9eQPQ+Yr1jvWMKbM931Z1e80x6u1lpUHDhZCQqgYwUIKLeS8RJqTa6sqpzeIx+teLWBd24PsRNl3RhVu2CWCihQSqUwbWctGT4TLI68=

Testing restore of signature: true
Has the restored signature been verified: true

=============================================================================

Starting to generate key pair

Endcoded PublicKey for: Process1 : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

Has the signature been verified: true

Key in String form: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

JSON (suffled) String list is: {
  "PbKey": "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB"
}
The Read In Encoded Public Key : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

The signed SHA-256 string: A5P77qYVhqiaAfLAedqwIbKr6yfXJZmxG2Qcz8DIG+43oNiigR/1nGn7BJarzFg1xtHw9eQPQ+Yr1jvWMKbM931Z1e80x6u1lpUHDhZCQqgYwUIKLeS8RJqTa6sqpzeIx+teLWBd24PsRNl3RhVu2CWCihQSqUwbWctGT4TLI68=

Testing restore of signature: true
Has the restored signature been verified: true

=============================================================================

Starting to generate key pair

Endcoded PublicKey for: Process2 : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

Has the signature been verified: true

Key in String form: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

JSON (suffled) String list is: {
  "PbKey": "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB"
}
The Read In Encoded Public Key : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIOOqhX3YqpQHLLgiForNKnUOxJMd7S66jog6XSGYmB6mmKTe8UdF9lcnHiL8KNl0cNimw/b5v4DSrd8gdYARygs27P9SYHwNg8UIYhl0HxkDW2OaH1xc0YabHtCuhWnQ2SW/+XDdNqsGo6b0dmgKPWMKPoxQNtL3MXued5YSNQIDAQAB

The signed SHA-256 string: A5P77qYVhqiaAfLAedqwIbKr6yfXJZmxG2Qcz8DIG+43oNiigR/1nGn7BJarzFg1xtHw9eQPQ+Yr1jvWMKbM931Z1e80x6u1lpUHDhZCQqgYwUIKLeS8RJqTa6sqpzeIx+teLWBd24PsRNl3RhVu2CWCihQSqUwbWctGT4TLI68=

Testing restore of signature: true
Has the restored signature been verified: true

=============================================================================
