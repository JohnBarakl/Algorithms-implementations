import java.util.*;

public class GaleShappley {
	
	/**
	 * Implements Gale - Shapely stable matching algorithm.
	 * 
	 * 
	 * The input is two lists each of which contains other lists. The "outer" list contains, corresponding to one party,
	 * the lists ranking members of the other party.
	 * 
	 * Both lists must have the same size n (n>0). The same must be true for the contained ("inner") lists.
	 * Also, the rankings of one party must correspond to a member (number [0, n-1]) of the other party and be unique for each member ranked.
	 * 
	 * For example, if the hospitalRanks argument consists of n lists: hospitalRanks[i] is the i-th hospital's list of rankings of the n doctors.
	 * Every list of rankings contains n elements where the element's value corresponds to a doctor and the element's position to that doctor's ranking for the hospital.
	 * 
	 * Similar conclusions can be made for the list of doctorRanks corresponding to ranking of hospitals from doctors.
	 * 
	 * @param hospitalRanks a list of each hospital's (0 to n-1) list of ranking of each doctor.
	 * @param doctorRanks a list of each doctor's (0 to n-1) list of ranking of each hospital.
	 * @return an array of matches (hospital-doctor pair) where the first dimension ([0][] to [n-1][]) corresponds to each matching and the second 
	 * @throws IllegalArgumentException in the case requirements above are not met.
	 */
	public static int[][] galeShappley(List<List<Integer>> hospitalRanks, List<List<Integer>> doctorRanks) throws IllegalArgumentException{
		if (hospitalRanks.size() != doctorRanks.size()) throw new IllegalArgumentException("Number of parties do not match (are not equal).");
		
		int n = doctorRanks.size();
		
		int[] hospitalMatches = new int[n]; for (int i=0; i<n; i++) hospitalMatches[i] = -1;
		int[] doctorMatches = new int[n]; for (int i=0; i<n; i++) doctorMatches[i] = -1;
		
		
		Stack<Integer> unmatchedHospitals = new Stack<Integer>();
		for (int i=hospitalRanks.size() - 1; i>=0; i--) unmatchedHospitals.push(i);
		
		int[][] doctorInvertedList = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j=0; j<n; j++) {
				doctorInvertedList[i][doctorRanks.get(i).get(j)] = j;
			}
		}
		
		int[] hospitalProgress = new int[n];
		
		while (!unmatchedHospitals.isEmpty()) {
			int currentHospital = unmatchedHospitals.pop();
			
			int doctorInspected;
			
			matching: while(true) {
				doctorInspected = hospitalRanks.get(currentHospital).get(hospitalProgress[currentHospital]++);
				
				if (doctorMatches[doctorInspected] == -1) {
					hospitalMatches[currentHospital] = doctorInspected;
					doctorMatches[doctorInspected] = currentHospital;
					break matching;
					
				} else if (doctorInvertedList[doctorInspected][doctorMatches[doctorInspected]] > doctorInvertedList[doctorInspected][currentHospital]) {
					hospitalMatches[doctorMatches[doctorInspected]] = -1;
					unmatchedHospitals.push(doctorMatches[doctorInspected]);
					
					doctorMatches[doctorInspected] = currentHospital;
					hospitalMatches[currentHospital] = doctorInspected;
					
					break matching;
				}
			}
			
		}
		
		
		int[][] tempReturn = new int[n][2];
		for (int i = 0; i < n; i++) {
			tempReturn[i][0] = i;
			tempReturn[i][1] = hospitalMatches[i];
		}
		
		return tempReturn;
	}
	
}
