package blackjack.view;

import blackjack.gametable.BlackjackTable;

public interface OutputView {

    void printInitialGameSettings(BlackjackTable table);

    void printDealerOneMoreCardMessage();

    void printGameSummary(BlackjackTable gameTable);

    void printPlayerCards(BlackjackTable gameTable, String playerName);

    void printGameResult(BlackjackTable gameTable);
}
