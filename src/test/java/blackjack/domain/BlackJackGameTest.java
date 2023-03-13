package blackjack.domain;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.FOUR;
import static blackjack.domain.card.Number.THREE;
import static blackjack.domain.card.Number.TWO;
import static blackjack.domain.card.Symbol.DIAMOND;
import static blackjack.domain.card.Symbol.HEART;
import static blackjack.domain.card.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.card.MockDeckGenerator;
import blackjack.domain.participant.Participants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("플레이어의 보유 카드를 모두 확인한다")
    @Test
    void should_OpenCards_Of_AllPlayers() {
        List<String> playerNames = List.of("odo", "doy");
        Participants participants = Participants.of(playerNames);

        Card card1 = new Card(DIAMOND, THREE);
        Card card2 = new Card(DIAMOND, FOUR);
        Card card3 = new Card(HEART, THREE);
        Card card4 = new Card(HEART, FOUR);

        DeckGenerator mockGenerator = new MockDeckGenerator((List.of(
                new Card(SPADE, ACE), new Card(SPADE, TWO),
                card1, card2,
                card3, card4
        )));
        participants.handInitialCards(mockGenerator.generate());

        List<Card> odoCards = participants.getPlayers().get(0).getCards();
        List<Card> doyCards = participants.getPlayers().get(1).getCards();

        assertThat(odoCards)
                .containsExactly(card1, card2);

        assertThat(doyCards)
                .containsExactly(card3, card4);
    }

}
