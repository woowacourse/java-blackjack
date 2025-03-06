package blackjack.model.player;

import static blackjack.model.card.CardCreator.createCard;
import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer("딜러");
    }

    @Test
    void 자신이_가진_카드의_합을_반환한다() {
        dealer.receiveCards(new Cards(
                List.of(createCard(CardNumber.ACE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));
        List<Integer> expected = List.of(9, 19);

        assertThat(dealer.calculatePossiblePoints()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
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
        assertThat(dealer.hasRole(role)).isEqualTo(expected);
    }

    @Test
    void 자신의_최저_포인트를_계산한다() {
        dealer.receiveCards(new Cards(
                        List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.ACE))
        ));

        assertThat(dealer.getMinimumPoint()).isEqualTo(16);
    }

    @Test
    void 첫_번째_카드를_반환한다() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));
        dealer.receiveCards(cards);

        assertThat(dealer.getFirstCard()).isEqualTo(createCard(CardNumber.TEN));
    }

}
