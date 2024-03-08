package controller.dto;

import domain.Card;
import domain.Hand;
import java.util.stream.Collectors;

public record HandStatus(
        String name,
        Hand hand
) {

    public String getCardInitStatus() {
        StringBuilder builder = new StringBuilder();
        if (name.equals("딜러")) {
            return buildDealerStatus(builder);
        }
        return buildPlayerStatus(builder);
    }

    public String getCardFinalStatus() {
        StringBuilder builder = new StringBuilder();
        if (name.equals("딜러")) {
            return buildDealerFinalStatus(builder);
        }
        return buildPlayerStatus(builder);
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

    private String buildPlayerStatus(final StringBuilder builder) {
        builder.append(name);
        builder.append("카드: ");
        builder.append(buildCardStatusMessage());
        return builder.toString();
    }

    private String buildCardStatusMessage() {
        return hand.getCards().stream()
                .map(this::getCardMessage)
                .collect(Collectors.joining(", "));
    }

    private String getCardMessage(final Card card) {
        return card.getName() + card.getShape();
    }

    private Card getFirstCard() {
        return hand.getCards().get(0);
    }
}
