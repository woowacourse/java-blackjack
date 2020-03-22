package view.dto;

import domain.card.Hands;
import domain.gamer.Gamer;

/**
 *   PlayerDto, DealerDto의 인터페이스
 *
 *   @author ParkDooWon
 */
public class GamerDto {
	private String name;
	private Hands hands;
	private int totalScore;

	private GamerDto(Gamer gamer) {
		this.name = gamer.getName();
		this.hands = gamer.getHands();
		this.totalScore = hands.calculateTotalScore();
	}

	public static GamerDto from(Gamer gamer) {
		return new GamerDto(gamer);
	}

	public String getName() {
		return name;
	}

	public Hands getHands() {
		return hands;
	}

	public int getTotalScore() {
		return totalScore;
	}
}
