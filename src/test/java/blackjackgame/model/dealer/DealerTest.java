package blackjackgame.model.dealer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjackgame.model.card.CardNumber;
import blackjackgame.model.card.CardShape;
import blackjackgame.model.card.StaticCardDispenser;
import java.util.List;
import blackjackgame.model.card.Card;
import blackjackgame.model.card.Cards;
import blackjackgame.model.participants.dealer.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 카드 합계가 16점 이하면 true를 반환한다")
    @Test
    void testCanAddCard() {
        Cards cards = createDealerCanAddCardTestCards();
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleAddCard()).isTrue();
    }

    private Cards createDealerCanAddCardTestCards() {
        StaticCardDispenser cardSixHeart = new StaticCardDispenser(CardNumber.SIX, CardShape.HEART);
        StaticCardDispenser cardJackHeart = new StaticCardDispenser(CardNumber.JACK, CardShape.HEART);
        return new Cards(List.of(new Card(cardSixHeart), new Card(cardJackHeart)));
    }

    @DisplayName("딜러의 카드 합계가 17점 이상이면 false를 반환한다")
    @Test
    void testCanNotAddCard() {
        Cards cards = createDealerCantAddCardTestCards();
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleAddCard()).isFalse();
    }

    private Cards createDealerCantAddCardTestCards() {
        StaticCardDispenser cardSevenHeart = new StaticCardDispenser(CardNumber.SEVEN, CardShape.HEART);
        StaticCardDispenser cardJackHeart = new StaticCardDispenser(CardNumber.JACK, CardShape.HEART);
        return new Cards(List.of(new Card(cardSevenHeart), new Card(cardJackHeart)));
    }

    @DisplayName("카드 1장을 획득하면 카드가 1개 증가한 딜러 객체를 반환한다")
    @Test
    void shouldAddCardWhenAllowed() {
        StaticCardDispenser cardTwoDia = new StaticCardDispenser(CardNumber.TWO, CardShape.DIAMOND);
        Cards cards = createDealerAddCardTestCards();
        Dealer dealer = new Dealer(cards);
        Card card = new Card(cardTwoDia);
        Dealer updatedDealer = dealer.withAdditionalCard(card);

        int expectedSize = cards.size() + 1;
        assertThat(updatedDealer.cardsSize()).isEqualTo(expectedSize);
    }

    private Cards createDealerAddCardTestCards() {
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        StaticCardDispenser cardJackHeart = new StaticCardDispenser(CardNumber.JACK, CardShape.HEART);
        return new Cards(List.of(new Card(cardAceHeart), new Card(cardJackHeart)));
    }
}
