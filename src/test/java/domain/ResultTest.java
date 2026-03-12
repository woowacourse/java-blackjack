package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.card.Card;
import domain.enums.GameResult;
import domain.enums.Rank;
import domain.enums.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void set_up() {
        player = new Player("stark");
        dealer = new Dealer();
    }

    @DisplayName("플레이어가 버스트 되면 플레이어는 패배하고 딜러는 승리한다.")
    @Test
    void 플레이어_버스트_시_무조건_플레이어는_패배한다() {
        player.addCards(List.of(
                        new Card(Rank.JACK, Suit.CLOVER),
                        new Card(Rank.QUEEN, Suit.CLOVER),
                        new Card(Rank.EIGHT, Suit.CLOVER)
                )
        );

        GameResult playerResult = getPlayerResult();

        int dealerWinCount = getDealerResultCount(playerResult, GameResult.WIN);

        assertSoftly(softly -> {
            assertThat(playerResult).isEqualTo(GameResult.LOSE);
            assertThat(dealerWinCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 높으면 플레이어가 승리하고 딜러는 패배한다.")
    @Test
    void 플레이어_점수_더_높으면_플레이어_승리한다() {
        player.addCards(List.of(
                        new Card(Rank.JACK, Suit.CLOVER),
                        new Card(Rank.QUEEN, Suit.CLOVER),
                        new Card(Rank.ACE, Suit.CLOVER)
                )
        );

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerLoseCount = getDealerResultCount(playerResult, GameResult.LOSE);

        assertSoftly(softly -> {
            assertThat(playerResult).isEqualTo(GameResult.WIN);
            assertThat(dealerLoseCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 딜러가 버스트된 경우 플레이어가 승리한다.")
    @Test
    void 딜러가_버스트된_경우_플레이어가_승리한다() {
        player.addCards(List.of(
                        new Card(Rank.JACK, Suit.CLOVER),
                        new Card(Rank.QUEEN, Suit.CLOVER)
                )
        );

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.SIX, Suit.HEART));
        dealer.addCard(new Card(Rank.SEVEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerLoseCount = getDealerResultCount(playerResult, GameResult.LOSE);

        assertSoftly(softly -> {
            assertThat(playerResult).isEqualTo(GameResult.WIN);
            assertThat(dealerLoseCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 낮으면 플레이어가 패배하고 딜러는 승리한다.")
    @Test
    void 플레이어_점수_더_높으면_플레이어_패배한다() {
        player.addCards(List.of(
                        new Card(Rank.JACK, Suit.CLOVER),
                        new Card(Rank.NINE, Suit.CLOVER)
                )
        );

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerWinCount = getDealerResultCount(playerResult, GameResult.WIN);

        assertSoftly(softly -> {
            assertThat(playerResult).isEqualTo(GameResult.LOSE);
            assertThat(dealerWinCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어와 딜러의 점수가 같으면 무승부가 된다.")
    @Test
    void 플레이어_딜러_점수_같으면_무승부_된다() {
        player.addCards(List.of(
                        new Card(Rank.JACK, Suit.CLOVER),
                        new Card(Rank.QUEEN, Suit.CLOVER)
                )
        );

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerDrawCount = getDealerResultCount(playerResult, GameResult.DRAW);

        //then
        assertSoftly(softly -> {
            assertThat(playerResult).isEqualTo(GameResult.DRAW);
            assertThat(dealerDrawCount).isEqualTo(1);
        });
    }

    @DisplayName("플레이어가 카드 2장으로 블랙잭이 되어 승리하면 BLACKJACK_WIN이 된다")
    @Test
    void 플레이어_카드_2장_블랙잭_승리_시_블랙잭_승리_된다() {
        player.addCards(List.of(
                        new Card(Rank.JACK, Suit.CLOVER),
                        new Card(Rank.ACE, Suit.CLOVER)
                )
        );

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerLoseCount = getDealerResultCount(playerResult, GameResult.LOSE);

        assertSoftly(softly -> {
            assertThat(playerResult).isEqualTo(GameResult.BLACKJACK_WIN);
            assertThat(dealerLoseCount).isEqualTo(1);
        });
    }

    @DisplayName("딜러가 블랙잭이고 플레이어가 카드 3장으로 21점이 되면 플레이어가 패배한다.")
    @Test
    void 딜러_블랙잭_플레이어_패배() {
        player.addCards(List.of(
                        new Card(Rank.JACK, Suit.CLOVER),
                        new Card(Rank.EIGHT, Suit.CLOVER),
                        new Card(Rank.THREE, Suit.CLOVER)
                )
        );

        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.ACE, Suit.HEART));

        GameResult playerResult = getPlayerResult();
        int dealerResultCount = getDealerResultCount(playerResult, GameResult.WIN);

        assertSoftly(softly -> {
            assertThat(playerResult).isEqualTo(GameResult.LOSE);
            assertThat(dealerResultCount).isEqualTo(1);
        });
    }

    private Integer getDealerResultCount(GameResult playerResult, GameResult win) {
        return GameResult.calculateDealerResult(List.of(playerResult)).get(win);
    }

    private GameResult getPlayerResult() {
        return GameResult.calculatePlayerResult(player, dealer);
    }
}




