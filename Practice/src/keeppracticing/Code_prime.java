package keeppracticing;

public class Code_prime {

	public static void main(String[] args) {

		int a = 4;
		boolean flag = false;

		for (int i = 2; i <= a / 2; i++) {

			if (a % i == 0) {
				flag = true;
				break;

			}

		}
		
		if(!flag) {
			System.out.println("prime");
			
		}else {
			System.out.println("non prime");
		}

	}

}
