package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.fixture.PlayersFixture;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("초기 분배 시 플레이어에게 두 장의 카드가 분배된다.")
    void dealInitialCardsSuccessfully() {
        // given
        Players players = PlayersFixture.createValidSinglePlayer();
        Dealer dealer = Dealer.from();
        Participants participants = new Participants(players, dealer);
        Deck deck = Deck.createShuffledDeck();

        // when
        participants.dealInitialCards(deck);
        Player firstPlayer = players.getAllPlayers().getFirst();
        List<String> playerCards = Arrays.stream(firstPlayer.getCardStatus().split(",")).toList();

        // then
        assertThat(playerCards).hasSize(2);
    }

    @Test
    @DisplayName("덱에 남은 카드가 부족할 때 초기 분배를 시도하면 예외가 발생한다.")
    void dealInitialCardsThrowsExceptionOnShortDeck() {
        // given
        Players players = PlayersFixture.createValidTwoPlayers();
        Participants participants = new Participants(players, Dealer.from());
        Deck shortDeck = Deck.from(List.of(
            new Card(Rank.TWO, Suit.SPADE),
            new Card(Rank.THREE, Suit.SPADE)
        ));

        // when & then
        assertThatThrownBy(() -> participants.dealInitialCards(shortDeck))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("뽑을 카드가 남아있지 않습니다.");
    }
}
