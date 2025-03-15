package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 플레이어는_이름과_카드를_가지고_있어야_한다() {
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
    void 플레이어는_모든_카드를_조회할_수_있다() {
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
    void 플레이어는_카드를_추가로_받을_수_있다() {
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
    void 플레이어는_카드의_합이_21이하일_때만_카드를_더_뽑을_수_있다() {
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
    void 카드의_합이_한계를_넘으면_제한됨을_나타낸다() {
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
    void 플레이어는_챌린저이다() {
        Player player = new Player("히로", new Hand());
        assertThat(player.isChallenger()).isTrue();
    }

    @Test
    void 플레이어는_추가로_카드를_뽑을_지_결정할_수_있다() {
        Player player = new Player("히로", new Hand());
        assertThat(player.canDecideToTakeMoreCard()).isTrue();
    }

    @Test
    void 플레이어는_이름을_가지고_있다() {
        Player player = new Player("히로", new Hand());
        assertThat(player.doesHaveName()).isTrue();
    }

    @Test
    void 플레이어는_첫번째_카드만을_공개하지_않는다() {
        Player player = new Player("히로", new Hand());
        assertThat(player.shouldRevealSingleCard()).isFalse();
    }
}
