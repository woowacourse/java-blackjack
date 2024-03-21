package blackjack.domain.dealer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.hand.Hand;
import fixture.CardFixture;
import fixture.HandFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 16 이하이면 카드를 추가로 받아야 한다.")
    @Test
    void testDraw() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal16();
        int beforeHandSize = hand.getCards().size();

        Dealer dealer = new Dealer(hand);

        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        // when
        dealer.draw(cardDeck);

        // then
        assertThat(dealer.getHand().getCards()).hasSize(beforeHandSize + 1);
    }

    @DisplayName("딜러는 17점 이상이면 카드를 추가로 받을 수 없다.")
    @Test
    void testDoNotDraw() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal16();
        hand.append(CardFixture.createAHeart());
        int beforeHandSize = hand.getCards().size();

        Dealer dealer = new Dealer(hand);

        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        // when
        dealer.draw(cardDeck);

        // then
        assertThat(dealer.getHand().getCards()).hasSize(beforeHandSize);
    }

    @DisplayName("한 장의 지급받은 카드를 공개한다.")
    @Test
    void testRevealFirstCard() {
        // given
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck(List.of(
                CardFixture.createAHeart(),
                CardFixture.create2Heart()
        ));

        dealer.deal(cardDeck);

        // when
        Card card = dealer.revealFirstCard();

        // then
        assertThat(card).isEqualTo(CardFixture.createAHeart());
    }
}
