package blackjack.domain;

import blackjack.domain.generator.NumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    class SelectedNumberGenerator implements NumberGenerator {

        @Override
        public int generate(int maxRange) {
            return maxRange;
        }
    }

    @DisplayName("인덱스를 통해 생성된 Rank와 Suit로 Card를 생성한다.")
    @Test
    void createCardGivenRankAndSuit() {
        // given
        SelectedNumberGenerator numberGenerator = new SelectedNumberGenerator();
        int indexOfRank = numberGenerator.generate(12);
        int indexOfSuit = numberGenerator.generate(3);

        Rank rank = Rank.getValue(indexOfRank);
        Suit suit = Suit.getValue(indexOfSuit);

        // when & then
        assertDoesNotThrow(() -> new Card(rank, suit));
    }
}
