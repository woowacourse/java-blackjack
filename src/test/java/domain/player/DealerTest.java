package domain.player;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.FIVE_HEART;
import static domain.fixture.BlackjackCardFixture.TEN_HEART;
import static domain.fixture.BlackjackCardFixture.THREE_HEART;
import static domain.fixture.BlackjackCardFixture.TWO_HEART;

import domain.card.Deck;
import domain.card.DeckGenerator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 초기_카드_분배_시_딜러는_2장을_받는다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Dealer dealer = Dealer.createDealer();

        // when
        dealer.drawInitialCards(deck);

        // when & then
        Assertions.assertThat(dealer.cards().size())
                .isEqualTo(2);
    }

    @Test
    void 초기_카드_공개_시_딜러는_카드_한장만_공개한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Dealer dealer = Dealer.createDealer();
        dealer.drawInitialCards(deck);

        // when
        dealer.openInitialCards();

        // when & then
        Assertions.assertThat(dealer.cards().openedCards().size())
                .isEqualTo(1);
    }

    @Test
    void 딜러는_카드합이_16이하인_동안_카드를_계속_뽑아야_한다() {
        // given
        Deck deck = new Deck(List.of(
                THREE_HEART, TWO_HEART, ACE_HEART,
                TEN_HEART, FIVE_HEART   // initial cards
        ));
        Dealer dealer = Dealer.createDealer();
        dealer.drawInitialCards(deck);

        // when
        dealer.hitWhileUnder16(deck);

        // then
        Assertions.assertThat(dealer.computeOptimalSum()).isEqualTo(18);
    }
}