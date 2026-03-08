package domain;

import domain.dto.GameFinalResultDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.constant.Rank.*;
import static domain.constant.Rank.NINE;
import static domain.constant.Suit.HEART;
import static domain.constant.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class GameResultTest {

    @Test
    void 플레이어의_게임_결과를_저장한다() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(KING, HEART));
        dealer.addCard(new Card(TEN, SPADE));

        Player player1 = new Player("pobi", new Hand());
        player1.addCard(new Card(EIGHT, HEART));
        player1.addCard(new Card(NINE, HEART));

        Player player2 = new Player("cary", new Hand());
        player2.addCard(new Card(TEN, HEART));
        player2.addCard(new Card(JACK, HEART));

        Player player3 = new Player("jason", new Hand());
        player3.addCard(new Card(ACE, HEART));
        player3.addCard(new Card(QUEEN, HEART));

        GameResult gameResult = new GameResult(dealer, List.of(player1, player2, player3));

        GameFinalResultDto finalResult = gameResult.convertToDto();

        assertThat(finalResult.getPlayerResults())
                .extracting(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ).containsExactly(
                        tuple("pobi", "패"),
                        tuple("cary", "무"),
                        tuple("jason", "승")
                );
    }
}
