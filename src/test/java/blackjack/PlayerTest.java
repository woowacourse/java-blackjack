package blackjack;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름이 null이면 예외가 발생한다")
    @Test
    void from_exception_null() {
        final TrumpCard card1 = new TrumpCard(TrumpNumber.EIGHT, TrumpSymbol.HEART);
        final TrumpCard card2 = new TrumpCard(TrumpNumber.TEN, TrumpSymbol.CLOVER);

        assertThatThrownBy(() -> {
            new Entry.Builder(null)
                    .deck(card1, card2)
                    .build();
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력된 이름이 없습니다.");
    }
}
