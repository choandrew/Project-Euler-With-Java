public class PE395 {

	public static void main(String[] arg) {
		double highestx;
		double lowestx;
		double highesty;
		double lowesty;
	}


	// gets negative value of quadratic formula
	public static double quadraticFormulaNeg(double a, double b, double c) {
		return (-b - Math.sqrt(b*b - 4*a*c))/(2*a);
	}

	//gets positive value of quadratic formula
	public static double quadraticFormulaPos(double a, double b, double c) {
		return (-b + Math.sqrt(b*b - 4*a*c))/(2*a);
	}


	public static double[] gettopleft(double[] bottomleft, double[] bottomright) {
	}

	public static double[] gettopright(double[] bottomleft, double[] bottomright) {
	}


	public static double[] outputsPointOppositeHypotenuse(double[] bottomleft, double[] bottomright, double[] topleft, double[] topright) {
		
		double m = - (topright[0] - topleft[0])/(topright[1] - topleft[1]);
		double perpx = 16*topleft[0]/25 + 9*topright[0]/25;
	       	double perpy = 16*topleft[1]/25 + 9*topright[1]/25;
		double b = m*perpx + perpy;
		double d =  Math.sqrt(Math.pow(topright[0]-topleft[0],2) + Math.pow(topright[1]-topleft[1],2));

		double x1 = PE395.quadraticFormulaPos(m*m+1, 2*(b*m - perpy*m - perpx), 144*d*d/625 - (b-perpy)*(b-perpy) - perpx*perpx);
		double x2 = PE395.quadraticFormulaNeg(m*m+1, 2*(b*m - perpy*m - perpx), 144*d*d/625 - (b-perpy)*(b-perpy) - perpx*perpx);
	
		double[] pointCandidate1 = {x1, m*x1+b};
		double[] pointCandidate2 = {x2, m*x2+b};
		if (PE395.isThisPointWithinThisSquare( bottomleft, bottomright, topleft, topright, pointCandidate1) != true) {
			return pointCandidate1;
		}

		if (PE395.isThisPointWithinThisSquare( bottomleft, bottomright, topleft, topright, pointCandidate2) != true) {
			return pointCandidate2;
		}
		
		System.err.println("error in area of checking if point is within square in outputsPointOppositeHypotenuse");
		System.exit(1);
		return new double[] {0,0};
		
	}


	public static boolean isThisPointWithinThisSquare(double[] bottomleft, double[] bottomright, double[] topleft, double[] topright, double[] point) {
		double areaofsquare1 = Math.pow(bottomleft[0] - bottomright[0],2) + Math.pow(bottomleft[1] - bottomright[1],2);
		double areaofsquare2 = Math.pow(topleft[0] - topright[0],2) + Math.pow(topleft[1] - topright[1],2);

		if (areaofsquare1 != areaofsquare2) {
			System.err.println("error in area of square calculations");
			System.exit(1);
		}
		
		return true;

	}
}
