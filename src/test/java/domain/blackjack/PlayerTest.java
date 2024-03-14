package domain.blackjack;

import static domain.card.FirstCardSelectStrategy.FIRST_CARD_SELECT_STRATEGY;
import static domain.card.TestCards.ACE_HEART;
import static domain.card.TestCards.EIGHT_HEART;
import static domain.card.TestCards.JACK_HEART;
import static domain.card.TestCards.JACK_SPADE;
import static domain.card.TestCards.QUEEN_HEART;
import static domain.card.TestCards.TWO_HEART;

import domain.card.Card;
import domain.card.Deck;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    static Stream<Arguments> validatePlayerHasNextDrawChanceParameters() {
        return Stream.of(
                Arguments.of(TWO_HEART, false), Arguments.of(ACE_HEART, true)
        );
    }

    @Test
    @DisplayName("플레이어는 총합이 21이 넘으면 카드를 뽑을 수 없는지 검증")
    void validateDrawLimit() {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(
                HoldingCards.of(JACK_HEART, EIGHT_HEART, JACK_SPADE));
        Deck deck = Deck.of(TWO_HEART);
        DrawResult drawResult = blackJackGameMachine.draw(deck, FIRST_CARD_SELECT_STRATEGY, new PlayerCardDrawCondition(
                blackJackGameMachine));
        Assertions.assertThat(drawResult.getFailCause())
                .isEqualTo("카드를 더이상 뽑을 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("validatePlayerHasNextDrawChanceParameters")
    @DisplayName("플레이어의 다음 드로우 기회 유무를 제대로 판단하는지 검증")
    void validatePlayerHasNextDrawChance(Card cardInDeck, boolean expected) {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(HoldingCards.of(JACK_HEART, QUEEN_HEART));
        DrawResult drawResult = blackJackGameMachine.draw(Deck.of(cardInDeck), FIRST_CARD_SELECT_STRATEGY,
                new PlayerCardDrawCondition(blackJackGameMachine));
        Assertions.assertThat(drawResult.hasNextChance())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("배팅 금액이 0 이상의 정수인지 검증")
    void validateBettingMoney() {
        Assertions.assertThatThrownBy(() -> Player.from("플레이어", HoldingCards.of(), -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 음수일 수 없습니다.");
    }
}
