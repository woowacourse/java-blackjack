package blackjack.domain;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackJackGameTest {

    private final static String dealerName = "딜러";

    @DisplayName("모든 참가자에게 카드를 2장씩 나눠준다.")
    @Test
    void should_AllParticipantsHas2Cards_When_HandOut() {
        List<String> playerNames = List.of("pobi", "odo", "jason");
        BlackJackGame blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), dealerName, playerNames);

        blackJackGame.handOut();
        
        assertThat(blackJackGame.openCardsByName(dealerName)).hasSize(2);
        for (String name : playerNames) {
            assertThat(blackJackGame.openCardsByName(name)).hasSize(2);
        }
    }

    @DisplayName("카드 오픈 시 참가자의 보유 카드를 모두 확인하되, 딜러 카드는 첫 카드만 확인한다.")
    @Test
    void should_OpenCards_Of_AllParticipants() {
        DeckGenerator mockGenerator = new MockDeckGenerator((List.of(
                new Card(SPADE, ACE), new Card(SPADE, TWO),
                new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR),
                new Card(HEART, THREE), new Card(HEART, FOUR)
        )));
        List<String> playerNames = List.of("odo", "doy");
        BlackJackGame blackJackGame = new BlackJackGame(mockGenerator, dealerName, playerNames);

        blackJackGame.handOut();

        Map<String, List<Card>> handOutCards = blackJackGame.openHandOutCards();
        assertThat(handOutCards.get(dealerName)).hasSize(1);
        for (String name : playerNames) {
            assertThat(handOutCards.get(name)).hasSize(2);
        }
    }
}
