package BlackJack;


public class Dealer extends Player{
	
	public Dealer(){
		super("딜러");
	}
	
	public boolean over(Calculate calculate, Deal deal) {
		if(calculate.sum()<17) {
			deal.dealOutCard(this);
			return true;
		}
		return false;
	}
}
