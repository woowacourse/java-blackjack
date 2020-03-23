package view.dto;

import domain.card.Hands;
import domain.gamer.Gamer;

/**
 *   class description
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class GamerDto {
	private String name;
	private Hands hands;
	private int score;

	private GamerDto(String name, Hands hands) {
		this.name = name;
		this.hands = hands;
		this.score = hands.calculateTotalScore();
	}

	public static GamerDto from(Gamer gamer) {
		return new GamerDto(gamer.getName(), gamer.getHands());
	}

	public String getName() {
		return name;
	}

	public Hands getHands() {
		return hands;
	}

	public int getScore() {
		return score;
	}
}
