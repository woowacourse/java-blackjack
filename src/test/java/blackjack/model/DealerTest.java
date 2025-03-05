package blackjack.model;

import static blackjack.model.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 카드_뭉치에서_N_개의_카드를_뽑아_반환한다() {
        CardDeck cardDeck = new CardDeck(
                new Cards(
                        List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX))
                )
        );
        Dealer dealer = new Dealer(cardDeck);

        Cards cards = dealer.draw(2);

        assertThat(cards.getValues()).hasSize(2);
    }

    @Test
    void 자신의_카드를_N_개_뽑는다() {
        CardDeck cardDeck = new CardDeck(
                new Cards(
                        List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX))
                )
        );
        Dealer dealer = new Dealer(cardDeck);

        dealer.drawSelf(2);

        assertThat(dealer.getCards().hasSize(2)).isTrue();
    }

    @Test
    void 자신이_가진_카드의_합을_반환한다() {
        CardDeck cardDeck = new CardDeck(
                new Cards(
                        List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
                )
        );

        Dealer dealer = new Dealer(cardDeck);

        dealer.drawSelf(3);
        int expected = dealer.getCards().sumAll();

        assertThat(dealer.calculateSumOfCards()).isEqualTo(expected);
    }

    @Test
    void 카드를_추가로_뽑아야_한다면_더_뽑은_후_참을_반환한다() {
        Rule fakeRule = new FakeRule(true);

        CardDeck cardDeck = new CardDeck(
                new Cards(
                        List.of(CardCreator.createCard(CardNumber.NINE), CardCreator.createCard(CardNumber.SIX))
                )
        );
        Dealer dealer = new Dealer(cardDeck);
        int before = dealer.getCards().getValues().size();

        boolean result = dealer.drawMoreCard(fakeRule);
        int after = dealer.getCards().getValues().size();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(after).isGreaterThan(before);
            softAssertions.assertThat(result).isTrue();
        });
    }

    @Test
    void 카드를_추가로_뽑을_수_없다면_뽑지_않은_후_거짓을_반환한다() {
        Rule fakeRule = new FakeRule(false);

        CardDeck cardDeck = new CardDeck(
                new Cards(
                        List.of(CardCreator.createCard(CardNumber.NINE), CardCreator.createCard(CardNumber.SIX))
                )
        );
        Dealer dealer = new Dealer(cardDeck);
        int before = dealer.getCards().getValues().size();

        boolean result = dealer.drawMoreCard(fakeRule);
        int after = dealer.getCards().getValues().size();

        assertSoftly(softAssertions -> {
            assertThat(after).isSameAs(before);
            assertThat(result).isFalse();
        });
    }

    private static class FakeRule extends Rule {

        private boolean shouldDealerDraw;

        public FakeRule(boolean shouldDealerDraw) {
            this.shouldDealerDraw = shouldDealerDraw;
        }

        @Override
        public boolean shouldDealerDraw(final Dealer dealer) {
            return shouldDealerDraw;
        }

        @Override
        public void drawDealerCards(final Dealer dealer) {
            dealer.drawSelf(1);
        }

        public void setShouldDealerDraw(boolean value) {
            shouldDealerDraw = value;
        }

    }

}
