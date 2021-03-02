package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class HandsTest {

    @DisplayName("Cards 객체 생성")
    @Test
    void create() {
        List<Card> cards = new ArrayList<>();
        assertThatCode(() -> new Hands(cards)).doesNotThrowAnyException();
    }
}
