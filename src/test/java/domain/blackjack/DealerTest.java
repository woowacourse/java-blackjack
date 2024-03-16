package domain.blackjack;

import static domain.card.TestCards.ACE_HEART;
import static domain.card.TestCards.FIVE_HEART;
import static domain.card.TestCards.JACK_HEART;
import static domain.card.TestCards.QUEEN_HEART;
import static domain.card.TestCards.SEVEN_HEART;
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

class DealerTest {

    @Test
    @DisplayName("딜러는 총합이 16이 넘으면 카드를 뽑을 수 없는지 검증")
    void validateDealerDrawLimit() {
        Deck deck = Deck.of(TWO_HEART);
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(HoldingCards.of(JACK_HEART, SEVEN_HEART));
        Dealer dealer = new Dealer(blackJackGameMachine);

        DrawResult drawResult = dealer.draw(deck);
        Assertions.assertThat(drawResult.getFailCause())
                .isEqualTo("카드를 더이상 뽑을 수 없습니다.");
    }

    static Stream<Arguments> validateDealerHasNextDrawChanceParameters() {
        return Stream.of(
                Arguments.of(TWO_HEART, false), Arguments.of(ACE_HEART, true)
        );
    }

    @ParameterizedTest
    @MethodSource("validateDealerHasNextDrawChanceParameters")
    @DisplayName("딜러의 다음 드로우 기회 유무를 제대로 판단하는지 검증")
    void validatePlayerHasNextDrawChance(Card cardInHand, boolean expected) {
        Deck deck = Deck.of(ACE_HEART);
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(
                HoldingCards.of(FIVE_HEART, QUEEN_HEART, cardInHand));
        Dealer dealer = new Dealer(blackJackGameMachine);

        DrawResult drawResult = dealer.draw(deck);
        Assertions.assertThat(drawResult.hasNextChance())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 하나가 숨겨진 상태로 조회되는지 검증")
    void getRawHoldingCardsWithoutFirstCard() {
        BlackJackGameMachine blackJackGameMachine = new BlackJackGameMachine(
                HoldingCards.of(FIVE_HEART, QUEEN_HEART));
        Dealer dealer = new Dealer(blackJackGameMachine);
        List<Card> rawHoldingCardsWithoutFirstCard = dealer.getRawHoldingCards(WithOutFirstCardShowStrategy.INSTANCE);
        Assertions.assertThat(rawHoldingCardsWithoutFirstCard)
                .containsExactly(QUEEN_HEART);
        Assertions.assertThat(dealer.getRawHoldingCards())
                .containsExactly(FIVE_HEART, QUEEN_HEART);
    }
}
