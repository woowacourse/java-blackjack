package blackjack.model;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Rule rule;

    public BlackJackGame(final CardDeck cardDeck, final Rule rule) {
        this.cardDeck = cardDeck;
        this.rule = rule;
    }

    public boolean drawDealerCards(final Dealer dealer) {
        if (rule.shouldDealerDraw(dealer)) {
            dealer.receiveCards(draw(1));
            return true;
        }
        return false;
    }

    private Cards draw(final int drawSize) {
        return new Cards(cardDeck.draw(drawSize));
    }

}
