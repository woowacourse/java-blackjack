package blackjack.domain;


import static blackjack.fixture.TestFixture.provideOver21Cards;
import static blackjack.fixture.TestFixture.provideParticipants;
import static blackjack.fixture.TestFixture.providePlayersWithCards;
import static blackjack.fixture.TestFixture.provideUnder21Cards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.CardManager;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("플레이어 및 딜러에게 카드를 두 장씩 나눠준다.")
    @Test
    void spreadTwoCardsToDealerAndPlayers() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                provideParticipants());

        // when & then
        Assertions.assertThatCode(blackjackGame::spreadInitialCards)
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어가 카드를 받을 수 있는지 물어본다.")
    @Test
    void canSpreadToPlayer() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(new Dealer(new ArrayList<>()),
                        new Players(providePlayersWithCards(provideUnder21Cards(), provideOver21Cards()))));

        // when & then
        assertAll(
                () -> assertThat(blackjackGame.canSpread(0)).isTrue(),
                () -> assertThat(blackjackGame.canSpread(1)).isFalse()
        );
    }

    @DisplayName("한 장의 카드를 플레이어에게 준다.")
    @Test
    void spreadOneCard() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                provideParticipants());

        // when
        blackjackGame.spreadOneCard(0);

        // then
        assertThat(blackjackGame.getPlayer(0).getCards()).hasSize(1);

    }
}