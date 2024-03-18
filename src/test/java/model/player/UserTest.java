package model.player;

import model.card.Card;
import model.card.Cards;
import model.card.Denomination;
import model.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    static Stream<Arguments> createCard() {
        return Stream.of(Arguments.of(
                List.of(Card.of(Suit.SPACE, Denomination.NINE)),
                List.of(Card.of(Suit.SPACE, Denomination.NINE),
                        Card.of(Suit.SPACE, Denomination.FIVE),
                        Card.of(Suit.SPACE, Denomination.ACE))));
    }

    @DisplayName("초기 참가자는 카드를 2장만 받아야 한다.")
    @ParameterizedTest
    @MethodSource("createCard")
    void validateCardSize(List<Card> cards) {
        Assertions.assertThatThrownBy(() -> new User(new Cards(cards)) {
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드를 추가할 수 있다.")
    @Test
    void addCard() {
        User user = new User(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.FIVE)))) {
        };
        Card card = Card.of(Suit.ClUBS, Denomination.EIGHT);
        user.addCard(card);
        assertThat(user.cards().cards()).hasSize(3);
    }

    @DisplayName("카드 여러 개를 추가할 수 있다.")
    @Test
    void addCards() {
        User user = new User(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.FIVE)))) {
        };
        user.addCard(Card.of(Suit.ClUBS, Denomination.EIGHT));
        user.addCard(Card.of(Suit.ClUBS, Denomination.FIVE));

        assertThat(user.cards().cards()).hasSize(4);
    }

    @DisplayName("카드의 합을 계산한다.")
    @Test
    void calculateScore() {
        User user = new User(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.FIVE)))) {
        };

        assertThat(user.calculateScore()).isEqualTo(14);
    }

    @DisplayName("Ace가 나왔을 때는 21을 초과하지 않으며 21과 가까운 수가 되도록 11이나 1을 고른다.")
    @Test
    void calculateScoreWithAce() {
        User user = new User(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.FIVE)))) {
        };
        user.addCard(Card.of(Suit.SPACE, Denomination.ACE));

        assertThat(user.calculateScore()).isEqualTo(15);
    }
}
