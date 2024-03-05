package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PlayerTest {

    @DisplayName("이름과, 손 패를 가지고 있다.")
    @Test
    void createSuccess() {
        Name name = new Name("wiib");
        List<Card> cards = List.of(new Card(Letter.J, Mark.CLOVER), new Card(Letter.FIVE, Mark.SPADE));
        Hand hand = new Hand(cards);

        assertThatCode(() -> new Player(name, hand))
                .doesNotThrowAnyException();
    }

    @DisplayName("손 패 값의 합을 반환한다.")
    @Test
    void getTotalScore() {
        Card card1 = new Card(Letter.SEVEN, Mark.CLOVER);
        Card card2 = new Card(Letter.K, Mark.SPADE);
        List<Card> cards = List.of(card1, card2);

        Hand hand = new Hand(cards);
        Name name = new Name("산초!!");
        Player player = new Player(name, hand);
        int actual = player.getTotalScore();
        int expected = card1.getValue() + card2.getValue();

        assertThat(actual)
                .isEqualTo(expected);
    }

    @DisplayName("참가자가 카드를 손 패로 가져온다.")
    @Test
    void hit() {
        Card card1 = new Card(Letter.SEVEN, Mark.CLOVER);
        Card card2 = new Card(Letter.K, Mark.SPADE);
        List<Card> cards = new ArrayList<>(List.of(card1, card2));
        Hand hand = new Hand(cards);
        Name name = new Name("wiib");
        Card newCard = new Card(Letter.J, Mark.DIAMOND);

        Player player = new Player(name, hand);
        player.hit(newCard);

        assertThat(player.getHand().getCards()).contains(newCard);
    }

    @DisplayName("Bust인지 확인한다.")
    @Nested
    class isBust {

        @Test
        @DisplayName("Bust가 아니면 false를 반환한다.")
        void isBust_false() {
            Card card1 = new Card(Letter.SEVEN, Mark.CLOVER);
            Card card2 = new Card(Letter.SEVEN, Mark.SPADE);
            Card card3 = new Card(Letter.SEVEN, Mark.HEART);
            List<Card> cards = new ArrayList<>(List.of(card1, card2, card3));
            Hand hand = new Hand(cards);
            Name name = new Name("wiib");
            Player player = new Player(name, hand);

            boolean isBurst = player.isBust();

            assertThat(isBurst).isFalse();
        }

        @Test
        @DisplayName("Bust이면 true를 반환한다.")
        void isBust_true() {
            Card card1 = new Card(Letter.SEVEN, Mark.CLOVER);
            Card card2 = new Card(Letter.SEVEN, Mark.SPADE);
            Card card3 = new Card(Letter.EIGHT, Mark.HEART);
            List<Card> cards = new ArrayList<>(List.of(card1, card2, card3));
            Hand hand = new Hand(cards);
            Name name = new Name("wiib");
            Player player = new Player(name, hand);

            boolean isBurst = player.isBust();

            assertThat(isBurst).isTrue();
        }
    }
}
