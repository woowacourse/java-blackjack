package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class AceAdjustPolicyTest {

    @ParameterizedTest
    @EnumSource(value = Rank.class)
    void 버스트가_아닌_경우_ace를_11로_조정한_총_점수를_반환한다(Rank rank) {
        // given
        AceAdjustPolicy aceAdjustPolicy = new AceAdjustPolicy(score -> false);
        List<Card> cards = List.of(
                new Card(Rank.ACE, Suit.CLUB),
                new Card(rank, Suit.CLUB)
        );
        int sum = Rank.ACE.getValue() + rank.getValue();

        // when
        int adjustedScore = aceAdjustPolicy.adjust(sum, cards);

        // then
        assertThat(adjustedScore).isEqualTo(sum + 10);
    }

}
