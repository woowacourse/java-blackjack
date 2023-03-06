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
            blackjackGame.setParticipants(List.of("포이", "에밀"));
            blackjackGame.handOutInitialCards((cards) -> {
                cards.clear();
                cards.add(Card.of(Suit.SPADE, Number.JACK));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
                cards.add(Card.of(Suit.SPADE, Number.SEVEN));
                cards.add(Card.of(Suit.SPADE, Number.NINE));
                cards.add(Card.of(Suit.SPADE, Number.EIGHT));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
            });

            //when
            Map<String, GameOutcome> outcome = blackjackGame.getPlayersOutcome();

            //then
            assertAll(
                    () -> assertThat(outcome.get("포이")).isEqualTo(WIN),
                    () -> assertThat(outcome.get("에밀")).isEqualTo(LOSE)
            );
        }
    }
}