package domain;

import domain.card.Card;
import domain.card.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static domain.card.Rank.ACE;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    void ACE인지_확인한다() {
        Card card = new Card(ACE, CLOVER);

        boolean ace = card.isAce();

        assertThat(ace).isEqualTo(true);
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"JACK", "QUEEN", "KING"})
    void JQK는_10점으로_처리한다(Rank rank) {
        Card card = new Card(rank, CLOVER);

        int score = card.score();

        assertThat(score).isEqualTo(10);
    }

    @Test
    void ACE는_기본_11점이다() {
        Card card = new Card(ACE, HEART);

        int score = card.score();

        assertThat(score).isEqualTo(11);
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"JACK", "QUEEN", "KING", "ACE"}, mode = EnumSource.Mode.EXCLUDE)
    void 숫자2_부터_10_사이의_숫자는_그대로_반환한다(Rank rank) {
        Card card = new Card(rank, CLOVER);
        int answer = rank.getValue();

        int score = card.score();

        assertThat(score).isEqualTo(answer);
    }
}
