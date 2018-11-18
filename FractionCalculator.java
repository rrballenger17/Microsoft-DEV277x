
import java.util.Scanner;

public class FractionCalculator {


	public String getOperation(Scanner input){

		String line = null;

		while(true){	
			System.out.print("Please enter an operation (+, -, /, *, =, or Q to quit): ");

			line = input.nextLine();

			if(line.equals("+") || line.equals("-") || line.equals("*") || line.equals("/") || line.equals("=") || line.equals("q") || line.equals("Q")){
				break;
			}

			System.out.println("Invalid input.");

		}
		return line;
	}


	private Fraction parseFraction(String input){

		if(input == null){
			return null;
		}

		if(!input.contains("/")){
			input = input + "/1";
		}

		String[] parts = input.split("/");
		
		if(parts.length != 2){
			return null;
		}

		Fraction frac = null;

		try{
			int top = Integer.parseInt(parts[0]);
			int bottom = Integer.parseInt(parts[1]);
			frac = new Fraction(top, bottom);
		}catch(Exception e){
			return null;
		}

		return frac;
	}


	public Fraction getFraction(Scanner input){

		Fraction frac = null;

		String userInput = null;

		while(true){

			System.out.print("Please enter a fraction (a/b) or integer (a), where a and b are integers and b is not zero: ");
		
			userInput = input.nextLine();

			frac = parseFraction(userInput);

			if(frac != null){
				break;
			}

			System.out.println("Invalid fraction.");

		}

		return frac;

	}


	private void testEqualResults(Fraction first, Fraction second){
    	String testEqual = first.toString() + " = " + second.toString() + " is ";

        first.toLowestTerms();

        second.toLowestTerms();

        if(first.equals(second)){
        	testEqual += "true";
        }else{
        	testEqual += "false";
        }

        testEqual += ".";

        System.out.println(testEqual);


	}

	private void printResults(String op, Fraction first, Fraction second, String result){

        //result.toLowestTerms();

        System.out.print(first.toString() + " ");

        System.out.print(op + " ");

		System.out.print(second.toString() + " ");

       	System.out.print("="  + " ");

       	System.out.println(result + " ");

       	

	}



    public static void main(String[] args) {

    	FractionCalculator fc = new FractionCalculator();

    	Scanner sc = new Scanner(System.in);

		System.out.println("\nFRACTION CALCULATOR");

    	while(true){
			System.out.println("-------------------------------------------------------------");

    		String op = fc.getOperation(sc);

    		if(op.toLowerCase().equals("q")){
        		break;
        	}

        	Fraction first = fc.getFraction(sc);
        	Fraction second = fc.getFraction(sc);

			String resultString = null;
        
        	if(op.equals("+")){
        		resultString = first.add(second).toString();
        	}else if(op.equals("-")){
				resultString = first.subtract(second).toString();
        	}else if(op.equals("*")){
        		resultString = first.multiply(second).toString();
        	}else if(op.equals("/")){
        		if(second.getNumerator() == 0){
        			resultString = "Undefined";
        		}else{
        			resultString = first.divide(second).toString();
        		}
        	}else{
        		fc.testEqualResults(first, second);
        		continue;
        	}

        	fc.printResults(op, first, second, resultString);

        }



        //System.out.println("Hello World!"); // Display the string.


    }



}











