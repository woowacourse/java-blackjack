package BlackJack;

public class Money {

	private Player player;
	private int money;
	private int result;
	
	Money(Player player, int money){
		this.player = player;
		this.money = money;
		this.result = 0;
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
	public int getResult() {
		return result;
	}
	
	public void setResult(int n) {
		this.result = n;
	}
	
	
	public int result(Dealer dealer,ResultView result) {
		Calculate calculate;
		calculate = new Calculate(dealer.getCards());
		int dealerSum = calculate.sum();
		calculate = new Calculate(player.getCards());
		int playerSum = calculate.sum();
		
		if(dealerSum > 21) {
			setResult(getMoney());
			return getResult();
		}
		if(playerSum > 21) {
			setResult(-getMoney());
			return getResult();
		}
		if(result.findWin(dealer, this) == 0) {
			setResult(getMoney());
			return getResult();
		}
		
		setResult(-getMoney());
		return getResult();
		
	}
}
