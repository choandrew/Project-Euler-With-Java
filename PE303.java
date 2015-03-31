/*
this question  has images in it, so go to following link to see:
https://projecteuler.net/problem=303
*/


import java.util.Arrays;
					

public class PE303 {
	/* the general strategy for this question will be to:
	 * for number n:
	 * make array of digits of n*m, where m starts at 1
	 * 
	 * create array of digits of n*m
	 * check array to see if all digits are <=2
	 * if not: m++
	 *
	 * once an m is found, then add to sum
	 * then add all the found m's
	 * 
	 * this problem is a harder form of PE52
	 *
	 *
	*/   
	public static void main(String[] args) {
		long totalsum = 0L;
		for (long n = 1; n <= 10000; n++) {
			/*
			 * While running the program, I noticed that numbers that were multiples of 9
			 * except with 9s between the digits took a disproportionate amount of calculation time
			 *
			 * for example:
			 *
			 * 9, 18, 27, 36, 45, 54, 63, 72, 81, 90, 99
			 * all took huge numbers for m
			 * 
			 * now add a 9 in between the numbers:
			 * 99, 198, 297, 396, 495, etc.
			 * these numbers took even bigger m values to get
			 * 
			 * n = 9999 would take days to calculate the m value for
			 * however, it is easy to get by hand
			 * for n = 9, m = 1358
			 * for n = 99, m = 11335578
			 * 
			 * so I extrapolated, and got that
			 * for n = 999,	 m = 111333555778
			 * and
			 * for n = 9999, m = 1111333355557778
			 *
			 *
			 * Similarly, n=1998 took forever to calculate
			 * when 	n = 18,		m = 679
			 * when 	n = 198		m = 5667789
			 * thus, when 	n = 1998	m = 55666777889
			 *
			 *
			 * the hardest numbers to get m values for are:
			 * 1998, 2997, 3996, 4995, 5994, 6993, 7992, 8991, 9990, 9999 
			 *
			 *
			 * to save computation, these will be done separately 
			 * 999 , 11133355578
			 * 1998, 55666777889
			 * 9999, 1111333355557778
			 *
			 */


			long m = 1L;

			if (n == 999L) {
				m = 111333555778L;
			}

			if (n == 1998L) {
				m = 55666777889L;
			}

			if (n==9999L) {
				m=1111333355557778L;
			}


			while (n!= 9999 && PE303.checkarray(PE303.makearrayofdigits(n*m))== false) {
				m++;		
		
			}
			totalsum += m;
		}
		System.out.println(totalsum);	
	}		


	/*
	*makearrayofdigits takes the input number and makes an array with the digits of the number
	 */

	public static byte[] makearrayofdigits(long number) {
		byte[] array;
		long dummy = number;
		array = new byte[(int) (Math.log10(number)+1)];
		for(int i=0; i< (int) Math.log10(number) +1; i++) {
			
				
	
			array[i]= (byte) ((dummy % 10));	
			
			
			dummy /= 10;
			}
		return array;
	}

	/* checkarray checks if every number in an array is less than or equal to 2
	 */
	public static boolean checkarray(byte[] array1) {
		for (byte digitofnumber : array1) {
			if (digitofnumber > 2) {
				return false;
			}
		}
		return true; 
		
	}	
}
