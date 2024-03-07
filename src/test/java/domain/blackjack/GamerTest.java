package domain.blackjack;

import static domain.card.CardName.ACE;
import static domain.card.CardName.EIGHT;
import static domain.card.CardName.JACK;
import static domain.card.CardName.QUEEN;
import static domain.card.CardName.SEVEN;
import static domain.card.CardName.TWO;
import static domain.card.CardType.HEART;
import static domain.card.CardType.SPADE;

import domain.card.Card;
import domain.card.Deck;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

// TODO: GameResultCalculateTest처럼 인스턴스 변수 정의 필요
class GamerTest {
    public static Stream<Arguments> isDeadParameters() {
        return Stream.of(
                Arguments.of(new Card(TWO, HEART), true), Arguments.of(new Card(ACE, HEART), false)
        );
    }

    @Test
    @DisplayName("게임 참가자가 카드를 뽑았을 때 점수가 올바르게 계산되는지 검증")
    void draw() {
        Gamer Gamer = new Gamer("robin", HoldingCards.of());
        Deck deck = Deck.of(new Card(JACK, HEART), new Card(EIGHT, HEART));
        Gamer.draw(deck, new TestPlayerCardDrawStrategy(Gamer));

        SummationCardPoint actual = Gamer.getSummationCardPoint();
        SummationCardPoint expected = new SummationCardPoint(10);

        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어는 총합이 21이 넘으면 카드를 뽑을 수 없는지 검증")
    void validateDrawLimit() {
        Gamer Gamer = new Gamer("robin", HoldingCards.of(
                new Card(JACK, HEART), new Card(EIGHT, HEART), new Card(JACK, SPADE)
        ));
        Deck deck = Deck.of(
                new Card(TWO, SPADE)
        );

        Assertions.assertThatThrownBy(() -> Gamer.draw(deck, new TestPlayerCardDrawStrategy(Gamer)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 더이상 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("딜러는 총합이 16이 넘으면 카드를 뽑을 수 없는지 검증")
    void validateDealerDrawLimit() {
        Gamer gamer = new Gamer("robin", HoldingCards.of(
                new Card(JACK, HEART), new Card(SEVEN, HEART)
        ));
        Deck deck = Deck.of(
                new Card(TWO, SPADE)
        );

        Assertions.assertThatThrownBy(() -> gamer.draw(deck, new TestDealerCardDrawStrategy(gamer)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 더이상 뽑을 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("isDeadParameters")
    @DisplayName("게이머의 점수가 21이 넘으면 죽었다고 판단하는지 검증")
    void isDead(Card additionalCard, boolean expected) {
        Gamer gamer = new Gamer("robin", HoldingCards.of(
                new Card(JACK, HEART), new Card(QUEEN, HEART), additionalCard
        ));

        Assertions.assertThat(gamer.isDead()).isEqualTo(expected);
    }
}
