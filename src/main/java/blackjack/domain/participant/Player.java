package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;
import java.util.Objects;

public class Player {

    private static final int REVEAL_COUNT = 2;

    private final PlayerName playerName;
    private final Hand hand;

    public Player(PlayerName playerName) {
        this(playerName, new Hand());
    }

    public Player(PlayerName playerName, Hand hand) {
        this.playerName = playerName;
        this.hand = hand;
    }

    public void deal(CardDeck cardDeck) {
        hand.appendInitial(cardDeck);
    }

    public Hand revealHand() {
        return hand.revealHand(REVEAL_COUNT);
    }

    public void draw(CardDeck cardDeck) {
        if (canHit()) {
            hand.append(cardDeck.popCard());
        }
    }

    public boolean canHit() {
        return hand.isPlayerHit();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public Score handScore() {
        return hand.calculateHandScore();
    }

    public PlayerName getName() {
        return playerName;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

    @Override
    public String toString() {
        return playerName.value();
    }
}
