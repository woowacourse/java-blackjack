package player;

import card.Card;
import card.CardRank;
import card.CardSuit;
import card.Deck;
import card.DeckGenerator;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러는_초기에_카드를_1장_공개한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Dealer dealer = new Dealer(deck);
        dealer.receiveInitialCards();

        // when & then
        assertThat(dealer.openInitialCards().size())
                .isEqualTo(1);
    }

    @Test
    void 딜러는_초기에_카드를_2장씩_받는다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Dealer dealer = new Dealer(deck);

        // when
        dealer.receiveInitialCards();

        // then
        Assertions.assertThat(dealer.getHandCards().size())
                .isEqualTo(2);
    }

    @Test
    void 점수합이_16이하면_TRUE를_반환한다() {
        // given
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
        Dealer dealer = new Dealer(deck);
        dealer.drawOneCardIfLowScore();

        // when & then
        Assertions.assertThat(dealer.drawOneCardIfLowScore())
                .isTrue();
    }

    @Test
    void 점수합이_16이면_TRUE를_반환한다() {
        // given
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
        Dealer dealer = new Dealer(deck);
        dealer.drawOneCardIfLowScore();
        dealer.drawOneCardIfLowScore();

        // when & then
        Assertions.assertThat(dealer.drawOneCardIfLowScore())
                .isTrue();
    }

    @Test
    void 점수합이_16초과면_FALSE를_반환한다() {
        // given
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
        Dealer dealer = new Dealer(deck);
        dealer.drawOneCardIfLowScore();
        dealer.drawOneCardIfLowScore();

        // when & then
        Assertions.assertThat(dealer.drawOneCardIfLowScore())
                .isFalse();
    }
}
