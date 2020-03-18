package first.domain.gamer;

import first.domain.card.providable.CardProvidable;
import first.domain.result.WinLose;
import first.domain.score.Calculatable;

public interface BlackJackGameable {
    WinLose determineWinLose(BlackJackGameable counterParts);

    void drawCard(CardProvidable cardProvidable);

    Calculatable calculateScore();

    boolean canDrawMore();
}
