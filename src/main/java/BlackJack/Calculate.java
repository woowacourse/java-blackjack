package BlackJack;

public class Calculate {

	private String[] shape= {"하트", "다이아몬드", "클로버", "스페이드"};
	private String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private Card cards;
	
	Calculate(Card cards){
		this.cards = cards;
	}
	
	public int change(String c) {
		char[] StringNum = c.toCharArray();
		char num = StringNum[StringNum.length-1];
		
		if(num>=50&&num<=57) {
			return num-48;
		}
		
		if(num == 'A') {
			return 1;
		}
		
		return 10;
	}
	
	public int sum() {
		int sum = 0;
		for(String c : cards.getCard()) {
			sum+=change(c);
		}
		
		return sum;
		
	}
	
	
}
