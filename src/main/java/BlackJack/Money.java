package BlackJack;

public class Money {

	private Player player;
	private int money;
	
	Money(Player player, int money){
		this.player = player;
		this.money = money;
	}
	
	public int getMoney() {
		return money;
	}
	public String getName() {
		return player.getName();
	}
	public Player getPlayer() {
		return player;
	}
	
	public int result(Dealer dealer,ResultView result) {
		Calculate calculate;
		calculate = new Calculate(dealer.getCards());
		int dealerSum = calculate.sum();
		calculate = new Calculate(player.getCards());
		int playerSum = calculate.sum();
		
		if(dealerSum > 21) {
			return getMoney();
		}
		if(playerSum > 21) {
			return -getMoney();
		}
		if(result.findWin(dealer, this) == 0) {
			return getMoney();
		}
		
		return -getMoney();
		
	}
}
