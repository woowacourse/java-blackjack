package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import blackjack.model.dealer.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MatchResultTest {
    @Test
    @DisplayName("플레이어만 블랙잭이면, 플레이어는 블랙잭 승리 한다")
    void determineMatchResultWhenPlayerOnlyBlackjackTest() {
        // given
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        // when & then
        assertThat(MatchResult.determine(dealer, player)).isEqualTo(MatchResult.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면, 플레이어는 패배한다")
    void determineMatchResultWhenDealerOnlyBlackjackTest() {
        // given
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when & then
        assertThat(MatchResult.determine(dealer, player)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘다 블랙잭이면, 플레이어는 푸시한다")
    void determineMatchResultWhenDealerAndPlayerBlackjackTest() {
        // given
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when & then
        assertThat(MatchResult.determine(dealer, player)).isEqualTo(MatchResult.PUSH);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 블랙잭이 아니고, 플레이어만 버스트면, 플레이어는 패배한다")
    void determineMatchResultWhenPlayerOnlyBustTest() {
        // given
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.KING)
        )));
        player.drawCard(() -> new Card(Suit.HEART, Denomination.KING));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when & then
        assertThat(MatchResult.determine(dealer, player)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 블랙잭이 아니고, 딜러만 버스트면, 플레이어는 승리한다")
    void determineMatchResultWhenDealerOnlyBustTest() {
        // given
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.KING)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.FIVE)
        )));
        dealer.drawCards(() -> new Card(Suit.HEART, Denomination.KING));

        // when & then
        assertThat(MatchResult.determine(dealer, player)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 블랙잭이 아니고, 플레이어와 딜러 둘다 버스트면, 플레이어는 패배한다")
    void determineMatchResultWhenDealerAndPlayerBustTest() {
        // given
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.KING)
        )));
        player.drawCard(() -> new Card(Suit.HEART, Denomination.KING));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.FIVE)
        )));
        dealer.drawCards(() -> new Card(Suit.HEART, Denomination.KING));

        // when & then
        assertThat(MatchResult.determine(dealer, player)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 블랙잭이 아니고, 플레이어와 딜러 둘다 버스트가 아니면, 플레이어는 딜러보다 점수가 더 높아야 승리한다")
    void determineMatchResultWhenCompareScoreTest() {
        // given
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.FIVE)
        )));
        player.drawCard(() -> new Card(Suit.HEART, Denomination.FIVE));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.FIVE)
        )));
        dealer.drawCards(() -> new Card(Suit.HEART, Denomination.FOUR));

        // when & then
        assertThat(MatchResult.determine(dealer, player)).isEqualTo(MatchResult.WIN);
    }
}
