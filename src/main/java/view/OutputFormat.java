package view;

import domain.card.Card;
import domain.participant.Name;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputFormat {

    private static final String DELIMITER = ", ";

    public String formatPlayersNames(List<Name> names) {
        List<String> playersNames = names
                .stream()
                .map(Name::getValue)
                .toList();
        return String.format("딜러와 %s에게 2장을 나누었습니다.", String.join(DELIMITER, playersNames));
    }

    public String formatHands(Name name, List<Card> cards) {
        return String.format("%s카드: %s", name.getValue(), formatCards(cards));
    }

    public String formatCards(List<Card> cards) {
        return cards.stream()
                .map(this::formatCard)
                .collect(Collectors.joining(DELIMITER));
    }

    private String formatCard(Card card) {
        return card.getRank().getName() + card.getShape().getName();
    }

    public String formatParticipantScore(Name name, List<Card> cards, int score) {
        return String.format("%s - 결과: %d", formatHands(name, cards), score);
    }

    public String formatBetResult(Map.Entry<Name, Double> winStatusEntry) {
        return String.format("%s: %d",
                winStatusEntry.getKey().getValue(),
                winStatusEntry.getValue().intValue()
        );
    }
}
