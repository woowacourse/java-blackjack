package domain.blackjack;

import static domain.blackjack.TestHoldingCards.DEAD_CARDS;
import static domain.blackjack.TestHoldingCards.ONLY_SEVEN_HEART;
import static domain.blackjack.TestHoldingCards.WIN_CARDS_WITHOUT_ACE;
import static domain.blackjack.TestHoldingCards.WIN_CARDS_WITH_ACE;
import static domain.card.FirstCardSelectStrategy.FIRST_CARD_SELECT_STRATEGY;
import static domain.card.TestCards.ACE_HEART;
import static domain.card.TestCards.EIGHT_HEART;
import static domain.card.TestCards.JACK_HEART;
import static domain.card.TestCards.QUEEN_HEART;
import static domain.card.TestCards.TWO_HEART;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackJackGameMachineTest {
    public static Stream<Arguments> isDeadParameters() {
        return Stream.of(
                Arguments.of(TWO_HEART, true), Arguments.of(ACE_HEART, false)
        );
    }

    public static Stream<Arguments> getSummationCardPointParameters() {
        return Stream.of(
                Arguments.of(new BlackJackGameMachine(ONLY_SEVEN_HEART), new SummationCardPoint(7)),
                Arguments.of(new BlackJackGameMachine(WIN_CARDS_WITH_ACE), new SummationCardPoint(21)),
                Arguments.of(new BlackJackGameMachine(WIN_CARDS_WITHOUT_ACE), new SummationCardPoint(21)),
                Arguments.of(new BlackJackGameMachine(DEAD_CARDS), new SummationCardPoint(22))
        );
    }

    @Test
    @DisplayName("카드를 뽑았을 때 소유중인 카드에 제대로 반영되는지 검증")
    void draw() {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(HoldingCards.of());
        Deck deck = Deck.of(JACK_HEART, EIGHT_HEART);
        blackJackGameMachine.draw(deck, FIRST_CARD_SELECT_STRATEGY, new PlayerCardDrawCondition(blackJackGameMachine));

        List<Card> actual = blackJackGameMachine.getRawHoldingCards();

        Assertions.assertThat(actual)
                .containsExactly(JACK_HEART);
    }

    @Test
    @DisplayName("드로우 조건에 따라 드로우 가능 여부가 결정되는지 검증")
    void validateDrawLimit() {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(HoldingCards.of());
        Deck deck = Deck.of(TWO_HEART);
        DrawResult drawResult = blackJackGameMachine.draw(deck, FIRST_CARD_SELECT_STRATEGY, () -> true);
        Assertions.assertThat(drawResult.isSuccess()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isDeadParameters")
    @DisplayName("점수가 21이 넘으면 죽었다고 판단하는지 검증")
    void isBust(Card additionalCard, boolean expected) {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(
                HoldingCards.of(JACK_HEART, QUEEN_HEART, additionalCard));

        Assertions.assertThat(blackJackGameMachine.isBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getSummationCardPointParameters")
    @DisplayName("점수가 잘 계산되는지 검증")
    void getSummationCardPoint(BlackJackGameMachine blackJackGameMachine, SummationCardPoint expected) {
        SummationCardPoint summationCardPoint = blackJackGameMachine.calculateSummationCardPoint();
        Assertions.assertThat(summationCardPoint)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("블랙잭인 경우 블랙잭이라 하는지 검증")
    void isBlackJack() {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(TestHoldingCards.BLACK_JACK);
        Assertions.assertThat(blackJackGameMachine.isBlackJack())
                .isTrue();
    }

    @Test
    @DisplayName("블랙잭이 아닌 경우 블랙잭이 아니라 하는지 검증")
    void isNotBlackJack() {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(WIN_CARDS_WITH_ACE);
        Assertions.assertThat(blackJackGameMachine.isBlackJack())
                .isFalse();
    }
}
