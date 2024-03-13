package blackjack.model.referee;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Players;
import blackjack.view.dto.PlayerMatchResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {
    @Test
    @DisplayName("플레이어만 블랙잭이면, 플레이어는 블랙잭 승리 한다")
    void determineMatchResultWhenPlayerOnlyBlackjackTest() {
        // given
        Players players = new Players(List.of("mia"));
        players.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        // when
        Referee referee = new Referee(players, dealer);
        List<PlayerMatchResult> playerMatchResults = referee.determinePlayersMatchResult();

        // then
        assertThat(playerMatchResults.get(0).matchResult()).isEqualTo(MatchResult.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면, 플레이어는 패배한다")
    void determineMatchResultWhenDealerOnlyBlackjackTest() {
        // given
        Players players = new Players(List.of("mia"));
        players.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when
        Referee referee = new Referee(players, dealer);
        List<PlayerMatchResult> playerMatchResults = referee.determinePlayersMatchResult();

        // then
        assertThat(playerMatchResults.get(0).matchResult()).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘다 블랙잭이면, 플레이어는 푸시한다")
    void determineMatchResultWhenDealerAndPlayerBlackjackTest() {
        // given
        Players players = new Players(List.of("mia"));
        players.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when
        Referee referee = new Referee(players, dealer);
        List<PlayerMatchResult> playerMatchResults = referee.determinePlayersMatchResult();

        // then
        assertThat(playerMatchResults.get(0).matchResult()).isEqualTo(MatchResult.PUSH);
    }
}
