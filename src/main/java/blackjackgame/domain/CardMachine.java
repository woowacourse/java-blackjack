package blackjackgame.domain;

public class CardMachine {
    private final Deck deck;

    public CardMachine(final Deck deck) {
        this.deck = deck;
    }

    public void initPlayersCards(final Guests guests, final Dealer dealer) {
        for (int count = 0; count < 2; count++) {
            distributeCard(dealer);
            guests.getGuests()
                .forEach(this::distributeCard);
        }
    }

    public void distributeCard(final Player player) {
        player.addCard(deck.pickOne());
    }
}
