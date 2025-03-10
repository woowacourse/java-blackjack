package blackjack.domain.participant;

import static blackjack.domain.card.Cards.BLACKJACK_NUMBER;

import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Gamer {

    private final String nickname;

    public Player(final String nickname, final Cards cards) {
        super(cards);
        this.nickname = nickname;
    }

    public static Player createEmpty(final String nickname) {
        return new Player(nickname, new Cards(new ArrayList<>()));
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
