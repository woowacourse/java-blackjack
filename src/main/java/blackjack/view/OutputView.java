package blackjack.view;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String INIT_END_MSG = "에게 2장을 나누었습니다.";
    private static final String INIT_START_MSG = "딜러와 ";
    private static final String CARD_MSG = "카드: ";
    private static final String DEALER_MSG = "딜러: ";
    private static final String DEALER_HIT_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_MSG = " - 결과: ";
    private static final String FINAL_START_MSG = "## 최종 승패";
    private static final String RESULT_DELIMITER = ": ";
    private static final String CARD_DELIMITER = ", ";
    private static final String BLANK = " ";
    private static final String EMPTY = "";
    private static final int ZERO = 0;

    public void printStartMsg(List<String> names) {
        System.out.println();
        int lastIndex = names.size() - 1;
        System.out.print(INIT_START_MSG);
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(names.get(i) + CARD_DELIMITER);
        }
        System.out.println(names.get(lastIndex) + INIT_END_MSG);
    }

    public void printPlayerCards(Player player, String end) {
        String name = player.showName();
        List<Card> cards = player.showCards();
        int lastIndex = cards.size() - 1;
        System.out.print(name + CARD_MSG);
        printCards(cards, end, lastIndex);
    }

    public void printDealerCards(Dealer dealer, String end) {
        List<Card> cards = dealer.showAllCards();
        int lastIndex = cards.size() - 1;
        System.out.print(DEALER_MSG);
        printCards(cards, end, lastIndex);
    }

    private void printCards(List<Card> cards, String end, int lastIndex) {
        for (int i = 0; i < lastIndex; i++) {
            Card card = cards.get(i);
            System.out.print(card.getCardNumberToString() + card.getCardSymbolToString() + CARD_DELIMITER);
        }
        Card card = cards.get(lastIndex);
        System.out.print(card.getCardNumberToString() + card.getCardSymbolToString() + end);
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println(DEALER_HIT_MSG);
    }

    public void printDealerResult(Dealer dealer, int calculateScore) {
        System.out.println();
        printDealerCards(dealer, EMPTY);
        System.out.println(RESULT_MSG + calculateScore);
    }

    public void printPlayerResult(Player player) {
        printPlayerCards(player, EMPTY);
        int score = player.calculateScore().getScore();
        System.out.println(RESULT_MSG + score);
    }

    public void printEndMsg() {
        System.out.println();
        System.out.println(FINAL_START_MSG);
    }

    public void printPlayerWinningResult(Map<Player, Result> playerResult) {
        for (Player player : playerResult.keySet()) {
            System.out.println(player.showName() + RESULT_DELIMITER + playerResult.get(player).getResult());
        }
    }

    public void printDealerWinningResult(Map<Result, Integer> dealerResult) {
        StringBuilder dealerResultMsg = new StringBuilder(DEALER_MSG);
        for (Result result : dealerResult.keySet()) {
            if (dealerResult.get(result) != ZERO) {
                dealerResultMsg.append(dealerResult.get(result)).append(result.getResult()).append(BLANK);
            }
        }
        System.out.println(dealerResultMsg);
    }
}
