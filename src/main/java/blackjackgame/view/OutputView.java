package blackjackgame.view;

import java.util.List;
import java.util.Map;

import blackjackgame.domain.GameOutcome;
import blackjackgame.domain.ResultDto;
import blackjackgame.domain.card.Card;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void printCards(final String playerName, final List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.printf("%s%s: ", System.lineSeparator(), playerName);

        cards.forEach(card -> stringBuilder.append(card)
                .append(DELIMITER));

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(DELIMITER));
        System.out.print(stringBuilder);
    }

    public void dealerAddCard() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(final int score) {
        System.out.print(" - 결과: " + score);
    }

    public void printResult(ResultDto resultDto) {
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder("## 최종 승패" + System.lineSeparator() + "딜러: ");
        appendDealerResult(resultDto.getDealerResult(), stringBuilder);
        appendGuestsResult(resultDto.getGuestsResult(), stringBuilder);
        System.out.println(System.lineSeparator() + stringBuilder);
    }

    private void appendDealerResult(final Map<GameOutcome, Integer> dealerResult, final StringBuilder stringBuilder) {
        for (final GameOutcome gameOutcome : dealerResult.keySet()) {
            stringBuilder.append(dealerResult.get(gameOutcome))
                .append(gameOutcome.getOutcome())
                .append(" ");
        }
    }

    private void appendGuestsResult(final Map<String, GameOutcome> result, final StringBuilder stringBuilder) {
        for (final String guestName : result.keySet()) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(guestName)
                .append(": ")
                .append(result.get(guestName).getOutcome());
        }
    }

    public void printStartingCards(Map<String, List<Card>> startingCards) {
        for (String name : startingCards.keySet()) {
            printCards(name, startingCards.get(name));
        }
    }
}
