package blackjackgame.domain.blackjack;

import static blackjackgame.domain.card.CardName.ACE;
import static blackjackgame.domain.card.CardName.JACK;
import static blackjackgame.domain.card.CardName.QUEEN;
import static blackjackgame.domain.card.CardName.TWO;
import static blackjackgame.domain.card.CardType.HEART;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.gamers.CardHolder;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardHolderTest {

    public static Stream<Arguments> isDeadParameters() {
        return Stream.of(
                Arguments.of(new Card(TWO, HEART), true), Arguments.of(new Card(ACE, HEART), false)
        );
    }

    @Test
    @DisplayName("이름과 손패를 갖는 게이머 클래스의 생성자를 만들 수 있다.")
    void createGamerWithNameAndHoldingCardsConstructorTest() {
        Assertions.assertThatCode(() ->
                new CardHolder("게이머", HoldingCards.of())
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("isDeadParameters")
    @DisplayName("게이머의 점수가 21이 넘으면 죽었다고 판단하는지 검증")
    void isDead(Card additionalCard, boolean expected) {
        CardHolder cardHolderGamer = new CardHolder("robin", HoldingCards.of(
                new Card(JACK, HEART), new Card(QUEEN, HEART), additionalCard
        ));

        Assertions.assertThat(cardHolderGamer.isDead()).isEqualTo(expected);
    }
}
