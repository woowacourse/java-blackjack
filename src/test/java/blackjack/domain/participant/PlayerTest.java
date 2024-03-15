package blackjack.domain.participant;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.FixtureCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("참가자를 상속한다.")
    @Test
    void extendsTest() {
        Player player = new Player("산초");

        assertThat(player).isInstanceOf(Participant.class);
    }

    @DisplayName("손 패 값의 합을 반환한다.")
    @Test
    void getTotalScore() {
        Player player = new Player("산초");
        player.initHand(List.of(SEVEN_HEART, TEN_HEART));

        int actual = player.getTotalScore();
        int expected = SEVEN_HEART.getRankValue() + TEN_HEART.getRankValue();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드를 손 패로 가져온다.")
    @Test
    void hit() {
        Player player = new Player("위브");
        Card newCard = TWO_HEART;

        player.hit(newCard);

        List<Card> hand = player.getHand().getCards();
        assertThat(hand).contains(newCard);
    }

    @DisplayName("Bust인지 확인한다.")
    @Nested
    class isBust {

        @Test
        @DisplayName("Bust이면 true를 반환한다.")
        void isBust_true() {
            Player player = new Player("산초");
            player.initHand(List.of(TEN_HEART, TEN_HEART, TWO_HEART));

            boolean isBurst = player.isBust();

            assertThat(isBurst).isTrue();
        }

        @Test
        @DisplayName("Bust가 아니면 false를 반환한다.")
        void isBust_false() {
            Player player = new Player("산초");
            player.initHand(List.of(TEN_HEART, TEN_HEART, ACE_HEART));

            boolean isBurst = player.isBust();

            assertThat(isBurst).isFalse();
        }
    }

    @DisplayName("더 뽑을 수 있는지를 반환한다.")
    @Nested
    class isHittable {

        @Test
        @DisplayName("더 뽑을 수 있으면 true를 반환한다.")
        void isHittable_true() {
            Player player = new Player("산초");
            player.initHand(List.of(TEN_HEART, TEN_HEART));

            boolean isHittable = player.isHittable();

            assertThat(isHittable).isTrue();
        }

        @Test
        @DisplayName("더 이상 뽑을 수 없으면 false를 반환한다.")
        void isHittable_false() {
            Player player = new Player("산초");
            player.initHand(List.of(TEN_HEART, TEN_HEART, ACE_HEART));

            boolean isHittable = player.isHittable();

            assertThat(isHittable).isFalse();
        }
    }
}
