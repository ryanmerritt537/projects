public class PayFriend {
	public static void main(String[] args){
		System.out.println("Enter Payment Amount");
		double amount = IO.readDouble();
		double fee = 0;
		if(amount <= 100)
			fee = 5;
		else if (amount > 100 && amount < 1000){
			if(amount <= 200){
				fee += 6;
			}else{
				fee += 0.03 * amount;
			}
		}
		else if(amount >= 1000 && amount < 10000){
			if(amount <= 1500){
				fee += 15;
			}else{
				fee += 0.01 * amount;
			}
		}
		else if (amount >= 10000){
			fee += 100; // fee for 1st 10000 will always add on 100
			if(amount <= 15000){
				fee += 0.02 * (amount - 10000); 
			}
			else if(amount > 15000){
				fee += 100; //for the 5000
				fee += (0.03 * (amount - 15000));
			}
		}
		IO.outputDoubleAnswer(fee);
	
	}
}
