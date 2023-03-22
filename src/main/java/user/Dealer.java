package user;

import card.Card;
import card.Deck;
import card.ShuffleMachine;
import java.util.List;
import playerState.PlayerStatus;
import playerState.dealerState.UnderSixteen;

public class Dealer {

    private final Deck deck;
    private PlayerStatus dealerStatus;

    public Dealer(PlayerStatus dealerStatus, ShuffleMachine shuffleMachine) {
        this.dealerStatus = dealerStatus;
        deck = new Deck(shuffleMachine);
    }

    public Card draw() {
        return deck.draw();
    }

    public List<Card> getCards() {
        return dealerStatus.getCards();
    }

    public void addCard(Card card) {
        dealerStatus = dealerStatus.addCard(card);
    }

    public boolean isUnderSixteen() {
        return getPoint() <= UnderSixteen.DEALER_THRESHOLDS && dealerStatus instanceof UnderSixteen;
    }

    public int getPoint() {
        return dealerStatus.getPoint();
    }

    public PlayerStatus getDealerStatus() {
        return dealerStatus;
    }
}
