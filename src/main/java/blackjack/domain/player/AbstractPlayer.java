package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.PlayerCards;
import java.util.ArrayList;
import java.util.Objects;

public class AbstractPlayer implements Player {

    private static final int BLACKJACK_CARD_COUNT = 2;

    private static final int MAX_SCORE = 21;

    private final Name name;
    private final PlayerCards playerCards;

    public AbstractPlayer(Name name) {
        this.name = name;
        this.playerCards = new PlayerCards(new ArrayList<>());
    }

    @Override
    public void hit(Card card) {
        playerCards.add(card);
    }

    @Override
    public boolean isBust() {
        return getScore() > MAX_SCORE;
    }

    @Override
    public boolean isBlackjack() {
        if (!playerCards.containsCardNumber(CardNumber.ACE)) {
            return false;
        }
        if (playerCards.get().size() != BLACKJACK_CARD_COUNT) {
            return false;
        }
        return playerCards.getTotalScore() == MAX_SCORE;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public PlayerCards getPlayerCards() {
        return playerCards;
    }

    @Override
    public int getScore() {
        return playerCards.getTotalScore();
    }

    @Override
    public boolean isValidRange() {
        return getScore() < MAX_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractPlayer that = (AbstractPlayer) o;
        return Objects.equals(name, that.name) && Objects.equals(playerCards, that.playerCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, playerCards);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", playerCards=" + playerCards +
                '}';
    }
}
