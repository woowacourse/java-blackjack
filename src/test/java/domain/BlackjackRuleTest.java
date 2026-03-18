package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.card.Card;
import domain.enums.GameResult;
import domain.enums.Rank;
import domain.enums.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackRuleTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player(new Name("stark"));
        dealer = new Dealer();
    }

    @DisplayName("플레이어가 버스트 되면 플레이어는 패배하고 딜러는 승리한다.")
    @Test
    void 플레이어_버스트_시_무조건_플레이어는_패배한다() {
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        player.addCard(new Card(Rank.QUEEN, Suit.CLOVER));
        player.addCard(new Card(Rank.EIGHT, Suit.CLOVER));

        GameResult playerResult = getPlayerResult();

        int dealerWinCount = getDealerResultCount(playerResult, GameResult.WIN);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerResult).isEqualTo(GameResult.LOSE);
            softAssertions.assertThat(dealerWinCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 높으면 플레이어가 승리하고 딜러는 패배한다.")
    @Test
    void 플레이어_점수_더_높으면_플레이어_승리한다() {
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        player.addCard(new Card(Rank.QUEEN, Suit.CLOVER));
        player.addCard(new Card(Rank.ACE, Suit.CLOVER));

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerLoseCount = getDealerResultCount(playerResult, GameResult.LOSE);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerResult).isEqualTo(GameResult.WIN);
            softAssertions.assertThat(dealerLoseCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 딜러가 버스트된 경우 플레이어가 승리한다.")
    @Test
    void 딜러가_버스트된_경우_플레이어가_승리한다() {
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        player.addCard(new Card(Rank.QUEEN, Suit.CLOVER));

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.SIX, Suit.HEART));
        dealer.addCard(new Card(Rank.SEVEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerLoseCount = getDealerResultCount(playerResult, GameResult.LOSE);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerResult).isEqualTo(GameResult.WIN);
            softAssertions.assertThat(dealerLoseCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 낮으면 플레이어가 패배하고 딜러는 승리한다.")
    @Test
    void 플레이어_점수_더_낮으면_플레이어_패배한다() {
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        player.addCard(new Card(Rank.NINE, Suit.CLOVER));

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerWinCount = getDealerResultCount(playerResult, GameResult.WIN);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerResult).isEqualTo(GameResult.LOSE);
            softAssertions.assertThat(dealerWinCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어와 딜러의 점수가 같으면 무승부가 된다.")
    @Test
    void 플레이어_딜러_점수_같으면_무승부_된다() {
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        player.addCard(new Card(Rank.QUEEN, Suit.CLOVER));

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerDrawCount = getDealerResultCount(playerResult, GameResult.DRAW);

        //then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerResult).isEqualTo(GameResult.DRAW);
            softAssertions.assertThat(dealerDrawCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 카드 2장으로 블랙잭이 되어 승리하면 BLACKJACK_WIN이 된다")
    @Test
    void 플레이어_카드_2장_블랙잭_승리_시_블랙잭_승리_된다() {
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        player.addCard(new Card(Rank.ACE, Suit.CLOVER));

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerLoseCount = getDealerResultCount(playerResult, GameResult.LOSE);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerResult).isEqualTo(GameResult.BLACKJACK_WIN);
            softAssertions.assertThat(dealerLoseCount).isEqualTo(1);
        });
    }

    @DisplayName("딜러가 블랙잭이고 플레이어가 카드 3장으로 21점이 되면 플레이어가 패배한다.")
    @Test
    void 딜러가_블랙잭이고_플레이어가_세_장으로_21점이면_플레이어_패배한다() {
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        player.addCard(new Card(Rank.EIGHT, Suit.CLOVER));
        player.addCard(new Card(Rank.THREE, Suit.CLOVER));

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.ACE, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerResultCount = getDealerResultCount(playerResult, GameResult.WIN);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerResult).isEqualTo(GameResult.LOSE);
            softAssertions.assertThat(dealerResultCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭이면 무승부가 된다.")
    @Test
    void 플레이어와_딜러_모두_블랙잭이면_무승부가_된다() {
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        player.addCard(new Card(Rank.ACE, Suit.CLOVER));

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.ACE, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerDrawCount = getDealerResultCount(playerResult, GameResult.DRAW);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerResult).isEqualTo(GameResult.DRAW);
            softAssertions.assertThat(dealerDrawCount).isEqualTo(1);
        });
    }

    private int getDealerResultCount(GameResult playerResult, GameResult dealerResult) {
        return BlackjackRule.judgeDealerResult(List.of(playerResult)).get(dealerResult);
    }

    private GameResult getPlayerResult() {
        return BlackjackRule.judgePlayerResult(player, dealer);
    }
}

