package view;

import domain.card.Card;
import domain.enums.GameResult;
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
    private static final String FINAL_RESULT = "## 최종 승패";
    private static final String DEALER_RESULT = "딜러: %d승 %d패 %d무";
    private static final String PLAYER_RESULT = "%s: %s";
    private static final String FINAL_PROFIT = "## 최종 수익";
    private static final String DEALER_PROFIT = "딜러: %d";
    private static final String PLAYER_PROFIT = "%s: %d";

    public void printPlayers(List<Card> dealerCard, Map<Name, List<Card>> playerCards) {
        String playerNames = String.join(STRING_JOIN_DELIMITER, playerCards.keySet().toString());

        System.out.printf(LINE_SEPARATOR + DISTRIBUTE_INITIAL_CARD + LINE_SEPARATOR, playerNames);
        printDealerFirstCard(dealerCard.getFirst());
        for (Name playerName : playerCards.keySet()) {
            printPlayerCard(playerName, playerCards.get(playerName));
        }
        System.out.print(LINE_SEPARATOR);
    }

    private void printDealerFirstCard(Card dealerCard) {
        System.out.printf(DEALER_CARD + LINE_SEPARATOR, dealerCard.getRankString() + dealerCard.getSuitString());
    }

    public void printPlayerCard(Name name, List<Card> playerCard) {
        String card = collectCards(playerCard);

        System.out.printf(PLAYER_CARD + LINE_SEPARATOR, name, card);
    }

    public void printDealerCardWithScore(List<Card> dealerCard, int score) {
        String card = collectCards(dealerCard);

        System.out.printf(LINE_SEPARATOR + DEALER_CARD + SCORE + LINE_SEPARATOR, card, score);
    }

    public void printPlayerCardWithScore(Name name, List<Card> playerCard, int score) {
        String card = collectCards(playerCard);

        System.out.printf(PLAYER_CARD + SCORE + LINE_SEPARATOR, name, card, score);
    }

    private String collectCards(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getRankString() + card.getSuitString())
                .collect(Collectors.joining(STRING_JOIN_DELIMITER));
    }

    public void printDealerHit() {
        System.out.println(LINE_SEPARATOR + DEALER_DRAW);
    }

    public void printGameResult(Map<GameResult, Integer> dealerResult, Map<Name, GameResult> playerResults) {
        System.out.println(LINE_SEPARATOR + FINAL_RESULT);

        int winCount = dealerResult.getOrDefault(GameResult.WIN, 0);
        int loseCount = dealerResult.getOrDefault(GameResult.LOSE, 0);
        int drawCount = dealerResult.getOrDefault(GameResult.DRAW, 0);

        System.out.printf(DEALER_RESULT + LINE_SEPARATOR, winCount, loseCount, drawCount);
        for (Name playerName : playerResults.keySet()) {
            System.out.printf(PLAYER_RESULT + LINE_SEPARATOR, playerName,
                    playerResults.get(playerName).getDescription());
        }
    }

    public void printProfit(int dealerBetProfit, Map<Name, Integer> playerBetProfit) {
        System.out.println(LINE_SEPARATOR + FINAL_PROFIT);
        System.out.printf(DEALER_PROFIT + LINE_SEPARATOR, dealerBetProfit);

        for (Name name : playerBetProfit.keySet()) {
            System.out.printf(PLAYER_PROFIT + LINE_SEPARATOR, name, playerBetProfit.get(name));
        }
    }
}
