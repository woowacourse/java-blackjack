package domain.participant;

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
        Cards cards = new Cards(
                List.of(new Card(Suit.HEART, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.NINE))
        );
        Participant player = new Player(participantName, cards);
        player.drawCard(List.of(new Card(Suit.CLOVER, Rank.TWO)));

        List<Card> expected = List.of(new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLOVER, Rank.TWO));

        assertThat(player.getCards()).isEqualTo(expected);
    }

    @Test
    void 플레이어가_가진_카드리스트의_합계가_21초과이면_true_아니면_false를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        Cards exceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Participant exceedPlayer = new Player(participantName, exceedBlackjackScoreCards);

        Cards notExceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Participant notExceedPlayer = new Player(participantName, notExceedBlackjackScoreCards);

        assertAll(
                () -> assertThat(exceedPlayer.isBust()).isTrue(),
                () -> assertThat(notExceedPlayer.isBust()).isFalse()
        );
    }

    @Test
    void 플레이어가_가진_카드리스트의_총합을_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Participant player = new Player(participantName, cards);

        assertThat(player.getTotalRankSum()).isEqualTo(22);
    }

    @Test
    void 플레이어가_가진_카드리스트를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Participant player = new Player(participantName, cards);

        List<Card> expected = List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.DIAMOND, Rank.JACK));

        assertThat(player.getCards()).isEqualTo(expected);
    }

    @Test
    void 플레이어_이름을_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        Cards cards = new Cards(List.of(new Card(Suit.DIAMOND, Rank.EIGHT)));
        Participant player = new Player(participantName, cards);

        assertThat(player.getParticipantName()).isEqualTo("duei");
    }

    @Test
    void 플레이어가_가진_카드리스트_두장의_합계가_21이면_true_아니면_false를_반환한다() {
        ParticipantName participantName = new ParticipantName("duei");
        Cards equalToBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Participant isBlackjackPlayer = new Player(participantName, equalToBlackjackScoreCards);

        Cards exceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Participant exceedPlayer = new Player(participantName, exceedBlackjackScoreCards);

        Cards notExceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.QUEEN),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Participant notExceedPlayer = new Player(participantName, notExceedBlackjackScoreCards);

        Cards exceedSizeCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.THREE)));
        Participant exceedSizePlayer = new Player(participantName, exceedSizeCards);

        assertAll(
                () -> assertThat(isBlackjackPlayer.isBlackjack()).isTrue(),
                () -> assertThat(exceedPlayer.isBlackjack()).isFalse(),
                () -> assertThat(notExceedPlayer.isBlackjack()).isFalse(),
                () -> assertThat(exceedSizePlayer.isBlackjack()).isFalse()
        );
    }
}
