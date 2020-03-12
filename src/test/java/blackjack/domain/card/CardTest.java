package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @ParameterizedTest
    @DisplayName("카드가 에이스인지 판별하는 기능 테스트")
    @CsvSource(value = {
            "DIAMOND,ACE,true",
            "SPADE,ACE,true",
            "CLUB,ACE,true",
            "HEART,ACE,true",
            "DIAMOND,FIVE,false"
    })
    void name(Suit suit, Symbol symbol, boolean expected) {
        assertThat(new Card(suit, symbol).isAce()).isEqualTo(expected);
    }
}
