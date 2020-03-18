package domain.result;

import java.util.List;

import domain.card.Card;

public interface ScoreBoard {
	String getName();

	int getScore();

	List<Card> getCards();
}
