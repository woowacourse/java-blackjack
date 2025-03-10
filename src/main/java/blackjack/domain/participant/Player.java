package blackjack.domain.participant;

import static blackjack.domain.card.Cards.BLACKJACK_NUMBER;

import blackjack.domain.card.Cards;
import java.util.Objects;

public class Player extends Gamer {

    private final String nickname;

    public Player(final String nickname, final Cards cards) {
        super(cards);
        this.nickname = nickname;
    }

    public void lose() {
        this.betAmount.loseAll();
    }

    public void win() {
        this.betAmount.getDouble();
    }

    public void draw() {
        this.betAmount.getOnce();
    }

    public void blackjack() {
        this.betAmount.getHalfTimes();
    }

    public boolean isBlackjack() {
        return cards.getSize() == 2 && calculateMaxSum() == BLACKJACK_NUMBER;
    }

    @Override
    public boolean canGetMoreCard() {
        int sum = cards.calculateMinSum();
        return sum < BLACKJACK_NUMBER;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Player player)) {
            return false;
        }
        return Objects.equals(nickname, player.nickname) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, cards);
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
