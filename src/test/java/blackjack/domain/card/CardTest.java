package blackjack.domain.card;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 객체값을_검증한다() {
        // given
        Card card = new Card(CardSuit.HEART, CardRank.EIGHT);

        // when
        List<Integer> actual = card.getRankValues();

        // then
        assertThat(actual).contains(8);
    }

    @Test
    void ACE는_1과_11의_값을_가질_수_있다() {
        // given
        Card ace = new Card(CardSuit.CLUB, CardRank.ACE);

        // when
        List<Integer> actual = ace.getRankValues();

        // then
        assertThat(actual).containsExactlyInAnyOrder(1, 11);
    }

    @Test
    void 값과_타입으로_동일한_객체인지_확인한다() {
        // given
        Card card = new Card(CardSuit.HEART, CardRank.TWO);
        Card comparedCard = new Card(CardSuit.HEART, CardRank.TWO);

        // when
        boolean actual = card.equals(comparedCard);

        // then
        assertThat(actual).isTrue();
    }
}
