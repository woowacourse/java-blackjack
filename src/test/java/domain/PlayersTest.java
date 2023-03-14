package domain;


import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Player;
import domain.player.Players;
import domain.stake.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayersTest {

    @Test
    void player는_5명까지_참여_가능하다() {
        //given,//when
        List<String> playerNames = List.of("judy", "jude", "pobi", "lio", "neo", "joan");

        //then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레어이는 5명까지 참가 가능합니다.");
    }

    @Test
    void player는_중복된_이름을_가질_수_없다() {
        //given, when
        List<String> playerNames = List.of("judy", "judy");
        //then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레이어 이름은 중복일 수 없습니다.");
    }

    @Test
    @DisplayName("players와 dealer의 점수를 비교한다.")
    void dealerCompareWithPlayersTest() {
        //given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("jude"));
        Player jude = players.getPlayers().get(0);
        dealer.drawCard(new Card(Suit.HEART, Denomination.ACE));
        jude.drawCard(new Card(Suit.HEART, Denomination.JACK));
        jude.drawCard(new Card(Suit.HEART, Denomination.JACK));
        final var bets = new HashMap<Player, Bet>();
        bets.put(jude, Bet.from(1000));
        Bet from = Bet.from(1000);
        Bet negate = from.negate();
        //when
        Map<Gambler, Bet> playerStatusMap = players.calculateFinalResults(dealer, bets);
        //then
        assertThat(playerStatusMap).containsExactly(Map.entry(dealer, negate), Map.entry(jude, Bet.from(1000)));
    }
}
