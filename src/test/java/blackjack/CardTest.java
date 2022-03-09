package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource({"8다이아몬드,8", "J하트,10", "A클로버,1", "K스페이스,10", "Q하트,10", "5다이아몬드,5", "4하트,4"})
    @DisplayName("카드 rank 정보 반환 테스트")
    void get_rank(String input, int expect) {
        Card card = new Card(input);
        assertThat(card.getRank()).isEqualTo(expect);
    }
}
