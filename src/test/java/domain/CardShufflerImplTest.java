package domain;

import static org.assertj.core.api.Assertions.assertThat;

import infra.CardShufflerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardShufflerImplTest {

    private final CardShuffler cardShuffler = new CardShufflerImpl();

    @Test
    @DisplayName("0부터 size-1까지의 수 중 하나를 반환한다.")
    public void 랜덤_수_반환_성공() {
        // given
        int size = 52;

        // when
        int cardIndex = cardShuffler.getRandomCardIndex(size);

        // then
        assertThat(cardIndex).isBetween(0, size - 1);
    }

}