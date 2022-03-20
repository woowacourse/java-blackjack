package blackjack.dto;

public class BettingDto {

	private final String name;
	private final double money;

	private BettingDto(String name, double money) {
		this.name = name;
		this.money = money;
	}

	public static BettingDto from(String name, double money) {
		return new BettingDto(name, money);
	}

	public String getName() {
		return name;
	}

	public double getMoney() {
		return money;
	}
}
