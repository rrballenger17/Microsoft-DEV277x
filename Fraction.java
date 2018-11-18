
// to-do: for large inputs, test for integer overflow

public class Fraction {
	
	private int numerator;
	private int denominator;

	public Fraction(int numerator, int denominator){

		if(denominator == 0){
			throw new IllegalArgumentException();
		}

		if(denominator < 0){
			numerator *= -1;
			denominator *= -1;
		}

		this.numerator = numerator;
		this.denominator = denominator;

		//toLowestTerms();
	}

	public int getNumerator(){
		return numerator;
	}

	public int getDenominator(){
		return denominator;
	}

	public String toString(){

		if(denominator == 1){
			return "" + numerator;
		}


		return numerator + "/" + denominator;
	}

	public double toDouble(){
		return (double)numerator/ (double)denominator;
	}

	public Fraction add(Fraction other){

		int n1 = getNumerator();
		int d1 = getDenominator();

		int n2 = other.getNumerator();
		int d2 = other.getDenominator();


		n1 *= d2;
		n2 *= d1;

		d1 *= d2;

		Fraction result = new Fraction(n1 + n2, d1);

		result.toLowestTerms();

		return result;



	}

	public Fraction subtract(Fraction other){

		int n1 = getNumerator();
		int d1 = getDenominator();

		int n2 = other.getNumerator();
		int d2 = other.getDenominator();


		n1 *= d2;
		n2 *= d1;

		d1 *= d2;

		Fraction result = new Fraction(n1 - n2, d1);

		result.toLowestTerms();

		return result;

	}

	public Fraction multiply(Fraction other){

		int n1 = getNumerator();
		int d1 = getDenominator();

		int n2 = other.getNumerator();
		int d2 = other.getDenominator();

		Fraction result = new Fraction(n1 * n2, d1 * d2);

		result.toLowestTerms();

		return result;

	}

	public Fraction divide(Fraction other) throws IllegalArgumentException{

		if(other.getNumerator() == 0){
			throw new IllegalArgumentException();
		}

		int n1 = getNumerator();
		int d1 = getDenominator();

		int n2 = other.getNumerator();
		int d2 = other.getDenominator();

		Fraction result = new Fraction(n1 * d2, d1 * n2);

		result.toLowestTerms();

		return result;


	}

	public boolean equals(Object other){

		if(!(other instanceof Fraction)){
			return false;
		}

		Fraction otherFrac = (Fraction) other;

		return otherFrac.getNumerator() == getNumerator() 
			&& otherFrac.getDenominator() == getDenominator();

	}

	public void toLowestTerms(){

		int gcd = gcd(numerator, denominator);

		numerator /= gcd;

		denominator /= gcd;

	}

	public static int gcd(int num, int den){

		while(num != 0 && den != 0){

			int remainder = num % den;

			num = den;

			den = remainder;

		}

		return num + den;

	}



}



