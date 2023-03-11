package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ERROR_HEADER = "[ERROR] ";
    private static final String CARD_SETUP_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ": ";
    private static final String JOINER = ", ";
    private static final String LINE_SEPARATOR = "";
    private static final String CARD = "카드";
    private static final String DEALER_POSSIBLE_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_IMPOSSIBLE_MESSAGE = "딜러는 17이상이라 카드를 받지 못합니다.";
    private static final String RESULT_MESSAGE = " - 결과: ";
    private static final String FINAL_RESULT = "## 최종 승패";

    public void showInitStatus(List<Player> players) {
        printMessage(LINE_SEPARATOR);
        String names = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(JOINER));

        printMessage(String.format(CARD_SETUP_MESSAGE, names));
    }

    public void showDealerFirstCard(Card card) {
        System.out.println(DEALER_NAME + DELIMITER + mapCard(card));
    }

    public void showPlayer(Player player) {
        printMessage(player.getName() + CARD + DELIMITER + convertCard(player.getCards()));
    }

    public void showPlayers(List<Player> players) {
        for (Player player : players) {
            showPlayer(player);
        }
        printMessage(LINE_SEPARATOR);
    }

    public void showDealerDrawPossible() {
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_POSSIBLE_MESSAGE);
    }

    public void showDealerDrawImpossible() {
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_IMPOSSIBLE_MESSAGE);
    }

    public void showTotalScore(Dealer dealer, List<Player> players) {
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_NAME + DELIMITER + convertCard(dealer.getCards())
                + RESULT_MESSAGE + dealer.calculateCurrentScore());

        for (Player player : players) {
            printMessage(player.getName() + DELIMITER + convertCard(player.getCards())
                    + RESULT_MESSAGE + player.calculateCurrentScore());
        }
    }

    public void showFinalResult(Map<Player, Result> results, List<Player> players) {
        printMessage(LINE_SEPARATOR);
        printMessage(FINAL_RESULT);
        printMessage(DEALER_NAME + DELIMITER + ResultMapper.getDealerResult(new ArrayList<>(results.values())));
        for (Player player : players) {
            printMessage(player.getName() + DELIMITER + ResultMapper.map(results.get(player)));
        }
    }

    private String convertCard(Hand inputHand) {
        return inputHand.getCards().stream()
                .map(this::mapCard)
                .collect(Collectors.joining(JOINER));
    }

    private String mapCard(Card card) {
        return DenominationMapper.map(card.getNumber()) + SuitMapper.map(card.getSymbol());
    }

    public void showError(String message) {
        printMessage(ERROR_HEADER + message);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
