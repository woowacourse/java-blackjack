package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.view.dto.PlayerMatchResultOutcome;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("플레이어 이름은 한 글자 이상이 아니면 예외가 발생한다")
    void validatePlayerNameLengthTest() {
        // when & then
        assertThatThrownBy(() -> new Player(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("카드 딜링을 하면 플레이어가 카드를 2장 받는다")
    void dealTest() {
        // when
        Player player = new Player("dora");
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        // then
        assertThat(player.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어는 카드 합을 21에 가깝게 맞추기 위해 카드를 더 받을 수 있다")
    void drawTest() {
        // given
        Player player = new Player("dora");
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when
        player.drawCard(() -> new Card(Suit.HEART, Denomination.ACE));

        // then
        assertThat(player.getCards()).hasSize(3);
    }

    @Test
    @DisplayName("플레이어는 카드 합이 21을 넘지 않을 경우 얼마든지 카드를 계속 뽑을 수 있다")
    void canDrawTest() {
        // given
        Player player = new Player("dora");
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when & then
        assertThat(player.canDrawCard()).isTrue();
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면, 플레이어는 블랙잭 승리 한다")
    void determineMatchResultWhenPlayerOnlyBlackjackTest() {
        // given
        Player player = new Player("dora");
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        // when
        PlayerMatchResultOutcome playerMatchResultOutcome = player.determineMatchResult(dealer);

        // then
        assertThat(playerMatchResultOutcome.matchResult()).isEqualTo(MatchResult.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면, 플레이어는 패배한다")
    void determineMatchResultWhenDealerOnlyBlackjackTest() {
        // given
        Player player = new Player("dora");
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when
        PlayerMatchResultOutcome playerMatchResultOutcome = player.determineMatchResult(dealer);

        // then
        assertThat(playerMatchResultOutcome.matchResult()).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘다 블랙잭이면, 플레이어는 푸시한다")
    void determineMatchResultWhenDealerAndPlayerBlackjackTest() {
        // given
        Player player = new Player("dora");
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when
        PlayerMatchResultOutcome playerMatchResultOutcome = player.determineMatchResult(dealer);

        // then
        assertThat(playerMatchResultOutcome.matchResult()).isEqualTo(MatchResult.PUSH);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 블랙잭이 아니고, 플레이어만 버스트면, 플레이어는 패배한다")
    void determineMatchResultWhenPlayerOnlyBustTest() {
        // given
        Player player = new Player("dora");
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

        // when
        PlayerMatchResultOutcome playerMatchResultOutcome = player.determineMatchResult(dealer);

        // then
        assertThat(playerMatchResultOutcome.matchResult()).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 블랙잭이 아니고, 딜러만 버스트면, 플레이어는 승리한다")
    void determineMatchResultWhenDealerOnlyBustTest() {
        // given
        Player player = new Player("dora");
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

        // when
        PlayerMatchResultOutcome playerMatchResultOutcome = player.determineMatchResult(dealer);

        // then
        assertThat(playerMatchResultOutcome.matchResult()).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 블랙잭이 아니고, 플레이어와 딜러 둘다 버스트면, 플레이어는 패배한다")
    void determineMatchResultWhenDealerAndPlayerBustTest() {
        // given
        Player player = new Player("dora");
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

        // when
        PlayerMatchResultOutcome playerMatchResultOutcome = player.determineMatchResult(dealer);

        // then
        assertThat(playerMatchResultOutcome.matchResult()).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 블랙잭이 아니고, 플레이어와 딜러 둘다 버스트가 아니면, 플레이어는 딜러보다 카드 합이 더 높아야 승리한다")
    void determineMatchResultWhenCompareScoreTest() {
        // given
        Player player = new Player("dora");
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

        // when
        PlayerMatchResultOutcome playerMatchResultOutcome = player.determineMatchResult(dealer);

        // then
        assertThat(playerMatchResultOutcome.matchResult()).isEqualTo(MatchResult.WIN);
    }
}
