package blackjack.domain.card;

import static blackjack.Fixtures.SPADE_ACE;
import static blackjack.Fixtures.SPADE_EIGHT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("카드 객체 생성을 테스트한다.")
    @Test
    public void testCreateCard() {
        //given & when
        Card card = SPADE_ACE;

        //then
        assertThat(card).isNotNull();
    }

    @DisplayName("카드의 심볼을 얻을 수 있다.")
    @Test
    public void testGetCardName() {
        //given & when
        Card card = SPADE_EIGHT;

        //then
        assertThat(card.getSymbol()).isEqualTo("8");
    }

    @DisplayName("카드의 번호를 얻을 수 있다.")
    @Test
    public void testGetCardWithSymbol() {
        //given & when
        Card card = SPADE_EIGHT;

        //then
        assertThat(card.getSuitName()).isEqualTo("스페이드");
    }
}
