package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player implements GameAction {

    private final String nickname;
    private final List<Card> cards;

    public Player(final String nickname, final List<Card> cards) {
        this.nickname = nickname;
        this.cards = cards;
    }

    @Override
    public List<Card> showInitialCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player player)) {
            return false;
        }
        return Objects.equals(nickname, player.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }

    public String getNickname() {
        return nickname;
    }

}
