# LISP-dialect
My own dialect of LISP programming language

This is test version of LISA language. Firstly I tried to impliment LISP language dialect, however, after inspratation of CLOJURE language I began to implement new lisp-type language

So, how is it different from excisting languages.
1. It is multi-paradigm language, functional language with allowance of variable decloration.
2. It would be simple and fast, the function of this language would be advanced math/Ai/Neural network implementation. 
3. It would have both web/desktop implementation. It would allow you program in web.
4. And many other things :)

Some examples:

## Adding two numbers
C++
```C++
1 + 2
```

Lisa:
```Clojure
(+ 1 2)
```

## Adding more than two numbers
C++
```C++
1 + 2 + 3 + 4 + 5
```

Lisa:
```Clojure
(+ 1 2 3 4 5)
```
**As you can see, it is more compact in Lisa**

## Sum of sequence
C++
```C++
int sum = 0;
for (int i = 1; i <= 100; i++)
    sum += i;
```

Lisa:
```Clojure
(apply + (list 1 100))
```
## Finding prime numbers :
C++:
```C++
vector<int> primes(int until) {
    vector<int> primes  = {2};
    for (int i = 3; i <= until; i++) {
        bool isPrime = false;
        for (int j = 0; j < primes.size(); j++) {
            if (i % primes[j] == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime)
            primes.push_back(i);
     }
     return primes;

}
```

Lisa:
```Clojure
(def gen-primes
  (fn [until]
    (do
        (def primes [2])
        (def is-prime (fn [x]
                    (apply and (lmap (fn [el] ( != 0 (mod x el))) primes))))
        (do-f (fn [num]
               (if (is-prime num) (pushb! primes num))) (range 3 until))
       primes
       )
    )
 )
 ```
