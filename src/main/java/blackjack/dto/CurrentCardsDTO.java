package blackjack.dto;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;

import java.util.List;

public class CurrentCardsDTO {

    private final String name;
    private final List<Card> cards;

    public CurrentCardsDTO(Player player){
        this.name = player.getName();
        this.cards = player.getCards();
    }

    public CurrentCardsDTO(Dealer dealer){
        this.name = dealer.getName();
        this.cards = List.of(dealer.getCards().get(0));
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
