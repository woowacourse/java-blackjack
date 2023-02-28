package blackjackgame.domain;

public class CardMachine {
    Cards cards;

    public CardMachine(Cards cards) {
        this.cards = cards;
    }

    public void initPlayersCards(Guests guests, Dealer dealer) {
        for (int count = 0; count < 2; count++) {
            giveCard(dealer);
            guests.getGuests()
                .forEach(this::giveCard);
        }
    }

    public void giveCard(Guest guest) {
        guest.addCard(cards.pickOne());
    }

    public void giveCard(Dealer dealer) {
        dealer.addCard(cards.pickOne());
    }
}
