package domain.blackjack;

import static domain.blackjack.TestHoldingCards.BLACK_JACK;
import static domain.blackjack.TestHoldingCards.DEAD_CARDS;
import static domain.blackjack.TestHoldingCards.ONLY_SEVEN_HEART;
import static domain.blackjack.TestHoldingCards.WIN_CARDS_WITHOUT_ACE;
import static domain.blackjack.TestHoldingCards.WIN_CARDS_WITH_ACE;
import static domain.card.Card.ACE_HEART;
import static domain.card.Card.EIGHT_HEART;
import static domain.card.Card.JACK_HEART;
import static domain.card.Card.QUEEN_HEART;
import static domain.card.Card.TWO_HEART;
import static domain.card.FirstCardSelectStrategy.FIRST_CARD_SELECT_STRATEGY;

import domain.card.Card;
import domain.card.Deck;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GamerTest {
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
    @DisplayName("게임 참가자가 카드를 뽑았을 때 점수가 올바르게 계산되는지 검증")
    void draw() {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(HoldingCards.of());
        Deck deck = Deck.of(JACK_HEART, EIGHT_HEART);
        blackJackGameMachine.draw(deck, FIRST_CARD_SELECT_STRATEGY, new PlayerCardDrawCondition(blackJackGameMachine));

        SummationCardPoint actual = blackJackGameMachine.calculateSummationCardPoint();
        SummationCardPoint expected = new SummationCardPoint(10);

        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("isDeadParameters")
    @DisplayName("게임 참가자의 점수가 21이 넘으면 죽었다고 판단하는지 검증")
    void isBust(Card additionalCard, boolean expected) {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(
                HoldingCards.of(JACK_HEART, QUEEN_HEART, additionalCard));

        Assertions.assertThat(blackJackGameMachine.isBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getSummationCardPointParameters")
    @DisplayName("게임 참가자의 점수가 잘 계산되는지 검증")
    void getSummationCardPoint(BlackJackGameMachine blackJackGameMachine, SummationCardPoint expected) {
        SummationCardPoint summationCardPoint = blackJackGameMachine.calculateSummationCardPoint();
        Assertions.assertThat(summationCardPoint)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("블랙잭인지 잘 판단하는지 검증")
    void isBlackJack() {
        Gamer player = Player.from("플레이어", BLACK_JACK);
        Assertions.assertThat(player.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("블랙잭이 아닌지 잘 판단하는지 검증")
    void isNotBlackJack() {
        Gamer player = Player.from("플레이어", WIN_CARDS_WITHOUT_ACE);
        Assertions.assertThat(player.isBlackJack()).isFalse();
    }
}
