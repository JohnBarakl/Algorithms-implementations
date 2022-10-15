/*
 * Ονοματεπώνυμο: Μπαρακλιλής Ιωάννης
 * ΑΕΜ: 3685.
 * Email: imparakl@csd.auth.gr
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Αντικείμενα αυτής της κλάσης αποτελούν ζεύγη ακεραίου (με σκοπό χρήσης του για αποθήκευση αριθμού
 * αντιστροφών) και λίστας.
 */
class ReversesAndListPair{
	private final int reverses;
	private final List<Integer> list;

	/**
	 * Θέτει τις τιμές του ζεύγους
	 * @param reverses Αριθμός αντιστροφών
	 * @param list Λίστα
	 */
	public ReversesAndListPair(int reverses, List<Integer> list) {
		this.reverses = reverses;
		this.list = list;
	}

	/**
	 * Επιστρέφει τον αριθμό αντιστροφών που έχει τεθεί κατά την κατασκευή.
	 * @return αριθμό αντιστροφών.
	 */
	public int getReverses() {
		return reverses;
	}

	/*
	 * Επιστρέφει την λίστα που έχει τεθεί κατά την κατασκευή.
	 * @return αποθηκευμένη λίστα.
	 */
	public List<Integer> getList(){
		return list;
	}

	@Override
	public String toString() {
		return "ReversesAndListPair{" +
				"reverses=" + reverses +
				", list=" + list +
				'}';
	}
}

/**
 * Η κλάση MergeSort περιέχει μεθόδους για την επίλυση του προβλήματος τη εκφώνησης της εργασίας.
 * Με την εκτέλεση της main, διαβάζονται απο το αρχείο το όνομα του οποίου δίνεται ως όρισμα κονσόλας και
 * εμφανίζονται στην οθόνη τα αποτελέσματα.
 *
 * @author Ioannis Baraklilis
 */
public class MergeSort {

	static int tabs = 0;

	}

	/**
	 * Υλοποίηση του τροποιημένου αλγορίθμου μέτρησης αντιστροφών (βασισμένο στην ταξινόμηση με συγχώνευση) με την μέθοδο "Διαίρει και Βασίλευε".
	 *
	 * Συγκεκριμένα, χωρίζει την δοθείσα (απο όρισμα) λίστα σε δύο ίσα (αν η αρχική λίστα έχει περιττό πλήθος στοιχείων η μία υπο-λίστα έχει ένα στοιχείο επιπλέον) μέρη,
	 * τα ταξινομεί αναδρομικά και ενώνει σε μία, νέα, ταξινομημένη λίστα.
	 *
	 * Επιστρέφει ένα αντικείμενο που αποτελεί ζεύγος του συνολικού κόστους όλων των αντιστροφών που βρέθηκαν και της ταξινομημένης λίστας που σχηματίστηκε.
	 * Όσον αφορά το κόστος, κάθε αντιστροφή έχει κόστος 3, εκτός της περίπτωσης που τα στοιχεία του ζεύγους αντιστροφής διαφέρουν κατά 1 όπου το κόστος είναι 2.
	 *
	 * Αν η λίστα που δίνεται ως όρισμα περιέχει ένα ή 0 στοιχεία, η περίπτωση αυτή θεωρείται τετριμμένη και επιστρέφεται αυτούσια η δοθείσα
	 * λίστα με πλήθος αντιστροφών = 0.
	 *
	 * @param list η λίστα για την οποία πρέπει να βρεθεί το συνολικό κόστος αντιστροφών.
	 * @return Ένα ζεύγος συνολικού κόστους και ταξινομημένης λίστας του ορίσματος.
	 */
	public static ReversesAndListPair sortAndCount(List<Integer> list) {

		if (list.size() <= 1) { // Πλήθος στοιχείων 0 ή 1, τετριμμένη περίπτωση.
			return new ReversesAndListPair(0, list); // Πολυπλοκότητα: O(1)
		}

		// Χωρισμός "αριστερού" τμήματος (πρώτου μισού) της λίστας, εύρεση αντιστροφών σε αυτό και ταξινόμηση του.
		ReversesAndListPair left = sortAndCount(list.subList(0, list.size()/2)); // Πολυπλοκότητα: T(n/2)
		ldebug("Left: " + left.toString());

		// Χωρισμός "δεξιού" τμήματος (δεύτερου μισού) της λίστας, εύρεση αντιστροφών σε αυτό και ταξινόμηση του.
		ReversesAndListPair right = sortAndCount(list.subList(list.size()/2, list.size())); // Πολυπλοκότητα: T(n/2)
		rdebug("Right: " + right.toString());

		// Ένωση (συγχώνευση) των δύο τμημάτων της λίστας σε μία νέα ταξινομημένη και εύρεση αντιστροφών της.
		ReversesAndListPair merged = mergeAndCount(left.getList(), right.getList()); // Πολυπλοκότητα: O(n)
		mdebug("Merged :" + merged.toString());

		// Το σύνολο των αντιστροφών στην λίστα του ορίσματος είναι το άθροισμα των αντιστροφών που βρέθηκαν στα επιμέρους
		// βήματα. Η ταξινομημένη λίστα απο την λίστα του ορίσματος είναι η λίστα που προκύπτει απο την συγχώνευση των ταξινομημένων τμημάτων
		// της λίστας.
		return new ReversesAndListPair(left.getReverses() + right.getReverses() + merged.getReverses(), merged.getList());
	} // Συνολική πολυπλοκότητα: T(n) = 2T(n/2) + O(n) + O(1) = 2T(n/2) + O(n) => T(n) = O(nlog(n))

	/**
	 * Υλοποιεί την λειτουργία της συγχώνευσης δύο ταξινομημένων λιστών σε μία νέα ταξινομημένη λίστα.
	 * Παράλληλα, μετράει και το κόστος των αντιστροφών των στοιχείων των δύο λιστών.
	 *
	 * Όσον αφορά το κόστος, κάθε αντιστροφή (ένα ζεύγος στοιχείων (left[i], right[j]) για κάποια i,j με left[i] > right[j])
	 * έχει κόστος 3, εκτός της περίπτωσης που τα στοιχεία του ζεύγους αντιστροφής διαφέρουν κατά 1 (συγκεκριμένα ισχύει
	 * για κάποια i,j με left[i] > right[j] και left[i] = right[j] + 1) όπου το κόστος είναι 2.
	 *
	 * @param left η πρώτη υπολίστα.
	 * @param right η δεύτερη υπολίστα.
	 * @return Ένα ζεύγος συνολικού κόστους και ταξινομημένης λίστας του ορίσματος.
	 */
	private static ReversesAndListPair mergeAndCount(List<Integer> left, List<Integer> right) {
		// Δεσμεύω την λίστα που θα αποθηκεύει την νέα ταξινομημένη λίστα.
		ArrayList<Integer> merged = new ArrayList<>(left.size() + right.size()); // Πολυπλοκότητα: O(1)

		// "Δείκτες" του τρέχον στοιχείου των πινάκων.
		int i = 0, j = 0; // Πολυπλοκότητα: O(1)
		// Μεγέθη πινάκων για να αποφευχθούν περιττές κλήσεις.
		int leftSize = left.size(), rightSize = right.size(); // Πολυπλοκότητα: O(1)
		int reverses = 0; // Αριθμός Αντιστροφών

		Integer previousNeighbour = -1; // Τιμή τρέχοντος προηγούμενου στοιχείου της πρώτης (left) υπολίστας για την οποία ισχύει
										// left[i] = right[j] + 1, για κάποια i,j. Τιμή -1 σημαίνει ότι δεν υπάρχει τέτοιο προηγούμενο στοιχείο που δεν έχουμε ήδη λάβει υπόψη
										// και επεξεργαστεί ήδη.
										// Πολυπλοκότητα: O(1)

		int consecutiveNeighbours = 0; // Αριθμός (διαδοχικών) στοιχείων της δεύτερης υπολίστας για την οποία ισχύει ότι left[i] = right[j] + 1.
									   // Πολυπλοκότητα: O(1)

		// Διαδικασία συγχώνευσης με ταυτόχρονη μέτρηση κόστους αντιστροφών:
		// 		Περίπτωση 1: Το τρέχον στοιχείο (left[i]) της αριστερής (left) υπολίστας είναι μικρότερο ή ίσο απο το τρέχον στοιχείο
		// (right[j]) της δεξιάς (right). Σε αυτή την περίπτωση απλά προσθέτω το τρέχον στοιχείο της αριστερής υπολίστας στην νέα υπολίστα
		// και αυξάνω τον αντίστοιχο δείκτη (i) του τρέχοντος στοιχείου. Εδώ, δεν υπάρχει καμία αντιστροφή.
		// 		Περίπτωση 2: Το τρέχον στοιχείο (left[i]) της αριστερής (left) υπολίστας είναι μεγαλύτερο απο το τρέχον στοιχείο
		// (right[j]) της δεξιάς (right). Σε αυτή την περίπτωση έχουμε πλήθος αντιστροφών όσο και το πλήθος των απομείναντων στοιχείων
		// της αριστερής υπολίστας (leftSize - i). Αρχικά, θεωρείται πως ισχύει η περίπτωση 2α και το σύνολο κόστους ενημερώνεται ανάλογα.
		// 			Περίπτωση 2α: Δεν ισχύει ότι: left[i] = right[j] + 1:
		// 		το συνολικό κόστος αυξάνεται κατά 3 για κάθε αντιστροφή δηλαδή κατά 3 * (πλήθος εναπομείναντων στοιχείων).
		// 			Περίπτωση 2β: Ισχύει ότι: left[i] = right[j] + 1:
		// 		το συνολικό κόστος αυξάνεται κατά 2 για κάθε ζεύγος (για κάθε i) αντιστροφής για το οποίο ισχύει left[i] = right[j] + 1 και κατά 3
		// 		για τα υπόλοιπα ζεύγη αντιστροφών στα οποία δεν ισχύει αυτό. Έτσι, πρέπει να υπολογιστεί ο αριθμός των ίσων στοιχείων (εφόσον επιτρέπεται,
		//		σύμφωνα με την εκφώνηση η ύπαρξη του ίδιου αριθμού πολλές φορές, της αριστερής (left) λίστας ώστε να προστεθεί το κατάλληλο κόστος, εφόσον η
		//		σύγκριση των τρεχόντων στοιχείων αριστερής και δεξιάς λίστας γίνεται μόνο μία φορά. Για να επιτευχθεί αυτό, όταν "εντοπίζεται" στοιχείο (κατά την περίπτωση 2β) τέτοιο ώστε
		//		να ισχύει left[i] = right[j] + 1, αναθεωρείται η προηγούμενη υπόθεση και υποθέτουμε ότι όλα τα στοιχεία της αριστερής λίστας είναι της τιμής left[i] και ενημερώνεται το σύνολο κόστους
		//		ώστε να έχει αποθηκευτεί 2 * (πλήθος εναπομείναντων στοιχείων) αντί της υπόθεσης. Επιπλέον, αποθηκεύεται η τιμή του left[i] (στην μεταβλητή previousNeighbour) και
		//		αυξάνεται η τιμή του του μετρητή consecutiveNeighbours.
		//		Στην συνέχεια, στην επόμενη περίπτωση 1 που θα συναντηθεί για την οποία θα ισχύει ότι το left[i] διαφέρει απο το αποθηκευμένο στοιχείο της μεταβλητής previousNeighbour
		//		(το οποίο σημαίνει ότι έχουμε "εξαντλήσει" τα ακολουθιακά ίσα στοιχεία της αριστερής λίστας και τα ακολουθιακά ίσα στοιχεία της δεξιάς λίστας επειδή εφόσον ισχύει left[i] = right[j] + 1 για τα επόμενα
		//		k > j το right[k] θα είναι είτε right[k] = right[j] και έχουμε περίπτωση ακολουθιακών ίσων στοιχείων στην δεξιά λίστα, right[k] = left[i] όπου βρισκόμαστε στην περίπτωση 1 με σταθερή την τιμή του previousNeighbour
		//		και right[k] > left[i] όπου βρισκόμαστε στην περίπτωση 1 και κάνουμε τις ακόλουθες επεξεργασίες:) προστίθεται στο σύνολο κόστους 1 * (πλήθος απομείναντων στοιχείων του αριστερού πίνακα),
		// 		το οποίο λύνει το πρόβλημα εμφάνισης του ίδιου αριθμού πολλές φορές στην αριστερή λίστα, πολλαπλασιασμένο κατά τον μετρητή consecutiveNeighbours, που λύνει το πρόβλημα της εμφάνισης
		// 		το πρόβλημα εμφάνισης του ίδιου αριθμού πολλές φορές στην δεξιά λίστα και, τέλος, αρχικοποιούμε τις τιμές previousNeighbour και consecutiveNeighbours.
		// 		Συνολικά, προστίθεται στο συνολικό κόστος previousNeighbour * (πλήθος απομείναντων στοιχείων του αριστερού πίνακα).
		// 		Κατ' αυτόν τον τρόπο, διορθώνονται οι υποθέσεις για ιδιότητες των στοιχείων των λιστών, που γίνονται σε κάποια βήματα του αλγορίθμου, και καταλήγουμε να έχουμε προσθέσει στο συνολικό
		// 		κόστος τα έγκυρα επιμέρους κόστη.
		// 	Τέλος, μετά το πέρας της επανάληψης, ή μία απο τις δύο υπολίστες θα είναι κενή και επομένως "βρισκόμαστε" για κάθε ένα απο αυτά τα στοιχεία είτε στην κατάσταση 1 είτε στην 2α.
		while (i < leftSize && j < rightSize) {

			// Περίπτωση 1:
			if (left.get(i) > right.get(j)) {  // Πολυπλοκότητα: O(1)
				merged.add(right.get(j));  // Πολυπλοκότητα: O(1)

				reverses += (leftSize - i) * 3; // Υποθέτω, αρχικά, ότι δεν είναι γειτονικά.
												// Πολυπλοκότητα: O(1)

				// Συνθήκη γειτονικών αντιστροφών.
				if (left.get(i) == right.get(j) + 1) {  // Πολυπλοκότητα: O(1)
					if (!previousNeighbour.equals(left.get(i)) && previousNeighbour!=-1) { // Πολυπλοκότητα: O(1)
						// "Διορθώνω" την υπόθεση ότι όλα τα απομείναντα στοιχεία του πρώτου πίνακα είναι ίσα με το τρέχον του δεξιού + 1.
						// Για να το πετύχω, προσθέτω 1 σε όλα τα απομείναντα  στοιχεία διαφορετικά απο το αποθηκευμένο "previousNeighbour".
						reverses += (leftSize - i) * consecutiveNeighbours; // Πολυπλοκότητα: O(1)
						consecutiveNeighbours = 0; // Πολυπλοκότητα: O(1)
						previousNeighbour = -1; // Πολυπλοκότητα: O(1)
					}

					// Διορθώνω την υπόθεση μη γειτονικότητας αφαιρώντας 1.
					// Παράλληλα, θεωρώ ότι όλα τα απομείναντα στοιχεία του πρώτου πίνακα είναι ίσα με το τρέχον του δεξιού + 1.
					// Έτσι, αφαιρώ 1 για όλα τα υπόλοιπα στοιχεία. Αν δεν ισχύει θα διορθωθούν στον άλλο κλάδο του if.
					reverses -= leftSize - i;  // Πολυπλοκότητα: O(1)

					previousNeighbour = left.get(i);  // Πολυπλοκότητα: O(1)
					consecutiveNeighbours++; // Πολυπλοκότητα: O(1)
				}

				j++; // Πολυπλοκότητα: O(1)
			// Περίπτωση 2:
			} else { // Πολυπλοκότητα παραπάνω block: O(8) = O(1)
				merged.add(left.get(i)); // Πολυπλοκότητα: O(1)

				// Περίπτωση 2β:
				if (!previousNeighbour.equals(left.get(i)) && previousNeighbour!=-1) { // Πολυπλοκότητα: O(1)
					// "Διορθώνω" την υπόθεση ότι όλα τα απομείναντα στοιχεία του πρώτου πίνακα είναι ίσα με το τρέχον του δεξιού + 1.
					// Για να το πετύχω, προσθέτω 1 σε όλα τα απομείναντα  στοιχεία διαφορετικά απο το αποθηκευμένο "previousNeighbour".
					reverses += (leftSize - i) * consecutiveNeighbours; // Πολυπλοκότητα: O(1)
					consecutiveNeighbours = 0; // Πολυπλοκότητα: O(1)
					previousNeighbour = -1; // Πολυπλοκότητα: O(1)
				}

				i++; // Πολυπλοκότητα: O(1)
			} // Πολυπλοκότητα παραπάνω block: O(6) = O(1)

		} // Πολυπλοκότητα παραπάνω βρόχου: Θα γίνει επανάληψη <= k + l (έστω k πλήθος στοιχείων λίστας left και έστω l πλήθος στοιχείων λίστας right) φορές,
		  // άρα αν θεωρήσουμε n = k + l, πολυπλοκότητα: T(n)= n * 1 => T(n) = O(n)

		// Κατάσταση 1 για τα απομείναντα στοιχεία.
		for(; i < leftSize; i++) {
			merged.add(left.get(i)); // Πολυπλοκότητα: O(1)

			if (!previousNeighbour.equals(left.get(i)) && previousNeighbour!=-1) { // Πολυπλοκότητα: O(1)
				reverses += (leftSize - i) * consecutiveNeighbours; // "Διορθώνω" την υπόθεση μη γειτονικότητας.	Πολυπλοκότητα: O(1)
				consecutiveNeighbours = 0; // Πολυπλοκότητα: O(1)
				previousNeighbour = -1; // Πολυπλοκότητα: O(1)
			}
		} // Πολυπλοκότητα παραπάνω βρόχου: Θα γίνει επανάληψη <=  k (έστω k πλήθος στοιχείων λίστας left) φορές, άρα αν θεωρήσουμε n = k + l, πολυπλοκότητα: T(n) = n * 5 = 5n => T(n) = O(n)

		// Κατάσταση 2α για τα απομείναντα στοιχεία.
		for(; j < rightSize; j++) {
			merged.add(right.get(j)); // Πολυπλοκότητα: O(1)
		} // Πολυπλοκότητα παραπάνω βρόχου: Θα γίνει επανάληψη l (έστω l πλήθος στοιχείων λίστας right)  <= n (έστω n = k + l) φορές, άρα πολυπλοκότητα: T(n) = n * 1 => T(n) = O(n)


		return new ReversesAndListPair(reverses, merged); // Πολυπλοκότητα: O(1)
	} // Συνολική πολυπλοκότητα: T(n) = 3*O(n) + O(1) = 3*O(n) => T(n) = O(n), για n = k + l (έστω k πλήθος στοιχείων λίστας left και έστω l πλήθος στοιχείων λίστας right).

	/**
	 * Διαβάζονται απο το αρχείο το όνομα του οποίου δίνεται ως (πρώτο) όρισμα κονσόλας και
	 * εμφανίζεται στην οθόνη το συνολικό κόστος λανθασμένης σειράς εγγράφων.
	 * @param args Ορίσματα κονσόλας.
	 */
	public static void main(String[] args) {
		try(Scanner parser = new Scanner(new BufferedReader(new FileReader(args[0])))){
			ArrayList<Integer> numbers = new ArrayList<>(); // Αρχικοποίηση πίνακα αριθμών εισόδου.

			while (parser.hasNextInt()) { // Εισαγωγή στοιχείων στον πίνακα.
				numbers.add(parser.nextInt());
			}
			
			ReversesAndListPair result = sortAndCount(numbers); // Εκτέλεση αλγορίθμου μέτρησης κόστους.
				
			System.out.printf("Total cost: %d", result.getReverses());	// Εμφάνιση αποτελεσμάτων.
			
		} catch (Exception e) { e.printStackTrace(); } // Σε περίπτωση σφάλματος τυπώνω το πρόβλημα στην προκαθορισμένη έξοδο.

	}
}