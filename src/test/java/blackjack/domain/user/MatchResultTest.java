package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchResultTest {

    @DisplayName("MatchResult 반대 이름 가져오기 테스트")
    @Test
    void getReverseName() {
        assertAll(
            () -> assertEquals(MatchResult.WIN.getReverseName(), "패"),
            () -> assertEquals(MatchResult.LOSE.getReverseName(), "승"),
            () -> assertEquals(MatchResult.DRAW.getReverseName(), "무")
        );
    }
}