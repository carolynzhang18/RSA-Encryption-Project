package com.CarolynZhang.MyProjects;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class RSA_CarolynZhang {
    static Scanner s = new Scanner (System.in);
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String WHITEBG_BLACKTEXT = "\u001b[47;1m" + "\u001b[30;1m";
    public static void main (String[] args) {
        introduction();
        algorithmOverview();
        example();
        System.out.println("I'd like to give you the chance to try inputting your own messages too! The ASCII conversion will be used―you may use any letters, numbers, and symbols you wish. \n" +
                "Unfortunately, this program has its limits in terms of computation! We will encrypt and decrypt letters individually, even though this lessens the security, since it keeps \n" +
                "the numbers smaller. Also, we will stick to the public and private key previously defined: n = 187, e = 3, d = 107. \n" +
                "However, imagine the use of this algorithm with primes that are much, much bigger than 11 and 17... The RSA encryption algorithm would ensure a very high degree of security.");
        pressEnter("TRY OUT THE RSA SIMULATOR");
        System.out.println("At the prompt, enter the word or sentence you'd like encrypted and decrypted. The program will perform and display the calculations needed to perform the RSA algorithm.");
        boolean again = true;
        while (again) {
            System.out.print("Enter a word/sentence: ");
            String input = s.nextLine();
            doRSA(input);
            System.out.print("\nWould you like to enter a new word/sentence? Enter Y/N: ");
            boolean isValid = false;
            while (!isValid) {
                String againIn = s.nextLine();
                if (againIn.equals("N")) {
                    again = false;
                    break;
                }
                else if (againIn.equals("Y")) {
                    break;
                }
                else {
                    System.out.print("Invalid entry. Enter Y/N: ");
                }
            }
        }
        System.out.print("Thanks for trying out the program! :)");
    }
    public static void pressEnter (String message) {
        System.out.println();
        System.out.println(WHITEBG_BLACKTEXT + "[PRESS ENTER " + message + "]" + RESET);
        s.nextLine();
    }
    public static void introduction() {
        System.out.println("This Java program explains and simulates the RSA algorithm.\nBy: Carolyn Zhang");
        pressEnter("FOR INTRODUCTION");
        System.out.println("RSA is a form of public-key cryptography originating from the 1970s. Public-key, or asymmetric cryptography makes use of two distinct encryption keys: one public and one private. \n" +
                "In order to perform the encryption, a one-way function is needed. A one-way function is easy to compute in one direction, but near impossible in the opposite direction. \n" +
                "The RSA algorithm is a particularly robust form of cryptography owing to its use of a variety of mathematical principles, such as modular arithmetic and Euler's theorem.");
        pressEnter("TO CONTINUE");
        System.out.println("Let's take a look at a general example to explore the fundamental ideas behind the RSA algorithm: \n" +
                "Suppose Alice and Bob wish to pass a treasure chest amongst themselves. This chest is secured with a padlock that only opens with a specific key. \n" +
                "Alice and Bob each have their own padlock and the corresponding key. Suppose Alice wants to send Bob some treasure one day. Using the concept of the RSA algorithm, \n" +
                "Alice first asks Bob to give her his opened padlock. Then, Alice places the treasure inside the chest and locks it with Bob’s padlock. She sends the chest back to Bob, \n" +
                "who is able to open it, since he owns the key. Because neither Alice or Bob’s personal keys are ever exchanged throughout the process, a third party cannot open the chest, \n" +
                "even if they manage to intercept it.");
        System.out.println("In essence, the RSA algorithm is the mathematical embodiment of this process.");
        pressEnter("FOR AN OVERVIEW OF THE RSA ALGORITHM");
    }
    public static void algorithmOverview() {
        System.out.println("Here is an overview of how the RSA algorithm works:");
        System.out.println("The security and reliability of the RSA algorithm is founded on the notion that it is incredibly difficult to deduce the prime factorization of a very large number―\n"
                + "this is where the algorithm begins.");
        pressEnter("TO CONTINUE");
        System.out.println("The PUBLIC KEY is the component of the encryption that is available to the public, as its name would suggest. The receiver's public key consists of two parts: \n" +
                "1. The product, n, of two large prime numbers, p and q. n is quite straightforward to compute, but it would be quite difficult to factor out p and q from n if they are large enough.\n" +
                "2. e, a number which is relatively prime to φ(n). \n\n"
                + "*Euler's phi function, φ(k), returns the number of integers between 1 and k that are relatively prime to k.\n" +
                "Euler's totient function states that φ(k) = φ(a)φ(b) if k = ab.\n" +
                "Additionally, for a prime number r, φ(r) = r-1, since every number less than b is relatively prime to it. \n" +
                "Thus, knowing that n = pq, φ(n) = (p-1)(q-1).");
        pressEnter("TO CONTINUE");
        System.out.println("Next, the receiver generates their PRIVATE KEY, d, which is only known to themselves. Compute a d value such that de ≡ 1(mod ϕ(n))\n" +
                "Using the extended Euclidean algorithm, this can be rewritten as:\n" +
                "   de = 1 + k ϕ(n)   , for an integer k\n" +
                "    d = (1 + k ϕ(n)) / e");
        pressEnter("TO CONTINUE");
        System.out.println("Following the generation of both the public and private keys, the sender can encrypt their message by encrypting m, the message, then transforming this into\n" +
                "c, the ciphertext.\n" +
                "1. The message is converted using a conversion process to determine each m value. One common conversion process is the ASCII alphabet \n" +
                "(search \"ASCII alphabet characters\" online for the full conversion table). \n" +
                "Note that m must always be lesser than n (the first component of the public key), in order to preserve the message when (mod n) is taken at a later step.\n" +
                "2. The sender computes the ciphertext, c, according to the following: c ≡ m^e (mod n)");
        pressEnter("TO CONTINUE");
        System.out.println("Now, the message has been transformed into c and is ready to be sent to the receiver. The receiver can decrypt the sender's message using c^d ≡ m (mod n), \n" +
                "which can be rewritten as: m ≡ c^d (mod n). Now, the receiver has successfully obtained the sender's original message, m.");
        pressEnter("TO CONTINUE");
        System.out.println("The security of the RSA algorithm lies in the difficult of finding ϕ(n) without knowing the prime factors of n. In our final decryption congruency statement, m ≡ c^d (mod n), \n" +
                "the value of n is publicly known and the value of c can be captured through interception―the only missing piece is d. However, d is the receiver's private key, \n" +
                "and may only be computed through d = (1 + k ϕ(n)) / e. However, this task is impossible without knowing ϕ(n), which is equal to (p-1)(q-1). In other words, without \n" +
                "knowing the original prime factors, p and q, one cannot determine the value of ϕ(n), leading to an inability to compute a value for d and thus an inability to decode\n" +
                "the ciphertext! In its real-world applications, RSA makes use of primes with hundreds of digits, meaning prime factoring n into pq will take computers many, many years.\n" +
                "Thus, the RSA algorithm safely encrypts the sender's message, even in the case of third-party interception.");
        pressEnter("FOR A SUMMARY");
        System.out.println("So, to summarize the RSA algorithm as concisely as possible: \n" +
                RED + "1. Receiver creates public key: n = pq; e is relatively prime to φ(n)\n" + RESET +
                CYAN + "2. Receiver create private key: de ≡ 1(mod ϕ(n))\n" + RESET +
                YELLOW + "3. Sender encrypts their message: m is obtained through some conversion process (e.g. ASCII); c ≡ m^e (mod n)\n" + RESET +
                GREEN + "4. Receiver decrypts c: m ≡ c^d (mod n)" + RESET);
        pressEnter("TO GO THROUGH AN EXAMPLE");
    }
    public static void example() {
        System.out.println("To illustrate, here's an example of the RSA algorithm in action:\n");
        System.out.println("Let's start with Step 1, the creation of the public key. Just to keep things relatively small, let's choose the prime numbers p and q to be 11 and 17.\n" + RED +
                "n = pq\n" +
                "n = 187\n" + RESET +
                "Let's choose " + RED + "e = 3" + RESET + ", since it = is relatively prime to φ(n) → recall that φ(n) is theoretically unknown to the public, since it is calculated from p and q\n" +
                "φ(n) = (p-1)(q-1)\n" +
                "     = (11-1)(17-1)\n" +
                "     = 160");
        pressEnter("TO CONTINUE");
        System.out.println("Next, let's calculate the private key.\n" + CYAN +
                "d = (1 + k ϕ(n)) / e\n" +
                "  = (1 + 160k) / 3\n" + RESET +
                "For d to be an integer, k could be 2, 5, 8, etc. Any of these k values could lead to a valid d value, but we will go with k=2 to keep numbers \"small\". Thus, " + CYAN + "d = 107" + RESET);
        pressEnter("TO CONTINUE");
        System.out.println("Now it's time to encrypt a message! Let's use the letter \"M\" as our message, and the ASCII table as our conversion process. In the table, it is shown that \n" + YELLOW +
                "m = 77" + RESET);
        System.out.println("To calculate the ciphertext, c: \n" + YELLOW +
                "c ≡ m^e (mod n)\n" +
                "c ≡ 77^3 (mod 187)\n" +
                "c ≡ 456533 (mod 187)\n" +
                "c = 66\n" + RESET + "This is ready to be sent to the receiver now.");
        pressEnter("TO CONTINUE");
        System.out.println("For the big reveal - decryption! The numbers will get a little big, but it all works out: \n" + GREEN +
                "m ≡ c^d (mod n)\n" +
                "m ≡ 66^107 (mod 187)\n" +
                "m ≡ 491135246984147980188122953654135257843455357933207841274683972828686388420486076510627098223255358651539475951834404594511860884300420823900041774412928018751066493857126529769518923283018285056 (mod 187)\n" +
                "m = 77\n" + RESET +
                "And voilà! The receiver has computed the value of m to be 77―the letter \"M\", exactly as the sender intended!");
        pressEnter("TO CONTINUE");
        }
    public static void doRSA(String input) {
        ArrayList<String> message = new ArrayList<>();
        for (int i=0; i<input.length(); i++) {
           message.add(String.valueOf(input.charAt(i)));
        }
        System.out.println(RED + "PUBLIC KEY: n = 187, e = 3" + RESET);
        System.out.println(CYAN + "PRIVATE KEY: d = 107" + RESET);
        System.out.println("SENT MESSAGE: " + message);
        ArrayList<Integer> messageASCII = new ArrayList<>();
        for (int i=0; i<message.size(); i++) {
            int ascii = (int) input.charAt(i);
            messageASCII.add(ascii);
        }
        System.out.println(YELLOW + "ASCII CONVERSION: m = " + messageASCII);
        ArrayList<String> ciphertextCalc = new ArrayList<>();
        for (Integer value : messageASCII) {
            String calculationCT = value + "^3(mod 187)";
            ciphertextCalc.add(calculationCT);
        }
        ArrayList<BigInteger> ciphertext = new ArrayList<>();
        BigInteger n = new BigInteger("187");
        for (Integer integer : messageASCII) {
            BigInteger m = new BigInteger(String.valueOf(integer));
            BigInteger powerCT = m.pow(3);
            BigInteger c = powerCT.mod(n);
            ciphertext.add(c);

        }
        System.out.println("CIPHERTEXT: c ≡ " + ciphertextCalc);
        System.out.println("CIPHERTEXT: c = " + ciphertext + RESET + "\nThis is the form in which the message is distributed.");
        ArrayList<String> decryptionCalc = new ArrayList<>();
        for (BigInteger bigInteger : ciphertext) {
            String calculationDC = bigInteger + "^107(mod 187)";
            decryptionCalc.add(calculationDC);
        }
        ArrayList<Integer> decryption = new ArrayList<>();
        for (BigInteger bigInteger : ciphertext) {
            BigInteger ciph = new BigInteger(String.valueOf(bigInteger));
            BigInteger powerDC = ciph.pow(107);
            int mess = powerDC.mod(n).intValue();
            decryption.add(mess);
        }
        ArrayList<String> reconvert = new ArrayList<>();
        for (Integer value : decryption) {
            int tempInt = value;
            char character = (char) tempInt;
            String tempString = String.valueOf(character);
            reconvert.add(tempString);
        }
        System.out.println(GREEN + "DECRYPTION: m ≡ " + decryptionCalc);
        System.out.println("DECRYPTION: m = " + decryption);
        System.out.println("DECRYPTION: m = " + reconvert + RESET);
        String finalMessage = "";
        for (String s : reconvert) {
            finalMessage += s;
        }
        System.out.println("RECEIVED MESSAGE: " + finalMessage);
    }
}
