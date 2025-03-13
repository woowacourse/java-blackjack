package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 플레이어는_이름과_카드를_가진다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        // when & then
        assertThatCode(() -> new Player("히로", hand))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이어의_모든_카드를_가져온다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();

        hand.takeCard(card1);
        hand.takeCard(card2);

        Player player = new Player("꾹이", hand);

        List<Card> expect = List.of(card1, card2);

        // when & then
        assertThat(player.getCards()).isEqualTo(expect);
    }

    @Test
    void 플레이어는_카드를_가져올_수_있다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();

        hand.takeCard(card1);
        hand.takeCard(card2);

        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);
        Player player = new Player("꾹이", hand);

        List<Card> expect = List.of(card1, card2, newCard);

        // when
        player.takeCard(newCard);

        // then
        assertThat(player.getCards()).isEqualTo(expect);
    }

    @Test
    void 플레이어는_갖고_있는_카드들의_합이_모두_21이하일_때만_카드를_더_뽑을_수_있다() {
        // given
        Hand hand = new Hand();

        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        hand.takeCard(card1);
        hand.takeCard(card2);

        Player player = new Player("히로", hand);

        // when
        boolean actual = player.ableToTakeMoreCards();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    void 카드의_합이_특정_범위를_넘어가면_한계에_도달했음을_나타낸다() {
        // given
        Hand hand = new Hand();

        Card card1 = new Card(CardSuit.CLUB, CardRank.TEN);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.TEN);
        Card card3 = new Card(CardSuit.HEART, CardRank.TEN);
        hand.takeCard(card1);
        hand.takeCard(card2);
        hand.takeCard(card3);

        Player player = new Player("히로", hand);

        // when
        boolean overLimit = player.isOverLimit(21);

        // then
        assertThat(overLimit).isTrue();
    }

    @Test
    void 플레이어는_챌린저다() {
        Player player = new Player("히로", new Hand());
        assertThat(player.isChallenger()).isTrue();
    }

    @Test
    void 플레이어는_카드를_더_뽑는_것을_선택할_수_있다() {
        Player player = new Player("히로", new Hand());
        assertThat(player.canDecideToTakeMoreCard()).isTrue();
    }

    @Test
    void 플레이어는_이름이_있다() {
        Player player = new Player("히로", new Hand());
        assertThat(player.doesHaveName()).isTrue();
    }

    @Test
    void 플레이어는_승패의_횟수를_세지_않는다() {
        Player player = new Player("히로", new Hand());
        assertThat(player.tracksWinLossCount()).isFalse();
    }

    @Test
    void 플레이어는_첫번째_카드만을_공개하지_않는다() {
        Player player = new Player("히로", new Hand());
        assertThat(player.shouldRevealSingleCard()).isFalse();
    }
}
