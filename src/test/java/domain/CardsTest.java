package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardsTest {

    @Test
    @DisplayName("처음 지급받는 카드의 수가 두 장이 아니라면 예외가 발생한다.")
    void player_OverSize_ExceptionThrown() {
        assertThatThrownBy(() -> new Cards(List.of(new Card(1), new Card(2), new Card(3))))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
