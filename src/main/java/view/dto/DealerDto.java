package view.dto;

import domain.card.Hands;
import domain.gamer.Dealer;

/**
 *    딜러 DTO 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class DealerDto {
	private String name;
	private Hands hands;

	private DealerDto(String name, Hands hands) {
		this.name = name;
		this.hands = hands;
	}

	public static DealerDto from(Dealer dealer) {
		return new DealerDto(dealer.getName(), dealer.getHands());
	}

	public String getName() {
		return name;
	}

	public Hands getHands() {
		return hands;
	}
}
