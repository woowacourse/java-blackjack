package domain;

import domain.dto.GameFinalResultDto;
import domain.participant.Dealer;
import domain.participant.Players;
import org.junit.jupiter.api.Test;

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
        dealer.receiveCard(new Card(KING, HEART));
        dealer.receiveCard(new Card(TEN, SPADE));

        Players players = new Players();
        players.register("pobi", "10000");
        players.drawCardTo("pobi", new Card(EIGHT, HEART));
        players.drawCardTo("pobi", new Card(NINE, HEART));

        players.register("cary", "10000");
        players.drawCardTo("cary", new Card(TEN, HEART));
        players.drawCardTo("cary", new Card(JACK, HEART));

        players.register("jason", "10000");
        players.drawCardTo("jason", new Card(ACE, HEART));
        players.drawCardTo("jason", new Card(QUEEN, HEART));

        GameResult gameResult = GameResult.of(dealer, players);

        GameFinalResultDto finalResult = GameFinalResultDto.of(dealer, gameResult);

        assertThat(finalResult.getPlayerResults().entrySet())
                .extracting(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ).containsExactly(
                        tuple("pobi", -10000),
                        tuple("cary", 0),
                        tuple("jason", 15000)
                );
    }
}
