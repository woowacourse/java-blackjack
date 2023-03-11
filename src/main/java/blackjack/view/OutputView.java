package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.gameplayer.BlackJackParticipant;
import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;
import blackjack.domain.gameplayer.Players;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String INIT_END_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String INIT_START_MESSAGE = "딜러와 ";
    private static final String CARD_MESSAGE = "카드";
    private static final String DEALER_MESSAGE = "딜러";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_MESSAGE = " - 결과";
    private static final String FINAL_START_MESSAGE = "## 최종 수익";
    private static final String RESULT_DELIMITER = ": ";
    private static final String CARD_DELIMITER = ", ";
    private static final String EMPTY = "";

    public void printStart(Players players, Dealer dealer) {
        printStartMessage(players.getPlayersName());
        System.out.print(DEALER_MESSAGE + RESULT_DELIMITER);
        printCards(dealer.showCards(), System.lineSeparator());
        for (Player player : players) {
            printPlayerName(player);
            printCards(player.showCards(), System.lineSeparator());
        }
    }

    public void printPlayerName(Player player) {
        String name = player.showName();
        System.out.print(name + CARD_MESSAGE + RESULT_DELIMITER);
    }

    private void printStartMessage(List<String> names) {
        int lastIndex = names.size() - 1;
        System.out.println(INIT_START_MESSAGE);
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(names.get(i) + CARD_DELIMITER);
        }
        System.out.println(names.get(lastIndex) + INIT_END_MESSAGE);
    }

    public void printCards(List<Card> cards, String end) {
        int lastIndex = cards.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            Card card = cards.get(i);
            System.out.print(card.getCardNumberToString() + card.getCardSymbolToString() + CARD_DELIMITER);
        }
        Card card = cards.get(lastIndex);
        System.out.print(card.getCardNumberToString() + card.getCardSymbolToString() + end);
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public void printGameResult(Dealer dealer, Players players) {
        printDealerResult(dealer);
        for (Player player : players) {
            printPlayerResult(player);
        }
    }

    private void printDealerResult(Dealer dealer) {
        int score = dealer.calculateScore().getScore();
        System.out.print(DEALER_MESSAGE);
        printCards(dealer.showAllCards(), EMPTY);
        System.out.println(RESULT_MESSAGE + RESULT_DELIMITER + score);
    }

    private void printPlayerResult(Player player) {
        printPlayerName(player);
        printCards(player.showCards(), EMPTY);
        int score = player.calculateScore().getScore();
        System.out.println(RESULT_MESSAGE + RESULT_DELIMITER + score);
    }

    public void printBettingResult(int dealerBettingResults, Map<Player, Integer> playerBettingResults) {
        System.out.println();
        System.out.println(FINAL_START_MESSAGE);
        printBlackJackParticipantsBettingResult(DEALER_MESSAGE, dealerBettingResults);
        for (Player player : playerBettingResults.keySet()) {
            printBlackJackParticipantsBettingResult(player.showName(), playerBettingResults.get(player));
        }
    }

    private void printBlackJackParticipantsBettingResult(String name, int betting) {
        System.out.println(name + RESULT_DELIMITER + betting);
    }
}
