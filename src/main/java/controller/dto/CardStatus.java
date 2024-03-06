package controller.dto;

import domain.Card;
import java.util.List;

public record CardStatus(
        String name,
        List<Card> cards
) {

    public String getCardStatus() {
        StringBuilder builder = new StringBuilder();
        if (name.equals("딜러")) {
            return buildDealerStatus(builder);
        }
        return buildGamerStatus(builder);
    }

    private String buildDealerStatus(final StringBuilder builder) {
        builder.append("딜러: ");
        builder.append(getCardMessage(getCardOf(0)));
        builder.append(name());
        return builder.toString();
    }

    private String buildGamerStatus(final StringBuilder builder) {
        builder.append(name);
        builder.append("카드: ");
        builder.append(getCardMessage(getCardOf(0)));
        builder.append(", ");
        builder.append(getCardMessage(getCardOf(1)));
        builder.append("\n");
        return builder.toString();
    }

    private String getCardMessage(final Card card) {
        return card.getName() + card.getShape();
    }

    private Card getCardOf(final int index) {
        return cards.get(index);
    }
}
