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
        List<Player> playerList = List.of(new Player("토리", 10000),
                new Player("에덴", 20000), new Player("이안", 30000));
        Players players = new Players(playerList);

        Dealer dealer = new Dealer();
        Map<Player, GameResult> gameResultMap = referee.judge(dealer, players);

        // when & then
        Assertions.assertThat(gameResultMap.size()).isEqualTo(3);
    }
}
