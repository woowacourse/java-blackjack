package blackjack.domain.card;

import blackjack.domain.game.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.CardConstant.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @Test
    @DisplayName("카드를 추가한다")
    void addCardsTest() {
        // given
        final Hand cards = Hand.from(new ArrayList<>());
        final List<Card> expectedCards = List.of(CLOVER_ACE);

        // when
        cards.add(CLOVER_ACE);

        // that
        assertThat(expectedCards).isEqualTo(cards.getCards());
    }

    @Test
    @DisplayName("카드의 총 합을 반환한다")
    void calculateTotalScoreTest() {
        // given
        final Hand cards = Hand.from(new ArrayList<>());
        final Score expected = Score.from(21);

        // when
        cards.add(CLOVER_ACE);
        cards.add(DIAMOND_JACK);

        // that
        assertThat(cards.calculateScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("패를 반환한다")
    void getCardsTest() {
        // given
        final Hand cards = Hand.from(new ArrayList<>());

        // when
        cards.add(CLOVER_ACE);
        cards.add(DIAMOND_JACK);

        // that
        assertThat(cards.getCards()).contains(CLOVER_ACE, DIAMOND_JACK);
    }

    @Test
    @DisplayName("에이스 2장 일때 합 12가 되는지 테스트")
    void calculateTotalOver21Test() {
        // given
        final Hand cards = Hand.from(new ArrayList<>());
        final Score expected = Score.from(12);

        // when
        cards.add(CLOVER_ACE);
        cards.add(DIAMOND_ACE);

        // that
        assertThat(cards.calculateScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("에이스 2장에 기본 19 일때 합 21가 되는지 테스트")
    void calculateTotalOver21Test2() {
        // given
        final Hand cards = Hand.from(new ArrayList<>());
        final Score expected = Score.from(21);

        // when
        cards.add(CLOVER_ACE);
        cards.add(SPACE_ACE);
        cards.add(DIAMOND_JACK);
        cards.add(DIAMOND_NINE);

        // that
        assertThat(cards.calculateScore()).isEqualTo(expected);
    }
}
