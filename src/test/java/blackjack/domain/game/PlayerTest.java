package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 플레이어는_이름과_카드를_가지고_있어야_한다() {
        // given
        Hand hand = new Hand(new Card(CardSuit.CLUB, CardRank.ACE));

        // when & then
        assertThatCode(() -> new Player("히로", hand, new BetAmount(1_000)))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이어는_모든_카드를_조회할_수_있다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand(card1, card2);

        Player player = new Player("꾹이", hand, new BetAmount(1_000));

        List<Card> expect = List.of(card1, card2);

        // when & then
        assertThat(player.getCards()).isEqualTo(expect);
    }

    @Test
    void 플레이어는_카드를_추가로_받을_수_있다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand(card1, card2);

        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);
        Player player = new Player("꾹이", hand, new BetAmount(1_000));

        List<Card> expect = List.of(card1, card2, newCard);

        // when
        player.takeCard(newCard);

        // then
        assertThat(player.getCards()).isEqualTo(expect);
    }

    @Test
    void 플레이어는_카드의_합이_21이하일_때만_카드를_더_뽑을_수_있다() {
        // given
        Hand hand = new Hand(
                new Card(CardSuit.CLUB, CardRank.ACE),
                new Card(CardSuit.DIAMOND, CardRank.FIVE)
        );

        Player player = new Player("히로", hand, new BetAmount(1_000));

        // when
        boolean actual = player.ableToTakeMoreCards();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    void 플레이어는_추가로_카드를_뽑을_지_결정할_수_있다() {
        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
        assertThat(player.canDecideToTakeMoreCard()).isTrue();
    }

    @Test
    void 플레이어는_이름을_가지고_있다() {
        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
        assertThat(player.getName()).isEqualTo("히로");
    }

    @Test
    void 플레이어는_가장_처음에_두장을_공개한다() {
        // given
        Hand hand = new Hand(
                new Card(CardSuit.SPADE, CardRank.TWO),
                new Card(CardSuit.HEART, CardRank.TWO)
        );
        Player player = new Player("히로", hand, new BetAmount(1_000));

        // when
        List<Card> startingCards = player.getStartingCards();

        // then
        assertThat(startingCards).hasSize(2);
    }
}
