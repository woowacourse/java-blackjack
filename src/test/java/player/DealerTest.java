package player;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardRank;
import card.CardSuit;
import card.Deck;
import card.DeckGenerator;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러는_초기에_카드를_1장_공개한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(deck);

        // when & then
        assertThat(dealer.openInitialCards().size())
                .isEqualTo(1);
    }

    @Test
    void 딜러는_이름이_딜러로_고정되어있다() {
        // given
        Dealer dealer = new Dealer();

        // when & then
        Assertions.assertThat(dealer.getName())
                .isEqualTo("딜러");
    }

    @Test
    void 점수합이_16이하면_TRUE를_반환한다() {
        // given
        Dealer dealer = new Dealer();
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.EIGHT),

                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT)
        )));
        dealer.drawOneCard(deck);

        // when & then
        Assertions.assertThat(dealer.drawOneCardIfLowScore(deck))
                .isTrue();
    }

    @Test
    void 점수합이_16이면_TRUE를_반환한다() {
        // given
        Dealer dealer = new Dealer();
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.EIGHT),

                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT)
        )));
        dealer.drawOneCard(deck);
        dealer.drawOneCard(deck);

        // when & then
        Assertions.assertThat(dealer.drawOneCardIfLowScore(deck))
                .isTrue();
    }

    @Test
    void 점수합이_16초과면_FALSE를_반환한다() {
        // given
        Dealer dealer = new Dealer();
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.EIGHT),

                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.TEN)
        )));
        dealer.drawOneCard(deck);
        dealer.drawOneCard(deck);

        // when & then
        Assertions.assertThat(dealer.drawOneCardIfLowScore(deck))
                .isFalse();
    }
}
