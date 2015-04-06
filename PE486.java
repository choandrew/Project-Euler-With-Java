/*
 * Let F5(n) be the number of strings s such that:
 *
 * - consists only of '0's and '1's,
 * - has length at most n, and
 * - contains a palindromic substring of length at least 5. 
 *
 * For example, F5(4) = 0, F5(5) = 8, F5(6) = 42 and F5(11) = 3844.
 *
 * Let D(L) be the number of integers n such that 5 <= n <= L and F5(n) is divisible by 87654321.
 * 
 * For example, D(10^7) = 0 and D(5*10^9) = 51.
 *
 *Find D(10^18).
 *
 */
import java.lang.Math;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;


/* through some ungodly methods of math and analysis I got that:
 * F(n) - 3*F(n-1) + 2*F(n-2) =	{	38 if n = 0 (mod 6)
 * 				{	36 if n = 1 (mod 6)
 *				{	32 if n = 2 (mod 6)
 *				{	32 if n = 3 (mod 6)
 *				{	30 if n = 4 (mod 6)
 *				{	32 if n = 5 (mod 6)
 * AND n > 11
 * that's why the problem gave F(11) and not some other number
 * 
 * answer is:11408450515
 *
 */



public class PE486 {
	
	public static void main(String[] args) {
		System.out.println(PE486.D(1000000000000000000L));
	//	PE486.bruteforcer(20);	
	}

// D is just a modified version of F that counts when F is divisible by 87654321
	public static long D(long n) {
		long count = 0;
	
		long m = 9;
		// F(7) = 138, F(8) = 362
		long recPrev=138;
		long recCurrent=362;

		while (n >= m) {	
			if (m %6 == 0) {
				long current = recCurrent;
				recCurrent = 3L*recCurrent-2*recPrev + 38L;
				recPrev = current;
			} else if (m%6== 1) {
				long current = recCurrent;
				recCurrent = 3L*recCurrent-2*recPrev + 36L;
				recPrev = current;
			} else if (m%6==2 || m%6==3 || m%6==5) {
				long current = recCurrent;
				recCurrent = 3L*recCurrent-2*recPrev + 32L;
				recPrev = current;
			} else /*(m%6==4)*/{
				long current = recCurrent;
				recCurrent = 3L*recCurrent-2*recPrev + 30L;
				recPrev = current;
			}
			recPrev = recPrev % 87654321;
			recCurrent = recCurrent % 87654321;	
			if (recCurrent == 0) {
				count++;
			}
			m +=1;
		}
		
		
		
		
		return count;

	}

	public static long F(long n) {
		
		//I figured the base cases out through PE486.bruteforcer(n);
		if (n <= 4) {
			return 0;
		}
		if (n==5) {
			return 8;
		} if (n==6) {
			return 45;
		} if (n==7) {
			return 138;
		} if (n==8) {
			return 362;
		}
		
		long m = 9;
		// F(7) = 138, F(8) = 362
		long recPrev=138;
		long recCurrent=362;

		while (n >= m) {	
			if (m %6 == 0) {
				long current = recCurrent;
				recCurrent = 3*recCurrent-2*recPrev + 38;
				recPrev = current;
			} if (m%6== 1) {
				long current = recCurrent;
				recCurrent = 3*recCurrent-2*recPrev + 36;
				recPrev = current;
			} if (m%6==2 || m%6==3 || m%6==5) {
				long current = recCurrent;
				recCurrent = 3*recCurrent-2*recPrev + 32;
				recPrev = current;
			} if (m%6==4){
				long current = recCurrent;
				recCurrent = 3*recCurrent-2*recPrev + 30;
				recPrev = current;
			}
			m +=1;
		}
		return recCurrent;
	}




/*
 * everything below this line is what I used to figure out the recurrence relationship
 * this solves up to F(25) pretty quickly
*/
	public static void bruteforcer(int input) {
		for (int length = 1; length <= input; length++) {	
			System.out.println("F(" + length + ") = " + PE486.F_brute(length));
		}


	}
	
	public static int F_brute(int length) {	
		
		int count = 0;

		while (length >= 5) {
			count +=  length>5 ? stringcreator(length) : 8;
			length--;
		}
		return count;

	}

	public static boolean palindromes_checker(String string) {	
		boolean isThisAPalindrome = false;
		int length = string.length();
	 	for (int i = 0; i <= (length-5); i++) {
			//this if checks for palindromes of length 5
			if (string.charAt(i) == string.charAt(i+4)) {
				if (string.charAt(i+1) == string.charAt(i+3)) {
					isThisAPalindrome = true;
					break;
				}	
			}
			//this if checks for palindromes of length 6
			if  (i != (length-5) && string.charAt(i) == string.charAt(i+5)) {
			//the i != (length-5) is so that it doensn't output a "index out of range" error
				if (string.charAt(i+1) == string.charAt(i+4)) {
					if (string.charAt(i+2) == string.charAt(i+3)) {
						isThisAPalindrome = true;
						break;
					}
				}
	
			}

		}

		return isThisAPalindrome;
	}

	public static int stringcreator(int length) {
		int numberOfPalindromes = 0; 	
		BigInteger count = new BigInteger("0");

		BigInteger maxnum = new BigInteger("2").pow(length);

		while (count.compareTo(maxnum) == -1) {
			String string = count.toString(2);
			while (string.length() < length) {
				string = "0" + string;
			}
			
			if (PE486.palindromes_checker(string)) {
				numberOfPalindromes++;
			}

			count = count.add(new BigInteger("1"));			
		}	
		return numberOfPalindromes;
	
	}
}
