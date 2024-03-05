package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardMakerTest {
    @Test
    @DisplayName("게임 시작 시 인원 당 받는 덱의 수는 2장이다.")
    void gameCreateTest() {
        // given
        CardMaker cardMaker = new CardMaker();

        // when
        List<Card> cards = cardMaker.make();

        // then
        assertThat(cards.size()).isEqualTo(2);
    }
}
