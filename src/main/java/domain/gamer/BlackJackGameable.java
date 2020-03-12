package domain.gamer;

import domain.card.providable.CardProvidable;
import domain.result.WinLose;

public interface BlackJackGameable {
    WinLose determineWinLose(BlackJackGameable counterParts);

    void drawCard(CardProvidable cardProvidable);

    int calculateScore();

    boolean canDrawMore();
}
