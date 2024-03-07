package controller.dto;

import domain.Card;
import java.util.List;
import java.util.stream.Collectors;

public record HandStatus(
        String name,
        List<Card> cards
) {

    public String getCardInitStatus() {
        StringBuilder builder = new StringBuilder();
        if (name.equals("딜러")) {
            return buildDealerStatus(builder);
        }
        return buildGamerStatus(builder);
    }

    public String getCardFinalStatus() {
        StringBuilder builder = new StringBuilder();
        if (name.equals("딜러")) {
            return buildDealerFinalStatus(builder);
        }
        return buildGamerStatus(builder);
    }

    private String buildDealerFinalStatus(final StringBuilder builder) {
        builder.append("딜러 카드: ");
        builder.append(buildCardStatusMessage());
        return builder.toString();
    }

    private String buildDealerStatus(final StringBuilder builder) {
        builder.append("딜러: ");
        builder.append(getCardMessage(getFirstCard()));
        return builder.toString();
    }

    private String buildGamerStatus(final StringBuilder builder) {
        builder.append(name);
        builder.append("카드: ");
        builder.append(buildCardStatusMessage());
        return builder.toString();
    }

    private String buildCardStatusMessage() {
        return cards.stream()
                .map(this::getCardMessage)
                .collect(Collectors.joining(", "));
    }

    private String getCardMessage(final Card card) {
        return card.getName() + card.getShape();
    }

    private Card getFirstCard() {
        return cards.get(0);
    }
}
