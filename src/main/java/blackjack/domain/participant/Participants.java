package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

import java.util.List;

public class Participants {
    private static final int DEALER_ADDITIONAL_CARD_STANDARD = 16;

    private final Participant dealer;
    private final List<Participant> players;

    public Participants(Participant dealer, List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void handOutCardToAll(CardDeck cardDeck) {
        handOutCardTo(dealer, cardDeck.pickCard());
        for (Participant player : players) {
            handOutCardTo(player, cardDeck.pickCard());
        }
    }

    public void handOutCardTo(Participant participant, Card pickedCard) {
        participant.receiveCard(pickedCard);
    }

    public boolean isDealerNeedAdditionalCard() {
        return dealer.getScore() <= DEALER_ADDITIONAL_CARD_STANDARD;
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players;
    }

}
