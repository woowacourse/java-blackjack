package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static domain.FixtureCard.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PlayerTest {

    @DisplayName("이름과, 손 패를 가지고 있다.")
    @Test
    void createSuccess() {
        Name name = new Name("wiib");
        List<Card> cards = List.of(TEN_HEART, TWO_HEART);
        Hand hand = new Hand(cards);

        assertThatCode(() -> new Player(name, hand))
                .doesNotThrowAnyException();
    }

    @DisplayName("손 패 값의 합을 반환한다.")
    @Test
    void getTotalScore() {
        List<Card> cards = List.of(SEVEN_HEART, TEN_HEART);

        Hand hand = new Hand(cards);
        Name name = new Name("산초");
        Player player = new Player(name, hand);
        int actual = player.getTotalScore();
        int expected = SEVEN_HEART.getLetterValue() + TEN_HEART.getLetterValue();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("참가자가 카드를 손 패로 가져온다.")
    @Test
    void hit() {
        List<Card> cards = new ArrayList<>(List.of(SEVEN_HEART, TEN_HEART));
        Hand hand = new Hand(cards);
        Name name = new Name("wiib");
        Card newCard = TWO_HEART;

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
            List<Card> cards = new ArrayList<>(List.of(SEVEN_HEART, TEN_HEART));
            Hand hand = new Hand(cards);
            Name name = new Name("wiib");
            Player player = new Player(name, hand);

            boolean isBurst = player.isBust();

            assertThat(isBurst).isFalse();
        }

        @Test
        @DisplayName("Bust이면 true를 반환한다.")
        void isBust_true() {
            List<Card> cards = new ArrayList<>(List.of(TEN_HEART, TEN_HEART, TWO_HEART));
            Hand hand = new Hand(cards);
            Name name = new Name("wiib");
            Player player = new Player(name, hand);

            boolean isBurst = player.isBust();

            assertThat(isBurst).isTrue();
        }
    }

    @DisplayName("더 이상 뽑을 수 있는지를 반환한다.")
    @Nested
    class isHittable {

        @Test
        @DisplayName("더 이상 뽑을 수 없으면 false를 반환한다.")
        void isHittable_false() {
            List<Card> cards = new ArrayList<>(List.of(ACE_HEART, TEN_HEART));
            Hand hand = new Hand(cards);
            Name name = new Name("wiib");
            Player player = new Player(name, hand);

            boolean isHittable = player.isHittable();

            assertThat(isHittable).isFalse();
        }

        @Test
        @DisplayName("Hit할 수 있으면 true를 반환한다.")
        void isHittable_true() {
            List<Card> cards = new ArrayList<>(List.of(TEN_HEART, TEN_HEART));
            Hand hand = new Hand(cards);
            Name name = new Name("wiib");
            Player player = new Player(name, hand);

            boolean isHittable = player.isHittable();

            assertThat(isHittable).isTrue();
        }
    }
}
