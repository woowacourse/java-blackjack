package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.dto.PlayerGameResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("이름 목록으로 플레이어 일급 컬렉션을 생성한다.")
    @Test
    void createPlayers() {
        // given
        Nickname boyeNickname = Nickname.from("boye");
        Nickname suminNickname = Nickname.from("sumin");
        Amount boyeAmount = Amount.from("1000");
        Amount suminAmount = Amount.from("20000");

        // when
        Players players = Players.makeEmptyPlayers().addPlayer(boyeNickname, boyeAmount)
            .addPlayer(suminNickname, suminAmount);

        // then
        assertThat(players.getAllPlayers()).hasSize(2);
        assertThat(players.getAllPlayerNickname()).containsExactly("boye", "sumin");
    }

    @DisplayName("카드를 받을 수 있는 플레이어를 순서대로 찾는다.")
    @Test
    void findDrawablePlayer() {
        // given
        Nickname boyeNickname = Nickname.from("boye");
        Nickname suminNickname = Nickname.from("sumin");
        Amount boyeAmount = Amount.from("1000");
        Amount suminAmount = Amount.from("20000");
        Players players = Players.makeEmptyPlayers().addPlayer(boyeNickname, boyeAmount)
            .addPlayer(suminNickname, suminAmount);

        // when & then
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("boye");

        players.dontWantDraw();
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("sumin");

        players.dontWantDraw();
        assertThat(players.findDrawablePlayerNickname()).isNull();
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
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("sumin");
    }

    @DisplayName("모든 플레이어가 카드를 받을 수 없는 상태면 null을 반환한다.")
    @Test
    void findDrawablePlayerReturnsNullWhenAllDone() {
        // given
        Nickname boyeNickname = Nickname.from("boye");
        Amount boyeAmount = Amount.from("1000");
        Players players = Players.makeEmptyPlayers().addPlayer(boyeNickname, boyeAmount);

        // when
        players.dontWantDraw();

        // then
        assertThat(players.findDrawablePlayerNickname()).isNull();
    }

    @DisplayName("플레이어가 딜러보다 점수가 높으면 승리로 기록되어야 한다.")
    @Test
    void getPlayerWinningResultsPlayerWins() {
        // given
        Nickname boyeNickname = Nickname.from("boye");
        Amount boyeAmount = Amount.from("1000");
        Players players = Players.makeEmptyPlayers().addPlayer(boyeNickname, boyeAmount);
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
        Nickname boyeNickname = Nickname.from("boye");
        Amount boyeAmount = Amount.from("1000");
        Players players = Players.makeEmptyPlayers().addPlayer(boyeNickname, boyeAmount);
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
