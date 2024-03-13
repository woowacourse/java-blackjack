package blackjack.model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardTest {

    @DisplayName("생성한 카드의 패턴과 점수가 조회 가능하다.")
    @Test
    void createCard() {
        //given
        Card card = new Card(Pattern.CLOVER, Rank.ACE);

        //when
        Pattern pattern = card.getCardPattern();
        int score = card.getCardScore();

        //then
        assertAll(
                () -> assertThat(pattern).isEqualTo(Pattern.CLOVER),
                () -> assertThat(score).isEqualTo(1)
        );
    }
}
