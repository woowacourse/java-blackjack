package view;

import domain.bet.Profit;
import domain.card.Card;
import domain.participant.Name;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = "\n";
    private static final String STRING_JOIN_DELIMITER = ", ";
    private static final String DISTRIBUTE_INITIAL_CARD = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_CARD = "딜러카드: %s";
    private static final String PLAYER_CARD = "%s카드: %s";
    private static final String SCORE = " - 결과: %d";
    private static final String DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_PROFIT = "## 최종 수익";
    private static final String DEALER_PROFIT = "딜러: %d";
    private static final String PLAYER_PROFIT = "%s: %d";

    public void printPlayers(List<Card> dealerCards, Map<Name, List<Card>> playerCards) {
        String playerNames = playerCards.keySet().stream()
                .map(Name::name)
                .collect(Collectors.joining(", "));

        System.out.printf(LINE_SEPARATOR + DISTRIBUTE_INITIAL_CARD + LINE_SEPARATOR, playerNames);
        printDealerFirstCard(dealerCards.getFirst());
        for (Name playerName : playerCards.keySet()) {
            printPlayerCard(playerName, playerCards.get(playerName));
        }
        System.out.print(LINE_SEPARATOR);
    }

    private void printDealerFirstCard(Card dealerCard) {
        System.out.printf(DEALER_CARD + LINE_SEPARATOR, dealerCard.rankString() + dealerCard.suitString());
    }

    public void printPlayerCard(Name name, List<Card> playerCards) {
        String card = collectCards(playerCards);

        System.out.printf(PLAYER_CARD + LINE_SEPARATOR, name.name(), card);
    }

    public void printDealerCardWithScore(List<Card> dealerCards, int score) {
        String card = collectCards(dealerCards);

        System.out.printf(LINE_SEPARATOR + DEALER_CARD + SCORE + LINE_SEPARATOR, card, score);
    }

    public void printPlayerCardWithScore(Name name, List<Card> playerCards, int score) {
        String card = collectCards(playerCards);

        System.out.printf(PLAYER_CARD + SCORE + LINE_SEPARATOR, name.name(), card, score);
    }

    private String collectCards(List<Card> cards) {
        return cards.stream()
                .map(card -> card.rankString() + card.suitString())
                .collect(Collectors.joining(STRING_JOIN_DELIMITER));
    }

    public void printDealerHit() {
        System.out.println(LINE_SEPARATOR + DEALER_DRAW);
    }

    public void printProfit(Profit dealerBetProfit, Map<Name, Profit> playerBetProfit) {
        System.out.println(LINE_SEPARATOR + FINAL_PROFIT);
        System.out.printf(DEALER_PROFIT + LINE_SEPARATOR, dealerBetProfit.amount());

        for (Name name : playerBetProfit.keySet()) {
            Profit profit = playerBetProfit.get(name);
            System.out.printf(PLAYER_PROFIT + LINE_SEPARATOR, name.name(), profit.amount());
        }
    }
}
