package domain.player;

import static domain.FixtureCard.ACE_HEARTS;
import static domain.FixtureCard.SEVEN_HEARTS;
import static domain.FixtureCard.TEN_HEARTS;
import static domain.FixtureCard.TWO_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.Hand;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("이름과, 손 패를 가지고 있다.")
    @Test
    void createSuccess() {
        PlayerName playerName = new PlayerName("wiib");
        List<Card> cards = List.of(TEN_HEARTS, TWO_HEARTS);
        Hand hand = new Hand(cards);

        assertThatCode(() -> new Player(playerName, hand))
                .doesNotThrowAnyException();
    }

    @DisplayName("참가자가 카드를 손 패로 가져온다.")
    @Test
    void hit() {
        List<Card> cards = new ArrayList<>(List.of(SEVEN_HEARTS, TEN_HEARTS));
        Hand hand = new Hand(cards);
        PlayerName playerName = new PlayerName("wiib");
        Card newCard = TWO_HEARTS;

        Player player = new Player(playerName, hand);
        player.hit(newCard);

        assertThat(player.getHand().getCards()).contains(newCard);
    }

    @DisplayName("Bust인지 확인한다.")
    @Nested
    class isBust {

        @Test
        @DisplayName("Bust가 아니면 false를 반환한다.")
        void isBust_false() {
            List<Card> cards = new ArrayList<>(List.of(SEVEN_HEARTS, TEN_HEARTS));
            Hand hand = new Hand(cards);
            PlayerName playerName = new PlayerName("wiib");
            Player player = new Player(playerName, hand);

            boolean isBurst = player.isBust();

            assertThat(isBurst).isFalse();
        }

        @Test
        @DisplayName("Bust이면 true를 반환한다.")
        void isBust_true() {
            List<Card> cards = new ArrayList<>(List.of(TEN_HEARTS, TEN_HEARTS, TWO_HEARTS));
            Hand hand = new Hand(cards);
            PlayerName playerName = new PlayerName("wiib");
            Player player = new Player(playerName, hand);

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
            List<Card> cards = new ArrayList<>(List.of(ACE_HEARTS, TEN_HEARTS));
            Hand hand = new Hand(cards);
            PlayerName playerName = new PlayerName("wiib");
            Player player = new Player(playerName, hand);

            boolean isHittable = player.isHittable();

            assertThat(isHittable).isFalse();
        }

        @Test
        @DisplayName("Hit할 수 있으면 true를 반환한다.")
        void isHittable_true() {
            List<Card> cards = new ArrayList<>(List.of(TEN_HEARTS, TEN_HEARTS));
            Hand hand = new Hand(cards);
            PlayerName playerName = new PlayerName("wiib");
            Player player = new Player(playerName, hand);

            boolean isHittable = player.isHittable();

            assertThat(isHittable).isTrue();
        }
    }

    @Test
    @DisplayName("현재 플레이어가 블랙잭인지를 판단한다.")
    void isBlackJack() {
        List<Card> cards = new ArrayList<>(List.of(TEN_HEARTS, ACE_HEARTS));
        Hand hand = new Hand(cards);
        PlayerName playerName = new PlayerName("wiib");
        Player player = new Player(playerName, hand);

        boolean isBlackJack = player.isBlackJack();

        assertThat(isBlackJack).isTrue();
    }
}
