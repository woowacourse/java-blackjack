package view.dto;

import java.util.stream.Collectors;

import domain.card.Card;
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

	public String showDealerInitialCard() {
		return this.name
			+ ": "
			+ "HIDDEN, "
			+ hands.getCards()
			.get(1)
			.shape();
	}

	public String showCards() {
		return this.name
			+ ": "
			+ hands.getCards().stream()
			.map(Card::shape)
			.collect(Collectors.joining(", "));
	}

	public int score() {
		return this.hands.calculateTotalScore();
	}
}
