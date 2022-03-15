package blackjack.dto;

public class BettingDto {

	private final String name;
	private final Integer money;

	private BettingDto(String name, Integer money) {
		this.name = name;
		this.money = money;
	}

	public static BettingDto from(String name, Integer money) {
		return new BettingDto(name, money);
	}

	public String getName() {
		return name;
	}

	public Integer getMoney() {
		return money;
	}
}
