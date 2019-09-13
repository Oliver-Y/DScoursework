import java.util.Scanner;

public class NumberConverter3 {
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
		//for 32 bit system
		String bi = "";
		if (number > 0) {
			for (int x=0; Math.pow(2, x) <=number; x++) {
				int digit = (int)(((number % Math.pow(2, x+1)-(number % Math.pow(2, x)))
						/Math.pow(2, x)));
				bi = digit + bi;
			}
		} else if (number == 0) {
			bi = 0 + bi;
		} else if (number < 0) {
			System.out.println("Outputing binary in two's complement");
			int counter = 0;
			int complement = (int) Math.pow(2, 31) + number + 1;
			for (int x=0; Math.pow(2, x) <=complement; x++) {
				int digit = (int)(((complement % Math.pow(2, x+1)-(complement % Math.pow(2, x)))
						/Math.pow(2, x)));
				bi = digit + bi;
				counter += 1;
			}
			for (int x=0; x <= 31-counter; x += 1) {
				bi = "" + bi;
			}
			bi = 1 + bi;
		}
		
		return bi;
		/* 
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
		 */
	}
	
	public static String toOctal(int number) {
		String oc = "";
		boolean isNegative = false;
		if (number != 0) {
			if (number <0) {
				isNegative = true;
				number *= -1;
			}
			for (int x=0; Math.pow(8, x) <=number; x++) {
				int digit = (int)(((number % Math.pow(8, x+1)-(number % Math.pow(8, x)))
						/Math.pow(8, x)));
				oc = digit + oc;
			}
		} else if (number == 0) {
			oc = 0 + oc;
		} 
		
		oc = "0" + oc;
		
		if (isNegative) {
			oc = "-" + oc;
		}
		
		return oc;
	}
	
	public static String toHex(int number) {
		String hex = "";
		boolean isNegative = false;
		if (number != 0) {
			if (number <0) {
				isNegative = true;
				number *= -1;
			}
			
			for (int x=0; Math.pow(16, x) <=number; x++) {
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
		} else if (number == 0) {
			hex = 0 + hex;
		}
		hex = "0x" + hex;
		
		if (isNegative) {
			hex = "-" + hex;
		}
		
		return hex;
	}

}
