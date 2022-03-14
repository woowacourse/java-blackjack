package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.EIGHT;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardPattern.HEART;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    private List<Card> cards;

    @BeforeEach
    void setup() {
        cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("플레이어의 이름에 null이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByNull(String input) {
        assertThatThrownBy(() -> Player
                .createNewPlayer(input, cards))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("플레이어의 이름은 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어의 이름에 공백이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByEmpty(String input) {
        assertThatThrownBy(() -> Player
                .createNewPlayer(input, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 공백이 들어올 수 없습니다.");
    }

    @Nested
    @DisplayName("플레이어의 hit 가능여부를 확인할 수 있다.")
    class CanHit {

        @Test
        @DisplayName("가능한 경우 true를 반환한다.")
        void canHit() {
            final Participant player = Player.createNewPlayer("user", cards);
            assertThat(player.canHit()).isTrue();
        }

        @Test
        @DisplayName("불가능한 경우 false를 반환한다.")
        void cannotHit() {
            final Participant player = Player.createNewPlayer("user", cards);
            player.hit(Card.of(SPADE, EIGHT));

            assertThat(player.canHit()).isFalse();
        }
    }

    @Test
    @DisplayName("hit이 불가능한데, 카드를 받으려 하면 예외를 발생시킨다.")
    void hitException() {
        final Participant player = Player.createNewPlayer("user", cards);
        player.hit(Card.of(SPADE, EIGHT));

        assertThatThrownBy(() -> player.hit(Card.of(SPADE, NINE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 턴이 종료되어 카드를 더 받을 수 없습니다.");
    }

    @Test
    @DisplayName("카드를 받을 수 있다.")
    void hit() {
        final Participant player = Player.createNewPlayer("user", cards);
        final Card card = Card.of(SPADE, A);
        player.hit(card);

        assertThat(player.cards()).contains(card);
    }

    @Test
    @DisplayName("턴을 종료할 수 있다.")
    void changeFinishStatus() {
        final Participant player = Player.createNewPlayer("user", cards);
        player.changeFinishStatus();

        assertThat(player.canHit()).isFalse();
    }

    @Test
    @DisplayName("턴이 종료되지 않은 경우에 카드의 합을 계산하려하면 예외를 발생시킨다.")
    void calculateResultScoreExceptionByNotEndTurn() {
        final Participant player = Player.createNewPlayer("user", cards);

        assertThatThrownBy(() -> player.calculateResultScore())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
    }

    @Test
    @DisplayName("턴이 끝나지 않은 유저와 서로 승부하려하면 예외를 발생시킨다.")
    void outcomeResultException() {
        final Participant player = Player.createNewPlayer("user", cards);
        final Participant dealer = Dealer.createNewDealer(createCards(Card.of(HEART, TEN), Card.of(HEART, SEVEN)));

        assertThatThrownBy(() -> player.fight(dealer))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료되지 않아 비교할 수 없습니다.");
    }
}
