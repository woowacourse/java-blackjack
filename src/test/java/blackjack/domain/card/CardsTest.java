package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void 플레이어는_자신이_가진_카드로_21에_최대한_가깝게_점수를_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.ACE)));

        //when
        Score maxScore = cards.calculateMaxScore();

        //then
        assertThat(maxScore).isEqualTo(new Score(20));
    }

    @Test
    void 카드가_블랙잭임을_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.KING)));

        //when & then
        assertThat(cards.isBlackjack()).isTrue();
    }

    @Test
    void 카드를_받을_수_있다() {
        //given
        Cards cards = new Cards(
                new ArrayList<>());

        //when
        cards.take(new Card(Suit.CLUB, Rank.TEN));

        //then
        assertThat(cards).isEqualTo(
                new Cards(
                        List.of(new Card(Suit.CLUB, Rank.TEN)))
        );
    }

    @Test
    void 카드를_받을뗴_버스트가_됐다면_예외가_발생한다() {
        //given
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.DIAMOND, Rank.TWO)));

        //when & then
        assertThatThrownBy(() -> cards.take(new Card(Suit.CLUB, Rank.TEN)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
    }

    @Test
    void 모든_카드의_최소_합이_21을_초과한다면_BUST이다() {
        //given
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.DIAMOND, Rank.TWO)));

        //when
        boolean result = cards.isBust();

        //then
        assertThat(result).isTrue();
    }

    @Test
    void 모든_카드의_최소_합이_21이하라면_BUST가_아니다() {
        //given
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.DIAMOND, Rank.ONE)));

        //when
        boolean result = cards.isBust();

        //then
        assertThat(result).isFalse();
    }
}
