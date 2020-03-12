package dto;

import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Hands;
import domain.gamer.Player;

/**
 *    플레이어 DTO 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class PlayerDto {
	private String name;
	private Hands hands;

	private PlayerDto(String name, Hands hands) {
		this.name = name;
		this.hands = hands;
	}

	public static PlayerDto from(Player player) {
		return new PlayerDto(player.getName(), player.getHands());
	}

	public String showCards() {
		return this.name
			+ ": "
			+ hands.getCards().stream()
			.map(Card::shape)
			.collect(Collectors.joining(", "));
	}

	public boolean isDefeat(int dealerScore) {
		return ((hands.calculateTotalScore() > Hands.BLACKJACK) ||
			((dealerScore > hands.calculateTotalScore()) && (dealerScore <= Hands.BLACKJACK)));
	}

	public boolean isDraw(int dealerScore) {
		return (dealerScore == hands.calculateTotalScore()) && (dealerScore <= Hands.BLACKJACK);
	}

	public String getName() {
		return name;
	}

	public Hands getHands() {
		return hands;
	}
}
