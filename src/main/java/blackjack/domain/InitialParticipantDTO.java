package blackjack.domain;

import java.util.List;

public class InitialParticipantDTO {

    private final String name;
    private final List<Card> cards;

    public InitialParticipantDTO(Player player){
        this.name = player.getName();
        this.cards = player.getCards();
    }

    public InitialParticipantDTO(Dealer dealer){
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
