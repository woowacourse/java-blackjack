package blackjack.dto;

public class BettingDto {

	private final String name;
	private final String money;

	private BettingDto(String name, String money) {
		this.name = name;
		this.money = money;
	}

	public static BettingDto from(String name, String money) {
		return new BettingDto(name, money);
	}

	public String getName() {
		return name;
	}

	public String getMoney() {
		return money;
	}
}
