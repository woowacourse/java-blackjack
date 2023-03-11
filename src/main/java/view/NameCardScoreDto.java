package view;

import java.util.List;

public class NameCardScoreDto {

	final String name;
	final List<String> cards;
	final String score;

	public NameCardScoreDto(String name, List<String> cards, String score) {
		this.name = name;
		this.cards = cards;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public List<String> getCards() {
		return cards;
	}

	public String getScore() {
		return score;
	}
}
