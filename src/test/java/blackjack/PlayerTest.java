package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("이름과 카드 일급 컬렉션을 통해서 플레이어를 생성 한다.")
    public void Player_Instance_create_with_name_and_cards() {
        Name name = new Name("초롱");
        Cards cards = new Cards(List.of(Card.EIGHT, Card.FOUR));

        assertThatCode(() -> new Player(name, cards)).doesNotThrowAnyException();

    }
}
