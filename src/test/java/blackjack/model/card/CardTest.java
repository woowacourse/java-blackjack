package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("Score가 11인 Ace 카드라면 True를 반환한다.")
    @Test
    void isElevenAce() {
        //given
        Card card = new Card(new CardProperties(CardPattern.CLOVER, CardNumber.ACE));

        //when, then
        assertThat(card.isElevenAce()).isTrue();
    }

    @DisplayName("Score가 11인 Ace 카드라면 Score를 1로 변경한다.")
    @Test
    void switchAceValue() {
        //given
        Card card = new Card(new CardProperties(CardPattern.CLOVER, CardNumber.ACE));

        //when
        int unmodifiedScore = card.getScore();

        card.switchAceValue();
        int modifiedScore = card.getScore();

        //when, then
        assertThat(unmodifiedScore).isEqualTo(11);
        assertThat(modifiedScore).isEqualTo(1);
    }
}
