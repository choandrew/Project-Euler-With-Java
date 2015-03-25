/*

It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.

Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
*/

import java.util.Arrays;
					

public class PE52 {
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



				if (PE52.checkarray(a,b) == true && PE52.checkarray(a,c) == true && PE52.checkarray(a,d) == true && PE52.checkarray(a,e) == true && PE52.checkarray(a,f) == true) {
					System.out.println("the answer is " + i);
					foundnumber = true;
				}
			}	
			digits++;
		}		
	}	



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

	public static boolean checkarray(byte[] array1, byte[] array2) {
		for (byte digitofnumber : array1) {
			if (Arrays.equals(array1, array2) == false) {
				return false;
			}
		}
		return true; 
		
	}	
}
