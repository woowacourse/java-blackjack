package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class CardTest {

    @Test
    @DisplayName("카드 생성")
    void createCard() {

        assertThatCode(() -> new Card(Suit.DIAMOND, Denomination.ACE))
            .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("카드의 포인트 얻기")
    void getCardPoint() {
        Card card = new Card(Suit.CLOVER, Denomination.JACK);

        assertThat(card.getPoint()).isEqualTo(10);
    }

    @Test
    void example() {
        String value = ",,,,,";
        List<String> list = Arrays.asList(value.trim().split(","));
        System.out.print("사이즈" + list.size());
        System.out.println(list);
    }

}
