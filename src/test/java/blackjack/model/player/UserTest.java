package blackjack.model.player;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("pobi", 1_000);
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        user.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(user.getCards().getValues()).hasSize(3);
    }

    @Test
    void 자신이_가진_카드의_최적합을_반환한다() {
        user.receiveCards(new Cards(
                List.of(createCard(CardNumber.JACK), createCard(CardNumber.QUEEN), createCard(CardNumber.ACE))
        ));

        assertThat(user.calculatePoint()).isEqualTo(21);
    }

    @Test
    void 자신이_딜러인지_확인해준다() {
        assertThat(user.isDealer()).isFalse();
    }

    @MethodSource("유저가_블랙잭인지_확인한다_테스트_케이스")
    @ParameterizedTest
    void 유저가_블랙잭인지_확인한다(Cards cards, boolean expected) {
        user.receiveCards(cards);

        assertThat(user.isBlackjack()).isEqualTo(expected);
    }

    private static Stream<Arguments> 유저가_블랙잭인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.ACE))), true),
                Arguments.of(new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.JACK))), false),
                Arguments.of(new Cards(List.of(
                        createCard(CardNumber.TEN), createCard(CardNumber.JACK), createCard(CardNumber.ACE))), false
                )
        );
    }

    @MethodSource("유저가_버스트인지_확인한다_테스트_케이스")
    @ParameterizedTest
    void 유저가_버스트인지_확인한다(Cards cards, boolean expected) {
        user.receiveCards(cards);

        assertThat(user.isBust()).isEqualTo(expected);
    }

    private static Stream<Arguments> 유저가_버스트인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.TEN), createCard(CardNumber.TWO)),
                        true),
                Arguments.of(
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.TEN), createCard(CardNumber.ACE)),
                        false)
        );
    }

}
