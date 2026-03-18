package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.dto.PlayerGameResult;
import blackjack.fixture.PlayersFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("이름 목록으로 플레이어 일급 컬렉션을 생성한다.")
    @Test
    void createPlayers() {
        // given
        Players players = PlayersFixture.createValidTwoPlayers();

        // then
        assertThat(players.getAllPlayers()).hasSize(2);
        assertThat(players.getAllPlayerNickname()).containsExactly("boye", "sumin");
    }

    @DisplayName("첫 번째 드로우 가능 플레이어를 찾는다.")
    @Test
    void getFirstDrawablePlayer() {
        // given
        Players players = PlayersFixture.createValidTwoPlayers();

        // when & then
        assertThat(players.hasDrawablePlayer()).isTrue();
        assertThat(players.getDrawablePlayerNickname()).isEqualTo("boye");
    }

    @DisplayName("플레이어가 드로우를 멈추면 다음 플레이어가 드로우 대상이 된다.")
    @Test
    void moveToNextDrawablePlayerWhenCurrentPlayerStops() {
        // given
        Players players = PlayersFixture.createValidTwoPlayers();

        // when
        players.dontWantDraw();

        // then
        assertThat(players.hasDrawablePlayer()).isTrue();
        assertThat(players.getDrawablePlayerNickname()).isEqualTo("sumin");
    }

    @DisplayName("버스트된 플레이어는 건너뛰고 다음 드로우 가능 플레이어를 찾는다.")
    @Test
    void skipBustedPlayerWhenFindingDrawable() {
        // given
        Nickname boyeNickname = Nickname.from("boye");
        Nickname suminNickname = Nickname.from("sumin");
        Amount boyeAmount = Amount.from("1000");
        Amount suminAmount = Amount.from("20000");
        Players players = Players.makeEmptyPlayers().addPlayer(boyeNickname, boyeAmount)
            .addPlayer(suminNickname, suminAmount);
        Hand bustedCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.TWO, Suit.DIAMOND)
        ));

        // when
        players.addCardToAvailablePlayer(bustedCards.getCards());

        // then
        assertThat(players.getDrawablePlayerNickname()).isEqualTo("sumin");
    }

    @DisplayName("모든 플레이어가 카드를 받을 수 없는 상태면 drawable player가 없다.")
    @Test
    void hasNoDrawablePlayerWhenAllDone() {
        // given
        Players players = PlayersFixture.createValidSinglePlayer();

        // when
        players.dontWantDraw();

        // then
        assertThat(players.hasDrawablePlayer()).isFalse();
    }

    @DisplayName("플레이어가 딜러보다 점수가 높으면 승리로 기록되어야 한다.")
    @Test
    void getPlayerWinningResultsPlayerWins() {
        // given
        Players players = PlayersFixture.createValidSinglePlayer();
        Hand playerCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART)
        ));
        Dealer dealer = Dealer.from();
        Hand dealerCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.DIAMOND),
            new Card(Rank.EIGHT, Suit.HEART)
        ));
        players.addCardToAvailablePlayer(playerCards.getCards());
        dealer.receiveCard(dealerCards.getCards());

        // when
        List<PlayerGameResult> winningResultsWithDealer = players.getWinningResultsWithDealer(dealer);
        PlayerGameResult result = winningResultsWithDealer.getFirst();

        // then
        assertThat(result.matchResult()).isEqualTo(MatchResult.WIN);
    }

    @DisplayName("플레이어가 딜러보다 점수가 낮으면 패배로 기록되어야 한다.")
    @Test
    void getPlayerWinningResultsPlayerLoses() {
        // given
        Players players = PlayersFixture.createValidSinglePlayer();
        Hand playerCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.DIAMOND),
            new Card(Rank.EIGHT, Suit.HEART)
        ));
        Dealer dealer = Dealer.from();
        Hand dealerCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART)
        ));
        players.addCardToAvailablePlayer(playerCards.getCards());
        dealer.receiveCard(dealerCards.getCards());

        // when
        List<PlayerGameResult> winningResultsWithDealer = players.getWinningResultsWithDealer(dealer);
        PlayerGameResult result = winningResultsWithDealer.getFirst();

        // then
        assertThat(result.matchResult()).isEqualTo(MatchResult.LOSE);
    }
}
