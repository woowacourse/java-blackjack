package blackjack.model.player;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
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

    @Test
    void 딜러인지_확인해준다() {
        assertThat(dealer.isDealer()).isTrue();
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
