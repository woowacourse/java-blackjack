package domain.gamer;

import domain.card.Card;
import domain.card.providable.CardProvidable;
import domain.gamer.action.TurnActions;
import domain.result.GameRule;
import domain.result.score.Score;
import domain.result.score.ScoreCalculable;

import java.util.List;

public interface Gamer {
    void drawInitialCards(CardProvidable cardProvidable);

    void playTurn(CardProvidable cardProvidable, GameRule gameRule, TurnActions turnActions);

    List<Card> openInitialCards();

    List<Card> openAllCards();

    Score calculateScore(ScoreCalculable scoreCalculable);
}
