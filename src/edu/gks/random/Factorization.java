/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav 
 * Print all possible factors of the given number 
 * Avoid printing duplicates.
 */
public class Factorization {

	public static void factorize(int n) {
		if (n == 0 || n == 1)
			System.out.println(n);
		else
			_factorize("", 1, n);
	}

	private static void _factorize(String prefix, int p, int n) {
		if (n < 2)
			return;
		int _max = (int) Math.ceil(Math.sqrt(n));
		for (int i = 2; i <= _max; i++) {
			if ((n % i == 0)) {
				if (i >= p && n / i >= i) {
					String _newPref = prefix + i + "*";
					System.out.println(_newPref + (n / i));
					_factorize(_newPref, i, n / i);
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		factorize(12);
	}

}
