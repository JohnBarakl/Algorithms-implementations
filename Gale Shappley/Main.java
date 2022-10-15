import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		List<List<Integer>> listH = new ArrayList<List<Integer>>();
		int Val = 0, Wayne = 1, Xavier = 2, Yolanda = 3, Zeus = 4;
		String[] doctors = {"Val", "Wayne", "Xavier", "Yolanda", "Zeus"};
		
		listH.add( Arrays.asList(new Integer[]{Wayne, Val, Yolanda, Zeus, Xavier}) );
		listH.add( Arrays.asList(new Integer[]{Yolanda, Wayne, Val, Xavier, Zeus}) );
		listH.add( Arrays.asList(new Integer[]{Wayne, Zeus, Xavier, Yolanda, Val}) );
		listH.add( Arrays.asList(new Integer[]{Val, Yolanda, Xavier, Wayne, Zeus}) );
		listH.add( Arrays.asList(new Integer[]{Wayne, Yolanda, Val, Zeus, Xavier}) );
		
		List<List<Integer>> listD = new ArrayList<List<Integer>>();
		int Atlanta = 0, Boston = 1, Chicago = 2, Detroit = 3, El_Paso = 4;
		String[] cities = {"Atlanta", "Boston", "Chicago", "Detroit", "El Paso"};
		
		listD.add( Arrays.asList(new Integer[]{El_Paso, Atlanta, Boston, Detroit, Chicago}) );
		listD.add( Arrays.asList(new Integer[]{Chicago, Boston, Detroit, Atlanta, El_Paso}) );
		listD.add( Arrays.asList(new Integer[]{Boston, Chicago, Detroit, El_Paso, Atlanta}) );
		listD.add( Arrays.asList(new Integer[]{Atlanta, El_Paso, Detroit, Chicago, Boston}) );
		listD.add( Arrays.asList(new Integer[]{Detroit, Boston, El_Paso, Chicago, Atlanta}) );
		
		int[][] result = GaleShappley.galeShappley(listH, listD);
		
		for (int[] i : result) {
			System.out.printf("%s - %s%n", cities[i[0]], doctors[i[1]]);
		}
	}
}
