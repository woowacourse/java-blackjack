package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardRankTest {
    private static final int RANK_LENGTH = 13;

    @DisplayName("CardRank 인스턴스가 13개 생성된다.")
    @Test
    void init() {
        int actual = CardRank.values().length;

        assertThat(actual).isEqualTo(RANK_LENGTH);
    }

    @DisplayName("getDisplayName 은 카드 랭크의 이름을 반환한다.")
    @Test
    void getDisplayName() {
        List<CardRank> ranks = List.of(CardRank.values());
        List<String> names = List.of("A", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "J", "Q", "K");

        for (int i = 0; i < RANK_LENGTH; i++) {
            assertThat(ranks.get(i).getDisplayName())
                    .isEqualTo(names.get(i));
        }
    }
}
