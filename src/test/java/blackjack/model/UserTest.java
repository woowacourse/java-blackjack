package blackjack.model;

import static blackjack.model.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest {

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        User user = new User();

        user.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(user.getCards().getValues()).hasSize(3);
    }

    @Test
    void 자신이_가진_카드의_합을_반환한다() {
        User user = new User();
        user.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));
        int expected = user.getCards().sumAll();

        assertThat(user.calculateSumOfCards()).isEqualTo(expected);
    }

    @CsvSource(value = {
            "DEALER,false", "USER,true"
    })
    @ParameterizedTest
    void 자신의_역할과_같은_역할인지_확인한다(Role role, boolean expected) {
        User user = new User();

        assertThat(user.hasRole(role)).isEqualTo(expected);
    }

}
