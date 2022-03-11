package blackjack.domain;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int GET_CARD_UPPER_BOUND = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean isFinished(final CardDeck cardDeck) {
        final PlayingCard peekedCard = cardDeck.justPeek();
        final int currentResult = playingCards.getResultWithPeekCard(peekedCard);
        if (currentResult > GET_CARD_UPPER_BOUND){
            playingCards.changeToBurst();
            return true;
        }
        return false;
    }

    @Override
    public void addCard(CardDeck cardDeck) {
        playingCards.addCard(cardDeck.pop());
    }

}
