package blackjack.domain.blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.*;
import blackjack.domain.player.info.BettingMoney;
import blackjack.domain.player.info.PlayerInfo;
import blackjack.domain.player.info.Name;
import blackjack.domain.result.GamePlayerResult;
import blackjack.fixture.CardFixture;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("플레이어 정보를 통해 플레이어를 생성하고 딜러와 플레이어에게 카드를 두장씩 나눠준다.")
    void accept_players() {
        final Blackjack blackjack = new Blackjack(CardFixture.카드_덱_생성());
        final PlayerInfo playerInfo1 = new PlayerInfo(new Name("초롱"), new BettingMoney(1000));
        final PlayerInfo playerInfo2 = new PlayerInfo(new Name("조이썬"), new BettingMoney(1500));
        blackjack.acceptPlayers(List.of(playerInfo1, playerInfo2));

        assertPlayer(blackjack.getDealer(), 8);

        assertPlayer(blackjack.getGamePlayers()
                              .get(0), 13);
        assertPlayer(blackjack.getGamePlayers()
                              .get(1), 15);
    }

    @Test
    @DisplayName("게임 결과를 종합한다.")
    void dealer_Count_result() {
        final PlayerInfo playerInfo1 = new PlayerInfo(new Name("초롱"), new BettingMoney(1000));
        final PlayerInfo playerInfo2 = new PlayerInfo(new Name("조이썬"), new BettingMoney(1500));
        final var sut = new Blackjack(CardFixture.카드_덱_생성());
        sut.acceptPlayers(List.of(playerInfo1, playerInfo2));
        sut.getGamePlayers()
           .forEach(GamePlayer::stand);
        sut.getDealer()
           .stand();

        final var result = sut.checkResult();

        assertGamePlayerResult(result.getGamePlayerResults()
                                     .get(0), 1000);
        assertGamePlayerResult(result.getGamePlayerResults()
                                     .get(1), 1500);
        assertThat(result.getDealerResult()
                         .getPrizeMoney()).isEqualTo(-2500);
    }

    private void assertPlayer(final Participant participant, final int value) {
        assertThat(participant.calculateScore()).isEqualTo(value);
    }

    private void assertGamePlayerResult(final GamePlayerResult gamePlayerResult, final int prize) {
        assertThat(gamePlayerResult.getPrizeMoney()).isEqualTo(prize);
    }

}
