package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드 패턴과 숫자로 카드를 생성한다.")
    @Test
    void createCard() {
        //given
        CardPattern cardPattern = CardPattern.DIAMOND;
        CardNumber cardNumber = CardNumber.FIVE;

        //when
        Card card = new Card(cardPattern, cardNumber);

        //then
        assertAll(
                () -> assertThat(card.getPattern()).isEqualTo(cardPattern),
                () -> assertThat(card.getNumber()).isEqualTo(cardNumber)
        );
    }

    @DisplayName("Score가 11인 Ace 카드라면 True를 반환한다.")
    @Test
    void isElevenAce() {
        //given
        Card card = new Card(CardPattern.CLOVER, CardNumber.ACE);

        //when, then
        assertThat(card.isElevenAce()).isTrue();
    }

    @DisplayName("Score가 11인 Ace 카드라면 Score를 1로 변경한다.")
    @Test
    void switchAceValue() {
        //given
        Card card = new Card(CardPattern.CLOVER, CardNumber.ACE);

        //when
        int unmodifiedScore = card.getScore();

        card.switchAceValue();
        int modifiedScore = card.getScore();

        //then
        assertAll(
                () -> assertThat(unmodifiedScore).isEqualTo(11),
                () -> assertThat(modifiedScore).isEqualTo(1)
        );
    }
}
