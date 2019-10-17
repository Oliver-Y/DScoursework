import java.util.Scanner;



public class HelloWorld {
	public static void main(String[] arguments) {
		int number = getInteger();
		System.out.println("In binary: " + toBinary(number));
		System.out.println("In octal: " + toOctal(number));
		System.out.println("In hex: " + toHex(number));
	}

	public static int getInteger() {
		Scanner in = new Scanner(System.in);
		int number = -1;

		while(true) {
			System.out.print("Type an integer: ");
			try {
				number = in.nextInt();
	        }
	        catch (NumberFormatException e) {
	        	System.out.println("Please input an integer!");
	        	continue;
	        }
			break;
		}
		in.close();
		return number;
	}
	public static String toBinary(int number) {
		String bi = "";
		String neg_bi = ""; 
		int power = 0; 
		for (int i = 0; Math.pow(2, i) <= Math.abs(number); ++i) {
			power = i; 
		}
		
		if (number < 0) {
			System.out.println("its negative"); 
			int a = (int)Math.pow(2, power+1) - Math.abs(number); 		
			System.out.println(a); 
			for (int i = 0; Math.pow(2, i)<=a; i++) {
				int digit = (int)(((a % Math.pow(2, i+1)-(a % Math.pow(2, i)))
						/Math.pow(2, i)));
				neg_bi = digit + neg_bi;	
				}
			for (int j = neg_bi.length(); j < power; j++) {
				neg_bi = '0' + neg_bi;
			}
			neg_bi = '1' + neg_bi;
			return neg_bi; 
		}
		else {
			System.out.println("Its positive"); 
			for (int x=0; Math.pow(2, x) <= number; x++) {
				int digit = (int)(((number % Math.pow(2, x+1)-(number % Math.pow(2, x)))
						/Math.pow(2, x)));
				bi = digit + bi;
			}
			bi = '0' + bi;
			return bi;
		}

	}
	

	public static String toOctal(int number) {
		String oc = "";
		for (int x=0; Math.pow(8, x) <number; x++) {
			int digit = (int)(((number % Math.pow(8, x+1)-(number % Math.pow(8, x)))
					/Math.pow(8, x)));
			oc = digit + oc;
		}
		oc = "0" + oc;
		return oc;
	}

	

	public static String toHex(int number) {
		String hex = "";
		for (int x=0; Math.pow(16, x) <number; x++) {
			int digit = (int)(((number % Math.pow(16, x+1)-(number % Math.pow(16, x)))
					/Math.pow(16, x)));
			switch (digit) {
				case 10: hex = 'a' + hex; break;
				case 11: hex = 'b' + hex; break;
				case 12: hex = 'c' + hex; break; 
				case 13: hex = 'd' + hex; break;
				case 14: hex = 'e' + hex; break; 
				case 15: hex = 'f' + hex; break; 
				default: hex = digit + hex;
			}
		}
		hex = "0x" + hex;
		return hex;

	}



}

