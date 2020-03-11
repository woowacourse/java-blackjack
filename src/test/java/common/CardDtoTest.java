package common;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardDtoTest {
    private CardDto cardDto;

    @BeforeEach
    void setUp() {
        cardDto = CardDto.of(new Card(Symbol.QUEEN, Type.CLOVER));
    }

    @Test
    void of() {
        assertThat(CardDto.of(new Card(Symbol.QUEEN, Type.CLOVER))).isNotNull();
    }

    @Test
    void getValue() {
        assertThat(cardDto.getValue()).isEqualTo(10);
    }

    @Test
    void getWord() {
        assertThat(cardDto.getWord()).isEqualTo("Q");
    }

    @Test
    void getPattern() {
        assertThat(cardDto.getPattern()).isEqualTo("♧");
    }
}