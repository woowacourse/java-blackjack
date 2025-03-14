package blackjack.user;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import blackjack.game.GameResult;
import java.util.List;

public class Dealer {

    private static final int DEALER_OPEN_INITIAL_CARD_COUNT = 1;
    private final CardHand cards;

    public Dealer(CardHand cards) {
        this.cards = cards;
    }

    public List<Card> openInitialCards() {
        return cards.openInitialCards(DEALER_OPEN_INITIAL_CARD_COUNT);
    }

    public void addCards(CardDeck cardDeck, int count) {
        cards.addCards(cardDeck, count);
    }

    public boolean isPossibleToAdd() {
        return cards.isPossibleToAdd();
    }

    public GameResult judgePlayerResult(final Player player) {
        CardHand playerCards = player.getCards();
        if (playerCards.isBust()) {
            return GameResult.LOSE;
        }
        if (playerCards.isBlackjack() && this.cards.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (playerCards.isBlackjack() || this.cards.isBust()) {
            return GameResult.WIN;
        }
        return GameResult.fromDenominationsSum(this, player);
    }

    public CardHand getCards() {
        return cards;
    }
}
