package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Number.FOUR;
import static blackjack.domain.Number.THREE;
import static blackjack.domain.Number.TWO;
import static blackjack.domain.Symbol.DIAMOND;
import static blackjack.domain.Symbol.HEART;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("플레이어의 보유 카드를 모두 확인한다")
    @Test
    void should_OpenCards_Of_AllPlayers() {
        List<String> playerNames = List.of("odo", "doy");
        Participants participants = Participants.of(playerNames);

        DeckGenerator mockGenerator = new MockDeckGenerator((List.of(
                new Card(SPADE, ACE), new Card(SPADE, TWO),
                new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR),
                new Card(HEART, THREE), new Card(HEART, FOUR)
        )));
        participants.handInitialCards(mockGenerator.generate());

        List<Card> odoCards = participants.getPlayers().get(0).getCards();
        List<Card> doyCards = participants.getPlayers().get(1).getCards();

        assertThat(odoCards)
                .containsExactly(new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR));

        assertThat(doyCards)
                .containsExactly(new Card(HEART, THREE), new Card(HEART, FOUR));
    }

}
