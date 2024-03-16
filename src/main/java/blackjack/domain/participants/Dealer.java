package blackjack.domain.participants;

import static blackjack.domain.GameBoard.INITIAL_CARD_COUNT;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Deck;
import blackjack.domain.participants.GamerInformation.GamblingMoney;
import blackjack.domain.participants.GamerInformation.Name;

public class Dealer extends Gamer {
    public static final int DEALER_BOUNDARY_SCORE = 17;
    private static final Name dealerName = new Name("딜러");

    private final Deck allCardDeck;

    public Dealer() {
        super(dealerName);
        this.allCardDeck = new Deck();
    }

    public Card drawCard() {
        return allCardDeck.pickRandomCard();
    }

    public void setInitialHand() {
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            receiveCard(drawCard());
        }
    }

    public void gainMoney(GamblingMoney gamblingMoney) {
        playerStatus.addMoney(gamblingMoney);
    }

    @Override
    public boolean isNotOver() {
        return playerStatus.calculateScore() < DEALER_BOUNDARY_SCORE;
    }
}
