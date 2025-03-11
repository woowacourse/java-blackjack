package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.card.Rank;
import domain.card.Suit;
import domain.player.Dealer;
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
        dealer.openInitialCards();

        // when & then
        assertThat(dealer.getOpenedCards().size())
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
    void 점수합이_16이하면_true를_반환한다() {
        // given
        Dealer dealer = new Dealer();
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),

                new Card(Suit.SPADE, Rank.EIGHT)
        )));
        dealer.drawOneCard(deck);

        // when & then
        Assertions.assertThat(dealer.drawOneCardIfLowScore(deck))
                .isTrue();
    }
}