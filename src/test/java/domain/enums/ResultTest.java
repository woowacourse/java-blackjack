package domain.enums;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    private final Player player = new Player("피즈");
    private final Dealer dealer = new Dealer();

    @DisplayName("플레이어가 버스트 되면 플레이어는 패배하고 딜러는 승리한다.")
    @Test
    void 플레이어_버스트_시_무조건_플레이어는_패배한다() {
        List<Card> playerCards = List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER),
                new Card(Rank.EIGHT, Suit.CLOVER));
        playerCards.forEach(player::addCard);

        Result playerResult = Result.calculatePlayerResult(dealer, player);

        assertThat(playerResult).isEqualTo(Result.LOSE);
        assertThat(Result.getOpposite(playerResult)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 높으면 플레이어가 승리하고 딜러는 패배한다.")
    @Test
    void 플레이어_점수_더_높으면_플레이어_승리한다() {
        List<Card> playerCards = List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER),
                new Card(Rank.ACE, Suit.CLOVER));
        playerCards.forEach(player::addCard);

        List<Card> dealerCards = List.of(new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.FOUR, Suit.CLOVER),
                new Card(Rank.EIGHT, Suit.CLOVER), new Card(Rank.SEVEN, Suit.HEART));
        dealerCards.forEach(dealer::addCard);

        Result playerResult = Result.calculatePlayerResult(dealer, player);

        assertThat(playerResult).isEqualTo(Result.WIN);
        assertThat(Result.getOpposite(playerResult)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 딜러가 버스트된 경우 플레이어가 승리한다.")
    @Test
    void 딜러가_버스트된_경우_플레이어가_승리한다() {
        List<Card> playerCards = List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER));
        playerCards.forEach(player::addCard);

        List<Card> dealerCards = List.of(new Card(Rank.JACK, Suit.HEART), new Card(Rank.SIX, Suit.HEART),
                new Card(Rank.SEVEN, Suit.HEART));
        dealerCards.forEach(dealer::addCard);

        Result playerResult = Result.calculatePlayerResult(dealer, player);

        assertThat(playerResult).isEqualTo(Result.WIN);
        assertThat(Result.getOpposite(playerResult)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 낮으면 플레이어가 패배하고 딜러는 승리한다.")
    @Test
    void 플레이어_점수_더_높으면_플레이어_패배한다() {
        List<Card> playerCards = List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.NINE, Suit.CLOVER));
        playerCards.forEach(player::addCard);

        List<Card> dealerCards = List.of(new Card(Rank.JACK, Suit.HEART), new Card(Rank.SIX, Suit.HEART),
                new Card(Rank.FOUR, Suit.HEART));
        dealerCards.forEach(dealer::addCard);

        Result playerResult = Result.calculatePlayerResult(dealer, player);

        assertThat(playerResult).isEqualTo(Result.LOSE);
        assertThat(Result.getOpposite(playerResult)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어와 딜러의 점수가 같으면 무승부가 된다.")
    @Test
    void 플레이어_딜러_점수_같으면_무승부_된다() {
        List<Card> playerCards = List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.NINE, Suit.CLOVER));
        playerCards.forEach(player::addCard);

        List<Card> dealerCards = List.of(new Card(Rank.JACK, Suit.HEART), new Card(Rank.NINE, Suit.HEART));
        dealerCards.forEach(dealer::addCard);

        Result playerResult = Result.calculatePlayerResult(dealer, player);

        assertThat(playerResult).isEqualTo(Result.DRAW);
        assertThat(Result.getOpposite(playerResult)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어만 블랙잭이면 플레이어는 블랙잭으로 승리하고 딜러는 패배한다.")
    @Test
    void 플레이어만_블랙잭이면_플레이어는_블랙잭_승리_딜러는_패배한다() {
        List<Card> playerCards = List.of(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.QUEEN, Suit.SPADE));
        playerCards.forEach(player::addCard);

        List<Card> dealerCards = List.of(new Card(Rank.SEVEN, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER));
        dealerCards.forEach(dealer::addCard);

        Result playerResult = Result.calculatePlayerResult(dealer, player);

        assertThat(playerResult).isEqualTo(Result.WIN_BLACKJACK);
        assertThat(Result.getOpposite(playerResult)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부가 된다.")
    @Test
    void 플레이어와_딜러_모두_블랙잭이면_무승부_된다() {
        List<Card> playerCards = List.of(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.QUEEN, Suit.SPADE));
        playerCards.forEach(player::addCard);

        List<Card> dealerCards = List.of(new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER));
        dealerCards.forEach(dealer::addCard);

        Result playerResult = Result.calculatePlayerResult(dealer, player);

        assertThat(playerResult).isEqualTo(Result.DRAW);
        assertThat(Result.getOpposite(playerResult)).isEqualTo(Result.DRAW);
    }
}
