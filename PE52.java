/*

It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.

Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
*/

import java.util.Arrays;
					

public class PE52 {


/*
* the reason I will be checking up to 10 ** (digits + 1)/6, which
* is equal to 1 followed by 6s (aka 16, 166, 1666, 16666) is
* because 6x needs to have the same number of digits as x
* so for example x = 167 would not work because 167 * 6 = 1001
* has one more digits
* 
* the strategy for this problem is making a sorted array of the
* every number and its multiples and checking to see if they are
* equal
*
* if all 6 arrays are equal, then we have the number
*/
	public static void main(String[] args) {
		boolean foundnumber = false;
		byte digits = 1;
		while(foundnumber == false) {
			for (int i =  (int) Math.pow(10,digits); i < (int) (Math.pow(10, digits+1))/6; i++) {

				byte[] a = PE52.makearray(i);
			       	byte[] b = PE52.makearray((2*i));
				byte[] c = PE52.makearray((3*i));
				byte[] d = PE52.makearray((4*i));
				byte[] e = PE52.makearray((5*i));
				byte[] f = PE52.makearray((6*i));
			
				Arrays.sort(a);
				Arrays.sort(b);
				Arrays.sort(c);
				Arrays.sort(d);
				Arrays.sort(e);
				Arrays.sort(f);



				if (Arrays.equals(a,b) == true && Arrays.equals(a,c) == true && Arrays.equals(a,d) == true && Arrays.equals(a,e) == true && Arrays.equals(a,f) == true) {
					System.out.println("the answer is " + i);
					foundnumber = true;
				}
			}	
			digits++;
		}		
	}	


/**
*makearray is  the process that puts the digits of an input
*number into an array
*/
	public static byte[] makearray(int number) {
		byte[] array;
		int dummy = number;
		array = new byte[(int) (Math.log10(number)+1)];
		for(int i=0; i< (int) Math.log10(number) +1; i++) {
			
				
			

			array[i]= (byte) ((dummy % 10));
			
				
			
			dummy /= 10;
			}
		return array;
	}

	
}
