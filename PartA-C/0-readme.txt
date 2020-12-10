This is my attemp for part A - C. My MiniBlockchain program will create a simple blockchain with 4 blocks in it. 
Then the program will convert the blockchain to JSOn and write it into the disk.

example output:


The block chain: 
[
  {
    "BlockNumber": 1,
    "hash": "111294d618db16e8cfb18ca42db1f057d5a87977fe6be15bc938a6c9b6e67241",
    "previousHash": "0",
    "data": "First block with random data",
    "timeStamp": 1604540082384,
    "nonce": 0,
    "uuuid": "f928a4e1-bf73-41b0-b5f0-56a67209a0b9"
  },
  {
    "BlockNumber": 2,
    "hash": "db0d77b1ab654431f0bb077cd36b40c02540ce86f6ea4c5f724ea5b97379179d",
    "previousHash": "111294d618db16e8cfb18ca42db1f057d5a87977fe6be15bc938a6c9b6e67241",
    "data": "This is the second block",
    "timeStamp": 1604540082386,
    "nonce": 0,
    "uuuid": "f928a4e1-bf73-41b0-b5f0-56a67209a0b9"
  },
  {
    "BlockNumber": 3,
    "hash": "afe6f97281afb922f2632acd62c11c457cbc99d3cedd6e8312db6b164fbfce5e",
    "previousHash": "db0d77b1ab654431f0bb077cd36b40c02540ce86f6ea4c5f724ea5b97379179d",
    "data": "The third block with random data",
    "timeStamp": 1604540082386,
    "nonce": 0,
    "uuuid": "f928a4e1-bf73-41b0-b5f0-56a67209a0b9"
  },
  {
    "BlockNumber": 4,
    "hash": "525b46bb3d53b8bbc4d5e33bd0682f649648f33a2ee1b5899e0467d46d41c05b",
    "previousHash": "afe6f97281afb922f2632acd62c11c457cbc99d3cedd6e8312db6b164fbfce5e",
    "data": "The fourth block with random data",
    "timeStamp": 1604540082386,
    "nonce": 0,
    "uuuid": "f928a4e1-bf73-41b0-b5f0-56a67209a0b9"
  }
]
Writing File: 
Done!

=========> In ReadJSON <=========