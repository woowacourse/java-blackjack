package blackjack.model.player;

import static blackjack.model.card.CardCreator.createCard;
import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @Test
    void 자신이_가진_카드의_합을_반환한다() {
        Dealer dealer = new Dealer("딜러");
        dealer.receiveCards(new Cards(
                List.of(createCard(CardNumber.ACE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));
        List<Integer> expected = List.of(9, 19);

        assertThat(dealer.calculatePossiblePoints()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        Dealer dealer = new Dealer("딜러");

        dealer.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(dealer.getCards().getValues()).hasSize(3);
    }

    @CsvSource(value = {
            "DEALER,true", "USER,false"
    })
    @ParameterizedTest
    void 자신의_역할과_같은_역할인지_확인한다(Role role, boolean expected) {
        Dealer dealer = new Dealer("딜러");

        assertThat(dealer.hasRole(role)).isEqualTo(expected);
    }

}
