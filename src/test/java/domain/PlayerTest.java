package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class PlayerTest {

    private Player player = new Player("이름");
    private Card jClover = new Card(Rank.JACK, Shape.CLOVER);
    private Card fiveHeart = new Card(Rank.FIVE, Shape.HEART);

    @Test
    void 플레이어는_2장의_카드를_받을수있다() {
        player.setUpCard(jClover, fiveHeart);

        Set<Card> cards = player.getCards();
        assertThat(cards).contains(jClover, fiveHeart);
    }

    @Test
    void 플레이어의_카드의_점수의_합을_구할수있다() {
        player.setUpCard(jClover, fiveHeart);

        var score = player.calculateScore();

        assertThat(score).isEqualTo(15);
    }

    @Test
    void A가있을때_나머지숫자의_합이_11이상이면_1을_선택() {
        Card aceHeart = new Card(Rank.ACE, Shape.HEART);
        Card aceClover = new Card(Rank.ACE, Shape.CLOVER);
        player.setUpCard(aceHeart, aceClover);

        var score = player.calculateScore();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void A가있을때_나머지숫자의_합이_10이하이면_11을_선택() {
        Card aceHeart = new Card(Rank.ACE, Shape.HEART);
        player.setUpCard(aceHeart, jClover);

        var score = player.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @Test
    void 플레이어의_카드를_추가한다() {
        player.setUpCard(jClover, fiveHeart);

        Card twoDiamond = new Card(Rank.TWO, Shape.DIAMOND);
        player.takeMore(twoDiamond);

        Set<Card> cards = player.getCards();
        assertThat(cards).contains(jClover, fiveHeart, twoDiamond);
    }
}
