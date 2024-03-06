package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("이름과 카드 일급 컬렉션을 통해서 딜러를 생성 한다.")
    public void Dealer_Instance_create_with_name_and_cards() {
        Name name = new Name("딜러");
        Cards cards = new Cards(List.of(CardValue.EIGHT, CardValue.FOUR));

        assertThatCode(() -> new Dealer(name, cards)).doesNotThrowAnyException();
    }
}
