package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.dto.PlayerBettingRequest;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.PlayersBettingRequest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("이름 목록으로 플레이어 일급 컬렉션을 생성한다.")
    @Test
    void createPlayers() {
        // given
        PlayerBettingRequest pobiRequest = PlayerBettingRequest.of("pobi", "1000");
        PlayerBettingRequest jasonRequest = PlayerBettingRequest.of("jason", "2000");
        PlayersBettingRequest playersBettingRequest = PlayersBettingRequest.from(List.of(pobiRequest, jasonRequest));

        // when
        Players players = Players.makePlayers(playersBettingRequest);

        // then
        assertThat(players.getAllPlayers()).hasSize(2);
        assertThat(players.getAllPlayerNickname()).containsExactly("pobi", "jason");
    }

    @DisplayName("카드를 받을 수 있는 플레이어를 순서대로 찾는다.")
    @Test
    void findDrawablePlayer() {
        // given
        PlayerBettingRequest pobiRequest = PlayerBettingRequest.of("pobi", "1000");
        PlayerBettingRequest jasonRequest = PlayerBettingRequest.of("jason", "2000");
        PlayersBettingRequest playersBettingRequest = PlayersBettingRequest.from(List.of(pobiRequest, jasonRequest));
        Players players = Players.makePlayers(playersBettingRequest);

        // when & then
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("pobi");

        players.dontWantDraw();
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("jason");

        players.dontWantDraw();
        assertThat(players.findDrawablePlayerNickname()).isNull();
    }

    @DisplayName("버스트된 플레이어는 건너뛰고 다음 드로우 가능 플레이어를 찾는다.")
    @Test
    void skipBustedPlayerWhenFindingDrawable() {
        PlayerBettingRequest pobiRequest = PlayerBettingRequest.of("pobi", "1000");
        PlayerBettingRequest jasonRequest = PlayerBettingRequest.of("jason", "2000");
        PlayersBettingRequest playersBettingRequest = PlayersBettingRequest.from(List.of(pobiRequest, jasonRequest));
        Players players = Players.makePlayers(playersBettingRequest);
        Hand bustedCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.TWO, Suit.DIAMOND)
        ));

        players.addCardToAvailablePlayer(bustedCards.getCards());

        assertThat(players.findDrawablePlayerNickname()).isEqualTo("jason");
    }

    @DisplayName("모든 플레이어가 카드를 받을 수 없는 상태면 null을 반환한다.")
    @Test
    void findDrawablePlayerReturnsNullWhenAllDone() {
        PlayerBettingRequest playerBettingRequest = PlayerBettingRequest.of("pobi", "1000");
        PlayersBettingRequest playersBettingRequest = PlayersBettingRequest.from(List.of(playerBettingRequest));
        Players players = Players.makePlayers(playersBettingRequest);

        players.dontWantDraw();

        assertThat(players.findDrawablePlayerNickname()).isNull();
    }

    @DisplayName("플레이어가 딜러보다 점수가 높으면 승리로 기록되어야 한다.")
    @Test
    void getPlayerWinningResultsPlayerWins() {
        PlayerBettingRequest playerBettingRequest = PlayerBettingRequest.of("pobi", "1000");
        PlayersBettingRequest playersBettingRequest = PlayersBettingRequest.from(List.of(playerBettingRequest));
        Players players = Players.makePlayers(playersBettingRequest);
        Hand playerCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART)
        ));
        players.addCardToAvailablePlayer(playerCards.getCards());
        Dealer dealer = Dealer.from();
        Hand dealerCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.DIAMOND),
            new Card(Rank.EIGHT, Suit.HEART)
        ));
        dealer.receiveCard(dealerCards.getCards());

        List<PlayerGameResult> winningResultsWithDealer = players.getWinningResultsWithDealer(dealer);
        PlayerGameResult result = winningResultsWithDealer.getFirst();

        assertThat(result.matchResult()).isEqualTo(MatchResult.WIN);
    }

    @DisplayName("플레이어가 딜러보다 점수가 낮으면 패배로 기록되어야 한다.")
    @Test
    void getPlayerWinningResultsPlayerLoses() {
        PlayerBettingRequest playerBettingRequest = PlayerBettingRequest.of("pobi", "1000");
        PlayersBettingRequest playersBettingRequest = PlayersBettingRequest.from(List.of(playerBettingRequest));
        Players players = Players.makePlayers(playersBettingRequest);
        Hand playerCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.DIAMOND),
            new Card(Rank.EIGHT, Suit.HEART)
        ));
        players.addCardToAvailablePlayer(playerCards.getCards());
        Dealer dealer = Dealer.from();
        Hand dealerCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART)
        ));
        dealer.receiveCard(dealerCards.getCards());

        List<PlayerGameResult> winningResultsWithDealer = players.getWinningResultsWithDealer(dealer);
        PlayerGameResult result = winningResultsWithDealer.getFirst();

        assertThat(result.matchResult()).isEqualTo(MatchResult.LOSE);
    }
}
