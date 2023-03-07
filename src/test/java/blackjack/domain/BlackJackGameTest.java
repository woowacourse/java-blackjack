package blackjack.domain;

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
        final List<String> playerNames = List.of("pobi", "odo", "jason");
        final BlackJackGame blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), dealerName, playerNames);

        blackJackGame.handOut();

        assertThat(blackJackGame.openCardsByName(dealerName)).hasSize(2);
        for (final String name : playerNames) {
            assertThat(blackJackGame.openCardsByName(name)).hasSize(2);
        }
    }

    @DisplayName("카드 오픈 시 참가자의 보유 카드를 모두 확인하되, 딜러 카드는 첫 카드만 확인한다.")
    @Test
    void should_OpenCards_Of_AllParticipants() {
        final List<String> playerNames = List.of("odo", "doy");
        final BlackJackGame blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), dealerName, playerNames);

        blackJackGame.handOut();

        final Map<String, List<Card>> handOutCards = blackJackGame.openHandOutCards();
        assertThat(handOutCards.get(dealerName)).hasSize(1);
        for (final String name : playerNames) {
            assertThat(handOutCards.get(name)).hasSize(2);
        }
    }
}
