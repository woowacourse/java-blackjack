package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class VictoryTest {
    @Test
    void 플레이어의_승패를_저장할_수_있다() {
        //given
        Player pobi = new Player("pobi", List.of(
                new Card(Suit.CLUB, Rank.THREE),
                new Card(Suit.CLUB, Rank.TEN)
        ), new ScoreCalculator());
        Player surf = new Player("surf", List.of(
                new Card(Suit.CLUB, Rank.THREE),
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.CLUB, Rank.FIVE),
                new Card(Suit.HEART, Rank.THREE)
        ), new ScoreCalculator());
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.CLUB, Rank.NINE)
        );
        Players players = new Players(List.of(pobi, surf));
        Stack<Card> cards = new Stack<>();
        cards.addAll(List.of(
                new Card(Suit.CLUB, Rank.FOUR),
                new Card(Suit.CLUB, Rank.FIVE),
                new Card(Suit.CLUB, Rank.ONE),
                new Card(Suit.DIAMOND, Rank.ONE),
                new Card(Suit.HEART, Rank.ONE),
                new Card(Suit.SPADE, Rank.ONE)
        ));
        Dealer dealer = new Dealer(
                players,
                new Deck(cards),
                dealerCards,
                new ScoreCalculator());

        Map<Player, WinningResult> playerVictoryResults =
                Map.of(pobi, WinningResult.LOSE,
                        surf, WinningResult.WIN);
        Map<WinningResult, Integer> dealerVictoryResult = Map.of(WinningResult.LOSE, 1, WinningResult.WIN, 1);

        //when
        Victory victory = Victory.create(dealer, players);

        //then
        Assertions.assertThat(victory.getPlayerVictoryResults()).isEqualTo(playerVictoryResults);
        Assertions.assertThat(victory.getDealerVictoryResults()).isEqualTo(dealerVictoryResult);
    }
}
