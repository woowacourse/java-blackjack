package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.common.Name;
import blackjack.domain.common.Names;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Player;
import blackjack.domain.result.ResultStatus;
import blackjack.fixture.CardFixture;
import blackjack.fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("이름 목록을 통해 플레이어를 생성하고 딜러와 플레이어에게 카드를 두장씩 나눠준다.")
    public void Blackjack_Accept_players() {
        Blackjack blackjack = new Blackjack(CardFixture.카드_덱_생성());
        Names names = Names.from(List.of("초롱", "조이썬"));

        var result = blackjack.acceptPlayers(names);

        assertPlayer(result.getDealer(), 8);

        assertPlayer(result.getGamePlayers()
                           .get(0), 13);
        assertPlayer(result.getGamePlayers()
                           .get(1), 15);
    }

    @Test
    @DisplayName("블랙잭은 게임 결과를 종합한다.")
    public void Dealer_Count_result() {
        GamePlayer gamePlayer = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.THREE));
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        Dealer dealer = new Dealer(name, cards);
        var sut = new Blackjack();

        var result = sut.checkResult(dealer, List.of(gamePlayer));

        assertThat(result.getGamePlayerResults()
                         .get(0)
                         .getResultStatus()).isEqualTo(ResultStatus.LOSE);
        assertThat(result.getDealerResult()
                         .getResultWithResultStatus(ResultStatus.WIN)).isEqualTo(1);
    }

    private void assertPlayer(Player player, int value) {
        assertThat(player.calculateScore()).isEqualTo(value);
    }

}
