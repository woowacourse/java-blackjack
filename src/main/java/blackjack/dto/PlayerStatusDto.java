package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerStatusDto {

    private final String name;
    private final List<String> cards;

    public PlayerStatusDto(Player player) {
        this.name = player.getName();
        this.cards = extractCardInfo(player);
    }

    private List<String> extractCardInfo(Player player) {
        List<String> cardInfo = new ArrayList<>();
        List<Card> inputCards = player.getHoldingCards().getCards();
        for (Card card : inputCards) {
            cardInfo.add(card.getNumber().getName()+card.getShape().getName());
        }
        return cardInfo;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
