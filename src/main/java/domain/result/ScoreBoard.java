package domain.result;

import java.util.List;

import domain.card.Card;
import domain.score.Score;

public interface ScoreBoard {
	String getName();

	Score getScore();

	List<Card> getCards();
}
