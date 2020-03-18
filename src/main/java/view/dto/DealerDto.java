package view.dto;

import domain.card.Hands;
import domain.gamer.Dealer;

/**
 *    딜러 DTO 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class DealerDto implements GamerDto {
	private String name;
	private Hands hands;
	private int score;

	private DealerDto(String name, Hands hands) {
		this.name = name;
		this.hands = hands;
		this.score = hands.calculateTotalScore();
	}

	public static DealerDto from(Dealer dealer) {
		return new DealerDto(dealer.getName(), dealer.getHands());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Hands getHands() {
		return hands;
	}

	public int getScore() {
		return score;
	}
}
