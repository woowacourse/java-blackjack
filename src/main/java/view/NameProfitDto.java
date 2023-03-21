package view;

public class NameProfitDto {

	final String name;
	final String profit;

	public NameProfitDto(final String name, final String profit) {
		this.name = name;
		this.profit = profit;
	}

	public String getName() {
		return name;
	}

	public String getProfit() {
		return profit;
	}
}
