package model.result;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.KING;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.betting.Bet;
import model.betting.PlayerBets;
import model.card.Card;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantProfitsTest {

    @DisplayName("딜러와 참가자들의 배팅 수익 결과 생성")
    @Test
    void createParticipantProfits() {
        Players players = preparePlayers();
        Dealer dealer = new Dealer(List.of(new Card(KING, HEART), new Card(JACK, SPADE)));
        PlayerBets bets = prepareBets();

        ParticipantProfits participantProfits = ParticipantProfits.of(players, dealer, bets);

        ParticipantProfit jojoProfit = participantProfits.getPlayerProfits().get(0);
        ParticipantProfit dealerProfit = participantProfits.getDealerProfit();

        assertAll(
            () -> assertThat(jojoProfit.getProfit()).isEqualTo(15000),
            () -> assertThat(dealerProfit.getProfit()).isEqualTo(-15000)
        );
    }

    private Players preparePlayers() {
        Players players = Players.from(List.of("조조"));
        Player jojo = Player.of("조조", List.of(new Card(ACE, HEART), new Card(JACK, SPADE)));
        players.getPlayers().set(0, jojo);
        return players;
    }

    private PlayerBets prepareBets() {
        Map<String, Bet> bets = new HashMap<>();
        bets.put("조조", new Bet(10000));
        return new PlayerBets(bets);
    }
}
