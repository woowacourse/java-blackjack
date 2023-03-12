package blackjackgame.view;

import blackjackgame.dto.CardDto;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void printFirstDealerCards(final String playerName, final List<CardDto> cards) {
        CardDto cardDto = cards.get(1);
        System.out.printf("%s%s: %s%s", System.lineSeparator(), playerName, cardDto.getValue(), cardDto.getSymbol());
        System.out.println();
    }

    public void printCards(final String playerName, final List<CardDto> cards) {
        System.out.println(formattingCards(playerName, cards));
    }

    private String formattingCards(final String playerName, final List<CardDto> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.printf("%s: ", playerName);
        for (final CardDto cardDto : cards) {
            stringBuilder.append(cardDto.getValue())
                    .append(cardDto.getSymbol())
                    .append(DELIMITER);
        }
        stringBuilder.delete(stringBuilder.lastIndexOf(DELIMITER), stringBuilder.length());
        return stringBuilder.toString();
    }

    public void dealerAddCard() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(final String playerName, final List<CardDto> cards, final int score) {
        System.out.println();
        System.out.println(formattingCards(playerName, cards) + " - 결과: " + score);
    }

    public void printBettingResult(final Map<String, Integer> bettingResult) {
        System.out.println();
        System.out.println();
        System.out.println("## 최종 수익");
        for(String name : bettingResult.keySet()) {
            System.out.println(name + ": " + bettingResult.get(name));
        }
    }
}
