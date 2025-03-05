package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player implements GameAction {

    public static final int BLACKJACK_NUMBER = 21;

    private final String nickname;
    private final List<Card> cards;

    public Player(final String nickname, final List<Card> cards) {
        this.nickname = nickname;
        this.cards = cards;
    }

    public void receiveCards(final List<Card> givenCards) {
        cards.addAll(givenCards);
    }

    @Override
    public boolean canGetMoreCard() {
        int sum = cards.stream()
                .mapToInt(Card::getCardMinNumber)
                .sum();
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

    @Override
    public List<Card> getCards() {
        return cards;
    }
}
