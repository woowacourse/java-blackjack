package blackjackgame.dto;

import blackjackgame.domain.card.CardName;
import blackjackgame.domain.card.CardType;

public class CardDTO {
    private final CardName name;
    private final CardType cardType;

    public CardDTO(CardName name, CardType cardType) {
        this.name = name;
        this.cardType = cardType;
    }

    public CardName getCardName() {
        return name;
    }

    public CardType getCardType() {
        return cardType;
    }
}
