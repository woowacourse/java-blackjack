package domain;

import static domain.GameOutcome.LOSE;
import static domain.GameOutcome.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Nested
    class 결과반환 {
        @Test
        void should_승패를판단하여반환한다_when_getPlayerOutcome호출시() {
            //given
            BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.initialize(List.of("포이", "에밀"), (cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.JACK));
                cards.add(new Card(Suit.SPADE, Number.TEN));
                cards.add(new Card(Suit.SPADE, Number.SEVEN));
                cards.add(new Card(Suit.SPADE, Number.NINE));
                cards.add(new Card(Suit.SPADE, Number.EIGHT));
                cards.add(new Card(Suit.SPADE, Number.TEN));
            });

            //when
            Map<String, GameOutcome> outcome = blackjackGame.getPlayersOutcome();

            //then
            assertAll(
                    () -> assertThat(outcome.get("포이")).isEqualTo(LOSE),
                    () -> assertThat(outcome.get("에밀")).isEqualTo(WIN)
            );
        }
    }
}