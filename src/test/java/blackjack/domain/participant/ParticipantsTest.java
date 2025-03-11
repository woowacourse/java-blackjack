package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideCards;
import static blackjack.fixture.TestFixture.provideOver21Cards;
import static blackjack.fixture.TestFixture.provideParticipants;
import static blackjack.fixture.TestFixture.provideTwoPlayersWithCards;
import static blackjack.fixture.TestFixture.provideUnder21Cards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private Participants participants;

    @BeforeEach
    void setUp() {
        participants = provideParticipants();
    }

    @DisplayName("모든 참가자에게 카드 두장씩 분배한다.")
    @Test
    void test() {
        // given
        Cards cards = provideCards(6);
        final Cards totalCards = provideCards(6);
        final Cards dealerCards = totalCards.getPartialCards(0, 2);
        final Cards playerCard1 = totalCards.getPartialCards(2, 4);
        final Cards playerCard2 = totalCards.getPartialCards(4, 6);

        // when
        participants.spreadAllTwoCards(cards);

        // then
        assertAll(
                () -> assertThat(participants.getDealer()).isEqualTo(new Dealer(dealerCards)),
                () -> assertThat(participants.getPlayers()).isEqualTo(
                        provideTwoPlayersWithCards(playerCard1, playerCard2))
        );
    }

    @DisplayName("초기에 나눠주는 카드 수를 계산한다.")
    @Test
    void calculateParticipantsSize() {
        // given

        // when & then
        assertThat(participants.getInitialTotalCardsSize()).isEqualTo(6);
    }

    @DisplayName("플레이어가 카드를 더 얻을 수 있으면 true를 반환한다.")
    @Test
    void canPlayerGetMoreCard() {
        // given
        final Cards firstCards = provideUnder21Cards();
        final Cards secondCards = provideOver21Cards();
        final List<Player> players = provideTwoPlayersWithCards(firstCards, secondCards);

        // when & then
        assertAll(
                () -> assertThat(players.getFirst().canGetMoreCard()).isTrue(),
                () -> assertThat(players.getLast().canGetMoreCard()).isFalse()
        );
    }
}
