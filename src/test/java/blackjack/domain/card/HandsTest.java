package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class HandsTest {

    @DisplayName("Cards 객체 생성")
    @Test
    void create() {
        List<Card> cards = new ArrayList<>();
        assertThatCode(() -> new Hands(cards)).doesNotThrowAnyException();
    }

    @DisplayName("Cards에 Card 객체 추가")
    @Test
    void add() {
        List<Card> cards = new ArrayList<>();
        Hands hands = new Hands(cards);
        hands.add(Card.create(CardSymbol.CLUB, CardValue.KING));

        assertThat(hands.toList().size()).isEqualTo(1);
    }
}
