package blackjack.model.blackjack_player;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardNumber;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    private static Stream<Arguments> 최적의_포인트를_계산한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.EIGHT),
                                createCard(CardNumber.ACE)
                        ),
                        19
                ),
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.EIGHT),
                                createCard(CardNumber.ACE),
                                createCard(CardNumber.NINE)
                        ),
                        18
                ),
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.EIGHT),
                                createCard(CardNumber.ACE),
                                createCard(CardNumber.NINE),
                                createCard(CardNumber.SIX)
                        ),
                        24
                )
        );
    }

    private static Stream<Arguments> 버스트인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.TEN), createCard(CardNumber.FIVE)),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.TEN), createCard(CardNumber.FIVE), createCard(CardNumber.SIX)),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.TEN), createCard(CardNumber.FIVE), createCard(CardNumber.SIX),
                                createCard(CardNumber.ACE)),
                        true
                ),
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.TEN), createCard(CardNumber.FIVE), createCard(CardNumber.SIX),
                                createCard(CardNumber.ACE), createCard(CardNumber.TWO)),
                        true
                )
        );
    }

    private static Stream<Arguments> 블랙잭인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.TEN), createCard(CardNumber.ACE)),
                        true
                ),
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.JACK), createCard(CardNumber.ACE)),
                        true
                ),
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.TEN), createCard(CardNumber.FIVE)),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.TEN), createCard(CardNumber.FIVE), createCard(CardNumber.SIX)),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                createCard(CardNumber.TEN), createCard(CardNumber.FIVE), createCard(CardNumber.SIX),
                                createCard(CardNumber.ACE)),
                        false
                )
        );
    }

    @Test
    void 비어_있는_패가_생성될_수_있다() {
        Hand hand = Hand.empty();

        assertThat(hand.getCards().getValues().size()).isEqualTo(0);
    }

    @Test
    void 카드들을_받아_패에_추가할_수_있다() {
        Hand hand = Hand.empty();

        BlackJackCards blackJackCards = new BlackJackCards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        );
        hand.addCards(blackJackCards);

        assertThat(hand.getCards().getValues()).hasSize(3);
    }

    @ParameterizedTest
    @MethodSource("최적의_포인트를_계산한다_테스트_케이스")
    void 최적의_포인트를_계산한다(final BlackJackCards blackJackCards, final int expected) {
        Hand hand = Hand.empty();

        hand.addCards(blackJackCards);

        assertThat(hand.calculateOptimalPoint()).isEqualTo(expected);
    }

    @Test
    void 최저_포인트를_계산한다() {
        Hand hand = Hand.empty();

        hand.addCards(new BlackJackCards(
                List.of(createCard(CardNumber.JACK), createCard(CardNumber.QUEEN), createCard(CardNumber.ACE))
        ));

        assertThat(hand.getMinimumPoint()).isEqualTo(21);
    }

    @ParameterizedTest
    @MethodSource("버스트인지_확인한다_테스트_케이스")
    void 버스트인지_확인한다(final BlackJackCards blackJackCards, final boolean expected) {
        Hand hand = Hand.empty();

        hand.addCards(blackJackCards);

        assertThat(hand.isBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("블랙잭인지_확인한다_테스트_케이스")
    void 블랙잭인지_확인한다(final BlackJackCards blackJackCards, final boolean expected) {
        Hand hand = Hand.empty();

        hand.addCards(blackJackCards);

        assertThat(hand.isBlackjack()).isEqualTo(expected);
    }
}
