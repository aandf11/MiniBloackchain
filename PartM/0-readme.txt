Part M :implement a priority queue. Create a couple of fake unverified blocks with timestamps. 
Insert the blocks into the priority queue, sorted (queued) by timestamp priority.



Example output:

The Priority block chain (sorted (queued) by timestamp priority): 
[
  {
    "hash": "af77c5917c82bb86b0dfd05df572a2f31c16b7d995833b7e386f094d1abdc6d8",
    "previousHash": "0",
    "data": "First block",
    "TimeStamp": "1604540964728"
  },
  {
    "hash": "c385e385f57f4723451dd9b8aaa9c744afc06b23ba213d2141347829908bc162",
    "previousHash": "7031af024a31b79d8c9706c6b025a6e76c2504d417697f85380eae05410fde25",
    "data": "Second block",
    "TimeStamp": "1604540964728"
  },
  {
    "hash": "df79e93405254f651f6f0afa50b4ef9103c3efae6f64b5112f8728115e870c9c",
    "previousHash": "7031af024a31b79d8c9706c6b025a6e76c2504d417697f85380eae05410fde25",
    "data": "Third block",
    "TimeStamp": "1604540964728"
  },
  {
    "hash": "7c7d639a3e764cc12c5be93ecef00d0ef5d62deaf52cae1591dc9cd21ac76f00",
    "previousHash": "7031af024a31b79d8c9706c6b025a6e76c2504d417697f85380eae05410fde25",
    "data": "Fourth block",
    "TimeStamp": "1604540964728"
  },
  {
    "hash": "5e456e75655024ef0d0877da4381a2adab97dcfe4d3346c7988586ef57593740",
    "previousHash": "7031af024a31b79d8c9706c6b025a6e76c2504d417697f85380eae05410fde25",
    "data": "Fifth block",
    "TimeStamp": "1604540964729"
  },
  {
    "hash": "313eb86fa0bc1ecf67b9929bb7f1aaf3a65ec6f557a90a1b2f1674487b6b8b6d",
    "previousHash": "7031af024a31b79d8c9706c6b025a6e76c2504d417697f85380eae05410fde25",
    "data": "Sixth block",
    "TimeStamp": "1604540964729"
  }
]
