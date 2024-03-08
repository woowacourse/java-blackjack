package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.rule.Score;
import java.util.Objects;

public class Player {

    private final PlayerName name;
    private final Hand hand;

    public Player(PlayerName name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void appendCard(Card card) {
        hand.append(card);
    }

    public Score calculateHandScore() {
        return hand.calculateScore();
    }

    public boolean canHit() {
        return hand.canHit();
    }

    public int handSize() {
        return hand.countCard();
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
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name.getValue();
    }

    public Hand getHand() {
        return hand;
    }
}
