package blackjack.domain;

import static blackjack.domain.ParticipantFixtures.BETTING_MONEY_1000;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackJackGameTest {

    private static final Player PLAYER_POBI = new Player("pobi", BETTING_MONEY_1000);
    private static final Player PLAYER_LISA = new Player("lisa", BETTING_MONEY_1000);
    private static final Player PLAYER_JASON = new Player("jason", BETTING_MONEY_1000);

    private static final Dealer DEALER = new Dealer("딜러");

    @DisplayName("모든 참가자에게 카드를 2장씩 나눠준다.")
    @Test
    void should_AllParticipantsHas2Cards_When_HandOut() {
        final List<Player> players = List.of(PLAYER_POBI, PLAYER_LISA, PLAYER_JASON);
        final BlackJackGame blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), DEALER, players);

        blackJackGame.handOut();

        assertThat(DEALER.cards()).hasSize(2);
        for (final Player player : players) {
            assertThat(player.cards()).hasSize(2);
        }
    }
}
