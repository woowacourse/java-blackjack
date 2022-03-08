package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardSymbolTest {
    private static final int SYMBOL_LENGTH = 4;

    @DisplayName("CardSymbol 인스턴스가 4개 생성된다.")
    @Test
    void init() {
        int actual = CardSymbol.values().length;

        assertThat(actual).isEqualTo(SYMBOL_LENGTH);
    }

    @DisplayName("getDisplayName 은 카드 심볼의 이름을 반환한다.")
    @Test
    void getDisplayName() {
        List<CardSymbol> symbols = List.of(CardSymbol.values());
        List<String> names = List.of("하트", "스페이드", "다이아몬드", "클로버");

        for (int i = 0; i < SYMBOL_LENGTH; i++) {
            assertThat(symbols.get(i).getDisplayName())
                    .isEqualTo(names.get(i));
        }
    }
}