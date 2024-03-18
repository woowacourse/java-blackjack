package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    @DisplayName("플레이어의 점수를 계산한다.")
    void calculateScoreTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Rank.KING),
                new Card(Shape.HEART, Rank.EIGHT)
        );
        Hand hand = new Hand(cards);
        // when, then
        assertThat(hand.calculateScore()).isEqualTo(new Score(18));
    }

    @Test
    @DisplayName("플레이어의 점수를 계산할 때, Ace의 점수를 유리한 방향(11)으로 결정한다.")
    void calculateAceAsElevenTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Rank.TEN),
                new Card(Shape.CLOVER, Rank.ACE)
        );
        Hand hand = new Hand(cards);
        // when, then
        assertThat(hand.calculateScore()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("플레이어의 점수를 계산할 때, Ace의 점수를 유리한 방향(1)으로 결정한다.")
    void calculateAceAsOneTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Rank.TEN),
                new Card(Shape.CLOVER, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.TEN)
        );
        Hand hand = new Hand(cards);
        // when, then
        assertThat(hand.calculateScore()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("블랙잭 여부를 확인한다.")
    void checkBlackJackTest() {
        List<Card> cardsBlackJack = List.of(
                new Card(Shape.CLOVER, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.TEN)
        );
        Hand handBlackJack = new Hand(cardsBlackJack);

        assertThat(handBlackJack.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("블랙잭 여부를 확인한다.")
    void checkNotBlackJackTest() {
        List<Card> cards21 = List.of(
                new Card(Shape.CLOVER, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.TEN),
                new Card(Shape.HEART, Rank.ACE)
        );
        Hand hand21 = new Hand(cards21);

        List<Card> cards20 = List.of(
                new Card(Shape.CLOVER, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.TEN)
        );
        Hand hand20 = new Hand(cards20);

        assertAll(
                () -> assertThat(hand21.isBlackJack()).isFalse(),
                () -> assertThat(hand20.isBlackJack()).isFalse()
        );
    }

    @Test
    @DisplayName("손에 카드를 한 장 추가한다.")
    void addCardTest() {
        List<Card> cards = new ArrayList<>();
        Hand hand = new Hand(cards);
        Card addedCard = new Card(Shape.HEART, Rank.FIVE);

        hand.addCard(addedCard);

        assertAll(
                () -> assertThat(hand.getCards().size()).isEqualTo(1),
                () -> assertThat(hand.getCards().get(0)).isEqualTo(addedCard)
        );
    }
}
