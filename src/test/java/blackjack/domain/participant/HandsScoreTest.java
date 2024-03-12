package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.deck.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsScoreTest {


    @DisplayName("카드패의 점수가 21을 초과했는지 판단한다")
    @Test
    void should_judgeBurst() {
        List<Card> notOverCards = List.of(Card.create(0), Card.create(9));
        HandsScore handsScore = HandsScore.of(notOverCards);

        assertFalse(handsScore.isBurst());

        List<Card> overCards = List.of(Card.create(0), Card.create(13), Card.create(9));
        handsScore = HandsScore.of(overCards);
        assertTrue(handsScore.isBurst());
    }

    @DisplayName("에이스는 21이 초과되지 않으면 11로 사용할 수 있다")
    @Test
    void should_downgradeAce() {
        HandsScore handsScore = HandsScore.of(List.of(Card.create(0), Card.create(9)));

        assertThat(handsScore.getScore()).isEqualTo(21);
    }
}
