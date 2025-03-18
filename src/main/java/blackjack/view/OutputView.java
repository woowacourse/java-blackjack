package blackjack.view;

import blackjack.gametable.BlackjackGame;

public interface OutputView {

    void printInitialGameSettings(BlackjackGame table);

    void printDealerOneMoreCardMessage();

    void printGameSummary(BlackjackGame gameTable);

    void printPlayerCards(BlackjackGame gameTable, String playerName);

    void printGameResult(BlackjackGame gameTable);
}
