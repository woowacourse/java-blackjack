package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Choice;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import blackjack.domain.player.Player;

public class BlackjackMachine {

    private final CardDeck deck;

    public BlackjackMachine(final CardDeck deck) {
        this.deck = deck;
    }

    public void giveInitialCards(final Dealer dealer, final Guests guests) {
        addInitialCards(dealer);

        for (Guest guest : guests) {
            addInitialCards(guest);
        }
    }

    private void addInitialCards(final Player player) {
        player.takeCard(deck.pickCard());
        player.takeCard(deck.pickCard());
    }

    public void giveCardToGuest(final Guest guest, final Choice choice) {
        if (choice.isHit() && guest.canTakeCard()) {
            guest.takeCard(deck.pickCard());
        }
    }

    public void giveCardToDealer(final Dealer dealer) {
        while (dealer.canTakeCard()) {
            dealer.takeCard(deck.pickCard());
        }
    }
}
