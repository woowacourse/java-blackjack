package blackjack.model;

import blackjack.model.card.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardUnit {
    private final Map<String, List<Card>> cardUnit;

    public CardUnit(Map<String, List<Card>> cardUnit) {
        this.cardUnit = cardUnit;
    }

    public Map<String, List<String>> getHandCardUnits() {
        Map<String, List<String>> handCardUnits = new HashMap<>();

        for (Map.Entry<String, List<Card>> handCard : cardUnit.entrySet()) {
            List<String> cardUnits = handCard.getValue().stream().map(Card::cardUnit)
                    .collect(Collectors.toList());
            handCardUnits.put(handCard.getKey(), cardUnits);
        }
        return handCardUnits;
    }
}
