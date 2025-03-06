package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public class Player implements Actionable {

    public static final int BLACKJACK_NUMBER = 21;

    private final String nickname;
    private final Cards cards;

    public Player(final String nickname, final Cards cards) {
        this.nickname = nickname;
        this.cards = cards;
    }

    public void receiveCards(final Cards givenCards) {
        cards.addAll(givenCards);
    }

    @Override
    public boolean canGetMoreCard() {
        int sum = cards.calculateMinSum();
        return sum < BLACKJACK_NUMBER;
    }

    public int calculateMaxSum() {
        return cards.calculateResult();
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
    public List<Card> showCards() {
        return cards.getCards();
    }
}
