package blackjack.dto;

import blackjack.domain.role.Role;

public class DealerTurnDto {

	private final String name;
	private final boolean draw;
	private final int standard;

	private DealerTurnDto(String name, boolean draw, int standard) {
		this.name = name;
		this.draw = draw;
		this.standard = standard;
	}

	public static DealerTurnDto from(Role dealer, boolean draw, int standard) {
		return new DealerTurnDto(dealer.getName(), draw, standard);

	}

	public String getName() {
		return name;
	}

	public boolean isDraw() {
		return draw;
	}

	public int getStandard() {
		return standard;
	}
}
