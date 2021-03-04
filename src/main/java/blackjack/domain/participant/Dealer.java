package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_THRESHOLD = 16;

    private final Deck deck;

    public Dealer() {
        super("딜러", new CardHand(new ArrayList<>()));
        this.deck = Deck.createShuffledDeck();
    }

    public void setBaseCard() {
        cardHand.add(drawCard());
        cardHand.add(drawCard());
    }

    public void deal(Player player) {
        player.receiveCard(drawCard());
    }

    public boolean shouldReceive() {
        return cardHand.dealerSum() <= DEALER_THRESHOLD;
    }

    public void setPlayersBaseCard(List<Player> players) {
        for (Player player: players) {
            player.receiveCard(drawCard());
            player.receiveCard(drawCard());
        }
    }

    public void pickAnotherCard() {
        // 뽑은 카드를 자기 패에 놓는다
        cardHand.add(drawCard());
    }

    private Card drawCard() {
        return deck.drawCard();
    }

    @Override
    public int getCardSum() {
        return cardHand.dealerSum();
    }
}
