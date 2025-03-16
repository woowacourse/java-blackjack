package domain.participant;

import domain.BettingAmount;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("플레이어 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerTest {

    @Test
    void 플레이어가_카드를_뽑으면_가지고있는_카드리스트에_추가된다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards cards = new Cards(
                List.of(new Card(Suit.HEART, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.NINE))
        );
        Player player = new Player(participantName, bettingAmount, cards);
        player.drawCard(List.of(new Card(Suit.CLOVER, Rank.TWO)));

        List<Card> expected = List.of(new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLOVER, Rank.TWO));

        assertThat(player.getCards()).isEqualTo(expected);
    }

    @Test
    void 플레이어가_가진_카드리스트의_합계가_21초과이면_true를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards exceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Player exceedPlayer = new Player(participantName, bettingAmount, exceedBlackjackScoreCards);

        assertThat(exceedPlayer.isBust()).isTrue();
    }

    @Test
    void 플레이어가_가진_카드리스트의_합계가_21이하이면_false를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards notExceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Player notExceedPlayer = new Player(participantName, bettingAmount, notExceedBlackjackScoreCards);

        assertThat(notExceedPlayer.isBust()).isFalse();
    }

    @Test
    void 플레이어가_가진_카드리스트의_총합을_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Player player = new Player(participantName, bettingAmount, cards);

        assertThat(player.getTotalRankSum()).isEqualTo(22);
    }

    @Test
    void 플레이어가_가진_카드리스트를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Player player = new Player(participantName, bettingAmount, cards);

        List<Card> expected = List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.DIAMOND, Rank.JACK));

        assertThat(player.getCards()).isEqualTo(expected);
    }

    @Test
    void 플레이어_이름을_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards cards = new Cards(List.of(new Card(Suit.DIAMOND, Rank.EIGHT)));
        Player player = new Player(participantName, bettingAmount, cards);

        assertThat(player.getParticipantName()).isEqualTo("duei");
    }

    @Test
    void 플레이어가_가진_카드리스트의_크기가_두장이면서_합계가_21이면_true를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards equalToBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Player isBlackjackPlayer = new Player(participantName, bettingAmount, equalToBlackjackScoreCards);

        assertThat(isBlackjackPlayer.isBlackjack()).isTrue();
    }

    @Test
    void 플레이어가_가진_카드리스트의_크기가_두장이면서_합계가_21초과이면_false를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards exceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.HEART, Rank.ACE)));
        Player exceedPlayer = new Player(participantName, bettingAmount, exceedBlackjackScoreCards);

        assertThat(exceedPlayer.isBlackjack()).isFalse();
    }

    @Test
    void 플레이어가_가진_카드리스트의_크기가_두장이면서_합계가_21이하이면_false를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards notExceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.QUEEN),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Player notExceedPlayer = new Player(participantName, bettingAmount, notExceedBlackjackScoreCards);

        assertThat(notExceedPlayer.isBlackjack()).isFalse();
    }

    @Test
    void 플레이어가_가진_카드리스트의_크기가_두장이아니고_합계가_21이면_false를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards sizeNotTwoCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.THREE)));
        Player sizeNotTwoPlayer = new Player(participantName, bettingAmount, sizeNotTwoCards);

        assertThat(sizeNotTwoPlayer.isBlackjack()).isFalse();
    }

    @Test
    void 플레이어가_카드를_뽑아야하는_상황이면_true를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards shouldHitCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.TWO)));

        Player shouldHitPlayer = new Player(participantName, bettingAmount, shouldHitCards);

        assertThat(shouldHitPlayer.shouldHit()).isTrue();
    }

    @Test
    void 플레이어가_카드를_뽑지않아도_되는_상황이면_false를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards equalToBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.THREE)));

        Cards exceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));

        Player shouldNotHitPlayer1 = new Player(participantName, bettingAmount, equalToBlackjackScoreCards);
        Player shouldNotHitPlayer2 = new Player(participantName, bettingAmount, exceedBlackjackScoreCards);

        assertAll(
                () -> assertThat(shouldNotHitPlayer1.shouldHit()).isFalse(),
                () -> assertThat(shouldNotHitPlayer2.shouldHit()).isFalse()
        );
    }
}
