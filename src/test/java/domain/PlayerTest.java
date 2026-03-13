package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.enums.CardRank;
import domain.enums.CardShape;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        Player player = new Player("요크");
        this.player = player;
    }

    @DisplayName("카드 합계 구하기")
    @Test
    void 카드_합계_정상_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.THREE));
        player.add(new Card(CardShape.SPADE, CardRank.TWO));

        int result = player.getCardsTotalSum();

        assertThat(result).isEqualTo(5);
    }

    @DisplayName("ACE가 11이어야 함")
    @Test
    void 카드_합계_에이스_11_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.TWO));

        int result = player.getCardsTotalSum();
        assertThat(result).isEqualTo(13);
    }

    @DisplayName("ACE가 1이어야 함")
    @Test
    void 카드_합계_에이스_1_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.NINE));
        player.add(new Card(CardShape.SPADE, CardRank.TWO));

        int result = player.getCardsTotalSum();
        assertThat(result).isEqualTo(12);
    }

    @DisplayName("ACE가 하나는 11이고 하나는 1이어야 함")
    @Test
    void 카드_합계_에이스_11_1_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.NINE));

        int result = player.getCardsTotalSum();
        assertThat(result).isEqualTo(21);
    }

    @DisplayName("ACE가 둘 다 1이어야 함")
    @Test
    void 카드_합계_에이스_2개_1_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.TEN));

        int result = player.getCardsTotalSum();
        assertThat(result).isEqualTo(12);
    }

    @DisplayName("Bust 여부 판단 - bust")
    @Test
    void Burst_여부_판단_bust_정상_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.TEN));
        player.add(new Card(CardShape.HEART, CardRank.TWO));
        player.add(new Card(CardShape.HEART, CardRank.TEN));

        boolean result = player.isBust();
        assertThat(result).isEqualTo(true);
    }

    @DisplayName("Bust 여부 판단 - not Bust")
    @Test
    void Burst_여부_판단_not_bust_정상_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.TEN));
        player.add(new Card(CardShape.HEART, CardRank.TWO));

        boolean result = player.isBust();
        assertThat(result).isEqualTo(false);
    }

    @DisplayName("K, Q, J는 10으로 계산")
    @Test
    void KQJ_10으로_계산() {
        Player player1 = createPlayerFromCards(List.of(
                new Card(CardShape.HEART, CardRank.KING),
                new Card(CardShape.HEART, CardRank.QUEEN),
                new Card(CardShape.HEART, CardRank.JACK)
        ));
        assertThat(player1.getCardsTotalSum()).isEqualTo(30);
    }

    private Player createPlayerFromCards(List<Card> cards) {
        Player player = new Player("aaaa");
        for (Card card : cards) {
            player.add(card);
        }
        return player;
    }
}