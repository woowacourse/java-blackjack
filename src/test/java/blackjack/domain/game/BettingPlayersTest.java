package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;
import blackjack.fixture.ParticipantCardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingPlayersTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        final Deck deck = new Deck();
        final ParticipantCards participantsCards1 = ParticipantCardsFixture.create(deck.draw(), deck.draw(), List.of());
        final ParticipantCards participantsCards2 = ParticipantCardsFixture.create(deck.draw(), deck.draw(), List.of());
        final ParticipantCards participantsCards3 = ParticipantCardsFixture.create(deck.draw(), deck.draw(), List.of());
        final Player player1 = new Player(participantsCards1, "헤나01");
        final Player player2 = new Player(participantsCards2, "헤나02");
        final Player player3 = new Player(participantsCards3, "헤나03");
        final List<Player> players = List.of(player1, player2, player3);
        final List<Money> moneys = List.of(Money.ZERO, Money.ZERO, Money.ZERO);

        assertThatNoException().isThrownBy(() -> new BettingPlayers(players, moneys));
    }

    @Test
    @DisplayName("플레이어 목록이 비어있을 경우 예외가 발생한다.")
    void throwExceptionWhenPlayersIsEmpty() {
        assertThatThrownBy(() -> new BettingPlayers(Collections.emptyList(), List.of(Money.ZERO)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("돈 목록이 비어있을 경우 예외가 발생한다.")
    void throwExceptionWhenMoneysIsEmpty() {
        final Deck deck = new Deck();
        final ParticipantCards participantsCards = ParticipantCardsFixture.create(deck.draw(), deck.draw(), List.of());
        final Player player = new Player(participantsCards, "헤나");

        assertThatThrownBy(() -> new BettingPlayers(List.of(player), Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
