package blackjack.domain;

public class Dealer extends Participant {

    private Dealer() {
    }

    public static Dealer startWithTwoCards(final Deck deck) {
        final Dealer dealer = new Dealer();
        dealer.drawCard(deck);
        dealer.drawCard(deck);
        return dealer;
    }

    public void continueDraw(Deck deck) {
        while (isPossibleToDrawCard()) {
            drawCard(deck);
        }
    }

    private boolean isPossibleToDrawCard() {
        return calculateCardSum() < 17;
    }

}
