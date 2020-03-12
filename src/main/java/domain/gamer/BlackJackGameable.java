package domain.gamer;

import domain.card.providable.CardProvidable;
import domain.result.WinLose;
import domain.score.Calculatable;

public interface BlackJackGameable {
    WinLose determineWinLose(BlackJackGameable counterParts);

    void drawCard(CardProvidable cardProvidable);

    Calculatable calculateScore();

    boolean canDrawMore();
}
