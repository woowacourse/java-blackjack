import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Deck;
import domain.Player;
import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("카드를 저장한다.")
    @Test
    void saveCard() {
        Deck twoCards = createNormalWithTwoCards();

        Player player = new Player("pobi");
        for (int i = 0; i < 2; i++) {
            player.pickOneCard(twoCards);
        }

        int totalSize = player.getHandSize();
        assertThat(totalSize).isEqualTo(2);
    }


    @DisplayName("카드의 총 점수를 계산한다.")
    @Nested
    class calculateScore {
        @DisplayName("에이스 카드가 없는 경우 단순 합산한다.")
        @Test
        void calculateScoreWithNoAce() {
            Deck twoCards = createNormalWithTwoCards();

            Player player = new Player("pobi");
            for (int i = 0; i < 2; i++) {
                player.pickOneCard(twoCards);
            }
            int totalScore = player.calculateResultScore();
            assertThat(totalScore).isEqualTo(16);
        }

        @DisplayName("에이스 카드가 11로 계산되었을 때 21을 초과하면 21미만이 될 때까지 하나씩 1로 계산한다.")
        @Test
        void calculateScoreWithAceIfBusted() {
            Deck cardsWithAce = createCardsWithAce();

            Player player = new Player("pobi");
            for (int i = 0; i < 3; i++) {
                player.pickOneCard(cardsWithAce);
            }

            int totalScore = player.calculateResultScore();
            assertThat(totalScore).isEqualTo(12);
        }
    }

    @DisplayName("라운드 진행 중에는 에이스 카드는 즉시 1로 계산한다.")
    @Test
    void drawAceCardAndCalculateScoreOne() {
        Deck cardsWithAce = createCardsWithAce();

        Player player = new Player("pobi");
        for (int i = 0; i < 3; i++) {
            player.pickOneCard(cardsWithAce);
        }

        int totalScore = player.calculateRoundScore();
        assertThat(totalScore).isEqualTo(12);
    }

    @DisplayName("카드를 더 받을 수 있는지 확인한다.")
    @Test
    void isAbleToDrawCard() {
        Deck bustedCards = createBustedCards();

        Player player = new Player("pobi");
        for (int i = 0; i < 3; i++) {
            player.pickOneCard(bustedCards);
        }

        boolean ableToDrawCard = player.isAbleToDrawCard();
        assertThat(ableToDrawCard).isFalse();
    }

    private Deck createNormalWithTwoCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND)
        )));
    }

    private Deck createCardsWithAce() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.EIGHT, Shape.CLOVER),
                new Card(Score.THREE, Shape.DIAMOND),
                new Card(Score.ACE, Shape.HEART)
        )));
    }

    private Deck createBustedCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.EIGHT, Shape.HEART)
        )));
    }
}
