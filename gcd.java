import java.lang.Math;

class gcd {
	

/* I implemented the Binary GCD algorithm. I got the C code from:
 * http://en.wikipedia.org/wiki/Binary_GCD_algorithm
 * used the iterative version, and transcribed it to java
 */
	public static long gcd(long u, long v) {
		
		int shift;
	
		if (u == 0) {
			return u;
		} if (v == 0) {
			return v;
		}
	
		// shift is the exponent of the greatest power of 2 that divides both u and v	
		for (shift = 0; ((u | v) & 1) == 0 ; ++shift) {
			u = u>>1;
			v = v>>1;
		}

	
		while ((u & 1) == 0 ) {
			u = u>>1;
		}


		// u should be odd from this point onward

		do {
			// remove all factors of 2 in v, as they are not part of the gcd
			// note: v is not zero, so while will terminate
			while ((v&1) == 0) {
				v = v>>1;
			}

			// Now u and v are both odd. Swap so that u <= v,
			// then set v = v - u (which should be even)
			if( u > v) {
				long t = v;
				v = u;
				u = t;
			}
			v = v - u;
		} while (v != 0);


		return u<<shift;		


	}
}
