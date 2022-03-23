package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantProfitTest {

    @Test
    @DisplayName("플레이어와 딜러의 수익을 정상 생성한다.")
    void createParticipantProfit() {
        final int bettingMoney = 10000;
        final String playerName = "slow";
        final List<Player> players = createPlayers(playerName, bettingMoney);
        final Dealer dealer = createDealer();

        final ParticipantProfit participantProfit = ParticipantProfit.create(dealer, players);
        final Double playerProfit = participantProfit.getPlayerProfit().get(playerName);
        final double dealerProfit = participantProfit.getDealerProfit();

        assertThat(playerProfit).isEqualTo(bettingMoney * 1.5);
        assertThat(dealerProfit).isEqualTo(playerProfit * -1);
    }

    private List<Player> createPlayers(String playerName, int bettingMoney) {
        final Cards playerCards = new Cards(List.of(
                new Card(CardPattern.HEART, CardNumber.ACE),
                new Card(CardPattern.HEART, CardNumber.KING)
        ));
        List<Player> players = new ArrayList<>();
        Player player = new Player(playerName, playerCards);
        player.betMoney(bettingMoney);
        players.add(player);

        return players;
    }

    private Dealer createDealer() {
        final Cards dealerCards = new Cards(List.of(
                new Card(CardPattern.DIAMOND, CardNumber.FOUR),
                new Card(CardPattern.DIAMOND, CardNumber.FIVE)
        ));
        return new Dealer(dealerCards);
    }
}
