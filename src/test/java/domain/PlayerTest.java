package domain;

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
    void 플레이어가_카드를_뽑는다() {
        Player player = new Player(new ParticipantName("drago"), new Cards(
            List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.CLOVER, Rank.JACK))));
        Card drawCard = new Card(Suit.HEART, Rank.FOUR);
        List<Card> providedCards = List.of(drawCard);

        Player newPlayer = player.drawCard(providedCards);
        Player expectedPlayer = new Player(new ParticipantName("drago"), new Cards(
            List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.CLOVER, Rank.JACK), drawCard)));
        assertThat(newPlayer).isEqualTo(expectedPlayer);
    }

    @Test
    void 플레이어가_가진_카드리스트의_합계가_21초과이면_true_아니면_false를_반환한다() {
        Player drago = new Player(new ParticipantName("drago"), new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.KING))));

        Player duei = new Player(new ParticipantName("duei"), new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.DIAMOND, Rank.JACK))));

        assertAll(
                () -> assertThat(drago.isBurst()).isTrue(),
                () -> assertThat(duei.isBurst()).isFalse()
        );
    }

    @Test
    void 플레이어의_이름과_카드리스트의_총합을_반환한다() {
        Player player = new Player(new ParticipantName("drago"), new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR))));

        assertThat(player.getTotalRankSum()).isEqualTo(22);
    }
}
