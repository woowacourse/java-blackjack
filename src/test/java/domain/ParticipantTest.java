package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("초기 카드는 2장이어야 한다.")
    void validateCardsSize() {
        Cards cards = new Cards(List.of(
            new Card(Suit.CLOVER, Number.FIVE),
            new Card(Suit.CLOVER, Number.SIX),
            new Card(Suit.CLOVER, Number.A)));

        assertThatThrownBy(() -> new Participant("roy", cards) {
            @Override
            public boolean canAddCard() {
                return true;
            }
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("초기 카드는 2장이어야 합니다.");
    }
}
