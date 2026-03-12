package domain;

import domain.card.Deck;
import domain.card.RandomShuffleStrategy;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.GameResult;
import domain.result.Referee;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @Test
    void 딜러와_참여중인_모든_플레이어의_승패_여부를_판정한다() {
        // given
        Deck deck = new Deck(new RandomShuffleStrategy());

        Referee referee = new Referee();
        List<Player> playerList = List.of(Player.from("토리", 10000),
                Player.from("이안", 20000), Player.from("에덴", 30000));
        Players players = new Players(playerList);

        Dealer dealer = new Dealer(deck);
        Map<Player, GameResult> gameResultMap = referee.judge(dealer, players);

        // when & then
        Assertions.assertThat(gameResultMap.size()).isEqualTo(3);
    }
}
