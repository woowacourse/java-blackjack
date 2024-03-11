package domain;

import static org.assertj.core.api.Assertions.assertThat;

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
        Hand twoCards = createNormalWithTwoCards();

        Player player = new Player(twoCards);

        int totalSize = player.getHandSize();
        assertThat(totalSize).isEqualTo(2);
    }


    @DisplayName("카드의 총 점수를 계산한다.")
    @Nested
    class CalculateScore {
        @DisplayName("에이스 카드가 없는 경우 단순 합산한다.")
        @Test
        void calculateScoreWithNoAce() {
            Hand twoCards = createNormalWithTwoCards();

            Player player = new Player(twoCards);

            int totalScore = player.calculateResultScore();
            assertThat(totalScore).isEqualTo(16);
        }

        @DisplayName("에이스 카드가 11로 계산되었을 때 21을 초과하면 21미만이 될 때까지 하나씩 1로 계산한다.")
        @Test
        void calculateScoreWithAceIfBusted() {
            Hand cardsWithAce = createCardsWithAce();

            Player player = new Player(cardsWithAce);

            int totalScore = player.calculateResultScore();
            assertThat(totalScore).isEqualTo(12);
        }
    }

    @DisplayName("라운드 진행 중에는 에이스 카드는 즉시 1로 계산한다.")
    @Test
    void drawAceCardAndCalculateScoreOne() {
        Hand cardsWithAce = createCardsWithAce();

        Player player = new Player(cardsWithAce);

        int totalScore = player.calculateRoundScore();
        assertThat(totalScore).isEqualTo(12);
    }

    @DisplayName("카드를 더 받을 수 있는지 확인한다.")
    @Test
    void isAbleToDrawCard() {
        Hand bustedCards = createBustedCards();

        Player player = new Player(bustedCards);

        boolean ableToDrawCard = player.isAbleToDrawCard();
        assertThat(ableToDrawCard).isFalse();
    }

    private Hand createNormalWithTwoCards() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND)
        )));
        return hand;
    }

    private Hand createCardsWithAce() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.EIGHT, Shape.CLOVER),
                new Card(Score.THREE, Shape.DIAMOND),
                new Card(Score.ACE, Shape.HEART)
        )));
        return hand;
    }

    private Hand createBustedCards() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.EIGHT, Shape.HEART)
        )));
        return hand;
    }
}
