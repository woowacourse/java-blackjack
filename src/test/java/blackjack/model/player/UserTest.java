package blackjack.model.player;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("pobi");
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        user.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(user.getCards().getValues()).hasSize(3);
    }

    @Test
    void 자신이_가진_카드의_합을_반환한다() {
        user.receiveCards(new Cards(
                List.of(createCard(CardNumber.ACE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));
        List<Integer> expected = List.of(9, 19);

        assertThat(user.calculatePossiblePoints()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @CsvSource(value = {
            "DEALER,false", "USER,true"
    })
    @ParameterizedTest
    void 자신의_역할과_같은_역할인지_확인한다(Role role, boolean expected) {
        assertThat(user.hasRole(role)).isEqualTo(expected);
    }

    @Test
    void 자신의_최저_포인트를_계산한다() {
        user.receiveCards(new Cards(
                List.of(createCard(CardNumber.JACK), createCard(CardNumber.QUEEN), createCard(CardNumber.ACE))
        ));

        assertThat(user.getMinimumPoint()).isEqualTo(21);
    }

}
