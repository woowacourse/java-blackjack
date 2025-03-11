package blackjack.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.ScoreCalculator;
import blackjack.domain.card.Suit;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class VictoryTest {
    @Test
    void 플레이어의_승패를_저장할_수_있다() {
        //given
        Player pobi = new Player("pobi",
                new Cards(List.of(
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.TEN)
                )), 1000);
        Player surf = new Player("surf",
                new Cards(List.of(
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.CLUB, Rank.FIVE),
                        new Card(Suit.HEART, Rank.THREE)
                )), 1000);
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.CLUB, Rank.NINE)
        );
        Players players = new Players(List.of(pobi, surf));
        Dealer dealer = new Dealer(new Cards(dealerCards, new ScoreCalculator()));

        //when
        Victory victory = Victory.create(dealer, players);

        //then
        assertAll(
                () -> assertThat(victory.getPlayerVictoryResults())
                        .isEqualTo(Map.of(pobi, WinningResult.LOSE, surf, WinningResult.WIN)),
                () -> assertThat(victory.getDealerVictoryResults())
                        .isEqualTo(Map.of(
                                WinningResult.LOSE, 1,
                                WinningResult.WIN, 1)
                        )
        );
    }
}
