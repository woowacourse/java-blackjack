package blackjack.dto;

import blackjack.domain.Card;

import java.util.List;
import java.util.Map;

public class InitialCardDto {
    private final Card dealerCard;
    private final Map<String, List<Card>> playerNameToCards;

    public InitialCardDto(final Card dealerCard, final Map<String, List<Card>> playerNameToCards) {
        this.dealerCard = dealerCard;
        this.playerNameToCards = playerNameToCards;
    }

    public Card getDealerCard() {
        return dealerCard;
    }

    public Map<String, List<Card>> getPlayerNameToCards() {
        return playerNameToCards;
    }
}
