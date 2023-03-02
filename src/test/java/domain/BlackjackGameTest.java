package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    @Nested
    class 첫카드분배 {
        @Test
        void should_딜러는카드두장을지급받는다_when_처음카드분배가끝나면() {
            //given
            Players players = Players.from(List.of("에밀", "포이"));
            BlackjackGame blackjackGame = new BlackjackGame(players);

            //when
            blackjackGame.initialize(Deck.create(), (list) -> {});

            //then
            assertThat(blackjackGame).extracting("dealer")
                    .extracting("hand")
                    .extracting("cards", collection(Card.class))
                    .hasSize(2)
                    .containsSequence(new Card(Suit.CLUB, Number.QUEEN), new Card(Suit.CLUB, Number.JACK));
        }
    }

}