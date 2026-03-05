package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private final List<Card> cards = List.of(
            Card.of(Rank.THREE, Suit.DIAMOND),
            Card.of(Rank.NINE, Suit.CLOVER),
            Card.of(Rank.EIGHT, Suit.DIAMOND)
    );

    @Test
    @DisplayName("플레이어의 카드 점수 합계 정상 테스트")
    void 플레이어_카드_점수_합계_정상_테스트() {
        int cardScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        int expect = 20;
        assertThat(cardScore).isEqualTo(expect);
    }

}