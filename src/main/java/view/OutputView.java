package view;

import domain.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printDealerCardWithHidden(final Card card) {
        System.out.println("딜러: " + getCardName(card));
    }

    public void printPlayerCards(final Map<String, List<Card>> playerToCard) {
        playerToCard.forEach((playerName, cards) ->
            System.out.println(getEachPlayerCards(playerName, cards)));
    }

    public void printDealerCardWithScore(final List<Card> cards, final int score) {
        printCardWithScore("딜러 ", cards, score);
    }

    public void printPlayerCardWithScore(final Map<String, List<Card>> playerToCard,
        final Map<String, Integer> playerToScore) {
        playerToCard.forEach((playerName, cards) -> {
            int score = playerToScore.get(playerName);
            printCardWithScore(playerName, cards, score);
        });
    }

    private void printCardWithScore(final String playerName, final List<Card> cards, final int score) {
        System.out.println(getEachPlayerCards(playerName, cards) + " - 결과: " + score);
    }

    private String getEachPlayerCards(final String playerName, final List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder(playerName);
        stringBuilder.append("카드: ");
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(getCardName(card));
        }
        stringBuilder.append(String.join(", ", cardNames));
        return stringBuilder.toString();
    }

    private String getCardName(final Card card) {
        List<String> cardName = card.getCardName();
        return cardName.get(0) + cardName.get(1);
    }

}
