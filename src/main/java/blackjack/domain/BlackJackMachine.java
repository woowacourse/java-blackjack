package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;

public class BlackJackMachine {

    private final CardDeck deck;

    public BlackJackMachine(final CardDeck deck) {
        this.deck = deck;
    }

    public void giveInitialCards(final Dealer dealer, final Participants participants) {
        addInitialCards(dealer);

        for (Participant participant : participants) {
            addInitialCards(participant);
        }
    }

    private void addInitialCards(final Player player) {
        player.addCard(deck.getCard());
        player.addCard(deck.getCard());
    }

    public void giveCardToPlayer(final Player player) {
        player.addCard(deck.getCard());
    }
}
