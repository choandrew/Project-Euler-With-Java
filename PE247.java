/*
 * this code solves the problem from this website:
 * https://projecteuler.net/problem=247
 *
 * this problem seemed perfect to apply 
 * what I learned in Data Structures: priority queues/heaps
 *
 *
 *
 * the code is admittedly a bit hacked together, but it is organized
 *
 * this code fundamentally does the following:
 * 1. each square is identified with an array of 7 doubles with
 *    {x coordinate of bottom left vertex, y coordinate of bottom left vertex,
 *    x coordinate of top right vertex, y coordinate of top left vertex,
 *    area of square, number of squares to the left, numbers of squares below}
 * 2. list all possible moves in a priority queue named possible_next_moves with the comparator being the area
 * 3. the head of the priority queue is then fed into an arraylist moves_in_order, which would then have
 *    the areas of the moves in order
 * 4. any move with an index of (3,3) would be fed into another arraylist has_correct_index
 * 5. once has_correct_index has 20 elements, then the last element of it is printed
 *    along with the it's index in moves_in_order, which is equal to the size of moves_in_order
 *    as it is the last element
 *    has_correct_index needs to have 20 elements (6C3) because the number of squares with index (3,3)
 *    is like a "manhattan distance" to the point (3,3) from the origin
 *
 * the output answer turns out to be 782252
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PE247{




	/*
	 * arraylist moves_in_order to hold every square in order of area
	 * arraylist has_correct_index holds every square with index (3,3)
	 * you can see the comparator I used at the very last method of this class
	 * essentially, the squares with higher area have lower priority and will be
	 * taken out first in a poll of the priority queue possible_next_moves
	 */
	public static void main (String[] args) {
	

		ArrayList<double[]> moves_in_order = new ArrayList<double[]>();
		ArrayList<double[]> has_correct_index = new ArrayList<double[]>();
		
		// as PQSort (the class implementing comparator) is a nested class, the program must create an instance of the outer class
		PE247 instance = new PE247();
		Comparator<double[]> comparator = instance.new PQSort();
		
		
		
		PriorityQueue<double[]> possible_next_moves = new PriorityQueue<double[]>(10, comparator);
		


		double[] initial_move = {1,0};
		moves_in_order.add(PE247.move_maker(moves_in_order, initial_move));
		// System.out.println("1 has: " + Arrays.toString(moves_in_order.get(0)));
		

		while (has_correct_index.size() < 20) {
			determine_possible_next_moves(moves_in_order, possible_next_moves);
			
			// only checks [6] (aka the y_index), because the y-index can only be nonzero if the x-index is 3, as seen in the
			// move_maker method
			if (possible_next_moves.peek()[6] == 3.0) {
				System.out.println("Square number " + (moves_in_order.size()+1) + " has index of (3,3)");
				has_correct_index.add(possible_next_moves.peek());
			}			
			moves_in_order.add(possible_next_moves.poll());

		}
		

		System.out.println("The answer is square number: " + (moves_in_order.size()+1));
	}

	/*
	 * after every step, this method, will add to the possible number of moves
	 * for every (x, 1/x) you make and hence take away from the possible moves
	 * another 2 possible moves will be created 
	 * these moves are added to the priority queue possible_next_moves
	 */
	
	
	
	public static void determine_possible_next_moves(ArrayList<double[]> moves_in_order, PriorityQueue<double[]> possible_next_moves) {
		double[] lastmove = moves_in_order.get(moves_in_order.size()-1);		
		
		double[] above = new double[2];
		double[] below = new double[2];

		above[0] = lastmove[0];
		above[1] = lastmove[3];
		
		below[0] = lastmove[2];
		below[1] = lastmove[1]; 	
		
		possible_next_moves.add(PE247.move_maker(moves_in_order, above));
		possible_next_moves.add(PE247.move_maker(moves_in_order, below));
			

	}


	/*
	 * given the bottom left coordinates of a square, this method will determine the square that
	 * will be produced
	 * [0] = bottom left x coordinate
	 * [1] = bottom left y coordinat(calculated with quadratic formula)
	 * [2] = top right x coordinate
	 * [3] = top right y coordinate (reciprocal of [2])
	 * [4] = area of square
	 * [5] = x index
	 * [6] = y index
	 */
	public static double[] move_maker(ArrayList<double[]> moves_in_order,double[] BL_coordinate_of_move) {
		double[] possible_move = new double[7];
		double x1 = BL_coordinate_of_move[0];
		double y1 = BL_coordinate_of_move[1];
		possible_move[0] = BL_coordinate_of_move[0];
		possible_move[1] = BL_coordinate_of_move[1];
		possible_move[2] = (x1 - y1 + Math.sqrt(Math.pow((x1-y1), 2) + 4))/2;
		possible_move[3] = 1.0/(possible_move[2]);
		possible_move[4] = Math.pow(possible_move[2]-possible_move[0],2) ;
		possible_move[5] = PE247.x_index(moves_in_order, possible_move[1]);
		

		// only calculates y-index if x-index is 3, because we only care if both are 3	
		if (possible_move[5] == 3) {
			possible_move[6] = PE247.y_index(moves_in_order, possible_move[0]);
		} else {
			possible_move[6] = 0;
		}


		return possible_move;
	}

	// returns 3 if the x index is 3
	// otherwise, returns 0
	 
	public static double x_index(ArrayList<double[]> moves_in_order, double BL_y_coordinate) {
		/*
		 * x_index should really be an int, but the array it's in is filled with doubles
		 * so I made it a double
		 */


		double x_index = 0;
		for(double[] moves : moves_in_order) {
			if (x_index >= 4.0) {
				return 0;
			}
			if (moves[1] <= BL_y_coordinate && BL_y_coordinate < moves[3]) {
				x_index++;
			}	
		}
		if (x_index == 3.0) {
			return x_index;
		} else {
			return 0;
		}
	}

	// returns 3 if the y index is 3
	// otherwise, returns 0
	public static double y_index(ArrayList<double[]> moves_in_order, double BL_x_coordinate) {
		/*
		 * y_index should really be an int, but the array it's in is filled with doubles
		 * so I made it a double
		 */
		double y_index = 0;
		for(double[] moves : moves_in_order) {
			if (y_index >= 4.0) {
				return 0;
			}
			if (moves[0] <= BL_x_coordinate && BL_x_coordinate < moves[2]) {
				y_index++;
			}
		}
		if (y_index == 3.0) {
			return y_index;
		} else {
			return y_index;
		}
	}



       public class PQSort implements Comparator<double[]> {
		@Override
		public int compare(double[] x, double[] y) {
		
			if (x[4] > y[4]) {
				return -1;
			}

			if (x[4] < y[4]) {
				return 1;
			}

		return 0;
		
		}
       }

}
