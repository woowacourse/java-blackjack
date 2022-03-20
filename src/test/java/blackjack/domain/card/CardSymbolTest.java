package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardSymbolTest {

    private static final int SYMBOL_LENGTH = 4;

    @DisplayName("애플리케이션 실행 시점에 CardSymbol 인스턴스가 4개 생성된다.")
    @Test
    void init() {
        int actual = CardSymbol.values().length;

        assertThat(actual).isEqualTo(SYMBOL_LENGTH);
    }

    @DisplayName("카드 심볼은 각자 대응되는 이름을 지닌다.")
    @Test
    void displayName() {
        List<String> actual = Arrays.stream(CardSymbol.values())
                .map(CardSymbol::getDisplayName)
                .collect(Collectors.toList());

        List<String> expected = List.of("♥️", "♣️", "♦️", "♠️");

        assertThat(actual).containsAll(expected);
    }
}