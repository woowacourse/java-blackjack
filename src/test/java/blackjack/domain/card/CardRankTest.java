package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.game.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CardRankTest {

    private static final int RANK_LENGTH = 13;

    @DisplayName("애플리케이션 실행 시점에 CardRank 인스턴스가 13개 생성된다.")
    @Test
    void init() {
        int actual = CardRank.values().length;

        assertThat(actual).isEqualTo(RANK_LENGTH);
    }

    @DisplayName("getDisplayName 메서드는 카드 랭크의 이름을 반환한다.")
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

    @DisplayName("ACE는 기본값으로 1의 Score를 지닌다.")
    @Test
    void getValue_Ace() {
        Score actual = CardRank.ACE.getValue();
        Score expected = Score.valueOf(1);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("TWO부터 TEN까지는 각각 대응되는 값을 Score로 지닌다.")
    @ParameterizedTest(name = "[{index}] 랭크: {0}, 값: {1}")
    @CsvSource(value = {"TWO,2", "THREE,3", "FOUR,4", "FIVE,5", "SIX,6", "SEVEN,7", "EIGHT,8", "NINE,9", "TEN,10"})
    void getValue_TwoToTen(String rankName, int value) {
        CardRank numberCard = CardRank.valueOf(rankName);

        Score actual = numberCard.getValue();
        Score expected = Score.valueOf(value);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("JACK, QUEEN, KING은 값으로 10의 Score를 지닌다.")
    @ParameterizedTest(name = "[{index}] 랭크: {0}, 값: 10")
    @ValueSource(strings = {"JACK", "QUEEN", "KING"})
    void getValue_JackQueenKing(String rankName) {
        CardRank numberCard = CardRank.valueOf(rankName);

        Score actual = numberCard.getValue();
        Score expected = Score.valueOf(10);

        assertThat(actual).isEqualTo(expected);
    }
}
