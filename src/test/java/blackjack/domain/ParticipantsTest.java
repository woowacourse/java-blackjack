package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.PlayerBettingRequest;
import blackjack.dto.PlayersBettingRequest;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("초기 분배 시 플레이어에게 두 장의 카드가 분배된다.")
    void distributeCardsSuccessfully() {
        // given
        PlayerBettingRequest playerBettingRequest = PlayerBettingRequest.of("pobi", "1000");
        PlayersBettingRequest playersBettingRequest = PlayersBettingRequest.from(List.of(playerBettingRequest));
        Players players = Players.makePlayers(playersBettingRequest);
        Dealer dealer = Dealer.from();
        Participants participants = new Participants(players, dealer);
        PlayingCards deck = PlayingCards.createShuffledDeck();

        // when
        participants.distributeCards(deck);
        List<Player> allPlayers = players.getAllPlayers();
        List<String> playerCards = Arrays.stream(allPlayers.getFirst().getCardStatus().split(",")).toList();

        // then
        assertThat(playerCards).hasSize(2);
    }

    @Test
    @DisplayName("덱에 남은 카드가 부족할 때 초기 분배를 시도하면 예외가 발생한다.")
    void distributeCardsThrowsExceptionOnShortDeck() {
        // given
        PlayerBettingRequest pobiRequest = PlayerBettingRequest.of("pobi", "1000");
        PlayerBettingRequest jasonRequest = PlayerBettingRequest.of("jason", "2000");
        PlayersBettingRequest playersBettingRequest = PlayersBettingRequest.from(List.of(pobiRequest, jasonRequest));
        Players players = Players.makePlayers(playersBettingRequest);
        Participants participants = new Participants(players, Dealer.from());
        PlayingCards shortDeck = PlayingCards.from(List.of(
            new Card(Rank.TWO, Suit.SPADE),
            new Card(Rank.THREE, Suit.SPADE)
        ));

        // when & then
        assertThatThrownBy(() -> participants.distributeCards(shortDeck))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("뽑을 카드가 남아있지 않습니다.");
    }
}
