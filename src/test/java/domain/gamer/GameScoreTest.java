package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class GameScoreTest {
    @Test
    public void generateGameResultsTest() {
        Gamers gamers = Stream.of("pobi", "json")
                .map(name -> new Player(name, "50"))
                .collect(collectingAndThen(toList(), players -> new Gamers(players, new Dealer())));

        gamers.getPlayers().forEach(player -> {
            player.addCard(Arrays.asList(
                    new Card(CardSuit.CLOVER, CardNumber.JACK),
                    new Card(CardSuit.CLOVER, CardNumber.TEN))
            );
        });

        gamers.getDealer().addCard(Arrays.asList(
                new Card(CardSuit.CLOVER, CardNumber.SEVEN),
                new Card(CardSuit.CLOVER, CardNumber.TEN))
        );

        GameResult gameResult = new GameResult(gamers);

        Map<Player, Money> totalEarning = gameResult.getPlayersTotalEarning();
        List<Player> players = new ArrayList<>(totalEarning.keySet());

        assertThat(totalEarning.get(players.get(0)))
                .isEqualTo(new Money("50"));
    }
}