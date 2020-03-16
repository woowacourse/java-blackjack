package domain.gamer;

import domain.card.Card;
import domain.card.providable.CardProvidable;
import domain.result.Score;
import domain.result.WinLose;

import java.util.List;

public interface Gamer {
    void drawCard(CardProvidable cardProvidable);

    boolean canDrawMore();

    List<Card> showInitialCards();

    List<Card> showAllCards();

    Score calculateScore();

    WinLose determineWinLose(Gamer counterParts);
}
