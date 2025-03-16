package model.hand;

import static org.assertj.core.api.Assertions.assertThat;

import model.deck.Card;
import model.deck.CardRank;
import model.deck.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HardHandTest {
    @Test
    @DisplayName("최종 점수는 카드들의 디폴트 밸류의 합으로 구한다.")
    void 최종점수는_카드들의_디폴트_밸류의_합() {
        //given
        HardHand hardHand = new HardHand();
        hardHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));
        hardHand.add(new Card(CardRank.THREE, CardSuit.DIAMOND));

        //when
        int score = hardHand.calculateFinalScore();

        //then
        assertThat(score).isEqualTo(5);
    }
}