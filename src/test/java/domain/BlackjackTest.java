package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlackjackTest {

    @Test
    void 블랙잭_객체를_생성한다() {
        // given
        Players players = new Players(List.of(
                new Dealer(),
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();

        // when & then
        Assertions.assertThatCode(() -> new Blackjack(players, deck))
                .doesNotThrowAnyException();
    }
}
