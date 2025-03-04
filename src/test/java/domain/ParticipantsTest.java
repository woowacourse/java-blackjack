package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class ParticipantsTest {

    private final Participants participants = new Player("이름");
    private final Card jClover = new Card(Rank.JACK, Shape.CLOVER);
    private final Card fiveHeart = new Card(Rank.FIVE, Shape.HEART);
    private final Card aceHeart = new Card(Rank.ACE, Shape.HEART);
    private final Card aceClover = new Card(Rank.ACE, Shape.CLOVER);
    private final Card twoDiamond = new Card(Rank.TWO, Shape.DIAMOND);

    @Test
    void 참가자는_2장의_카드를_받을수있다() {
        participants.setUpCards(jClover, fiveHeart);

        List<Card> cards = participants.getCards();
        assertThat(cards).contains(jClover, fiveHeart);
    }

    @Test
    void 참가자의_카드의_점수의_합을_구할수있다() {
        participants.setUpCards(jClover, fiveHeart);

        var score = participants.calculateScore();

        assertThat(score).isEqualTo(15);
    }

    @Test
    void A가있을때_나머지숫자의_합이_11이상이면_1을_선택() {
        participants.setUpCards(aceHeart, aceClover);

        var score = participants.calculateScore();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void A가있을때_나머지숫자의_합이_10이하이면_11을_선택() {
        participants.setUpCards(aceHeart, jClover);

        var score = participants.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @Test
    void 참가자의_카드를_추가한다() {
        participants.setUpCards(jClover, fiveHeart);

        participants.takeMore(twoDiamond);

        List<Card> cards = participants.getCards();
        assertThat(cards).contains(jClover, fiveHeart, twoDiamond);
    }
}
