package domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Card;
import domain.model.Cards;
import domain.model.Dealer;
import domain.model.Player;
import domain.model.Players;
import domain.type.Letter;
import domain.type.Suit;
import domain.vo.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackResultMakerTest {

    private final BlackJackResultMaker blackJackResultMaker = new BlackJackResultMaker();
    private final Cards dealerCards = new Cards(Set.of(new Card(Suit.SPADE, Letter.TWO)));
    private final Dealer dealer = new Dealer(dealerCards);
    private static final List<Card> playerCards = List.of(
        new Card(Suit.SPADE, Letter.SIX),
        new Card(Suit.DIAMOND, Letter.ACE));
    private static Players players;

    @BeforeAll
    public static void setUp() {
        final List<String> names = new ArrayList<>();
        final List<Double> battings = new ArrayList<>();
        IntStream.range(0, playerCards.size())
            .forEach(i -> {
                names.add("test" + i);
                battings.add((double) (i * 1000));
            });
        players = Players.from(names, battings);
        players.addAll(playerCards);
    }


    @Test
    @DisplayName("플레이어들 승패 결과 만들기 테스트(플레이어 모두 승)")
    public void testMakeParticipantsResult() {
        //given
        //when
        Map<Player, Result> results = blackJackResultMaker.makePlayersResult(dealer, players);

        //then
        results.keySet().forEach(player -> {
            assertThat(results.get(player).getVictory()).isEqualTo(1);
            assertThat(results.get(player).getDefeat()).isEqualTo(0);
        });
    }

    @Test
    @DisplayName("딜러 승패 결과 만들기 테스트(딜러 10패, 플레이어 모두 승)")
    public void testMakeDealerResult() {
        //given
        //when
        Result result = blackJackResultMaker.makeDealerResult(dealer, players);

        //then
        assertThat(result.getDefeat()).isEqualTo(playerCards.size());
        assertThat(result.getVictory()).isEqualTo(0);
        assertThat(result.getDraw()).isEqualTo(0);
    }
}
