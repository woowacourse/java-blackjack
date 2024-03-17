package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.BettingAmounts;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Names;
import blackjack.domain.player.Player;
import blackjack.domain.player.Profit;
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
        BettingAmounts battingAmounts = BettingAmounts.from(List.of("10000", "20000"));

        var result = blackjack.createPlayers(names.getNames(), battingAmounts.getBettingAmounts());

        assertPlayer(result.getDealer(), 8);

        assertPlayer(result.getGamePlayers()
                           .get(0), 13);
        assertPlayer(result.getGamePlayers()
                           .get(1), 15);
    }

    private void assertPlayer(Player player, int value) {
        assertThat(player.calculateScore()).isEqualTo(value);
    }

}
