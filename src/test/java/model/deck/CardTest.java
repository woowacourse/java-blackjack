package model.deck;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    @DisplayName("ace 카드면 소프트밸류로 판단한다.")
    void 에이스_카드면_소프트밸류() {
        //given
        Card card = new Card(CardRank.ACE, CardSuit.DIAMOND);

        //when, then
        assertThat(card.isSoftCard()).isTrue();
    }
}