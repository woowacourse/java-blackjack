package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.ScoreCalculator;
import blackjack.domain.card.Suit;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinningResultTest {

    @Test
    void 승부를_보는_두명이_모두_21을_초과한_경우_무승부이다() {
        //given
        Cards mainCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.TEN),
                        new Card(Suit.HEART, Rank.TEN)
                ),
                new ScoreCalculator()
        );
        Cards subCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.DIAMOND, Rank.NINE),
                        new Card(Suit.HEART, Rank.NINE)
                ),
                new ScoreCalculator()
        );

        //when
        WinningResult result = WinningResult.decide(mainCards, subCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.DRAW);
    }

    @Test
    void 상대방이_21을_초과했고_본인이_21을_초과하지_않았다면_승리이다() {
        //given
        Cards mainCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.TEN)
                ),
                new ScoreCalculator()
        );
        Cards subCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.DIAMOND, Rank.NINE),
                        new Card(Suit.HEART, Rank.NINE)
                ),
                new ScoreCalculator()
        );

        //when
        WinningResult result = WinningResult.decide(mainCards, subCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 상대방이_21을_초과하지_않았고_본인이_21을_초과하지_않았을_때_더_높은_점수가_승리이다() {
        //given
        Cards mainCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.TEN)
                ),
                new ScoreCalculator()
        );
        Cards subCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.DIAMOND, Rank.NINE)
                ),
                new ScoreCalculator()
        );

        //when
        WinningResult result = WinningResult.decide(mainCards, subCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 상대방이_블랙잭이_아닌_21이고_본인이_블랙잭일_때_승리이다() {
        //given
        Cards mainCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.TEN)
                ),
                new ScoreCalculator()
        );
        Cards subCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.DIAMOND, Rank.NINE)
                ),
                new ScoreCalculator()
        );

        //when
        WinningResult result = WinningResult.decide(mainCards, subCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 상대방과_점수가_같다면_무승부이다() {
        //given
        Cards mainCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.TEN)
                ),
                new ScoreCalculator()
        );
        Cards subCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.CLUB, Rank.TWO),
                        new Card(Suit.DIAMOND, Rank.NINE)
                ),
                new ScoreCalculator()
        );

        //when
        WinningResult result = WinningResult.decide(mainCards, subCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.DRAW);
    }

    @Test
    void 상대방이_21을_초과하지_않았고_본인이_21을_초과하지_않았을_때_더_낮은_점수가_패배이다() {
        //given
        Cards mainCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.CLUB, Rank.TWO)),
                new ScoreCalculator()
        );
        Cards subCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.HEART, Rank.TEN)
                ),
                new ScoreCalculator()
        );

        //when
        WinningResult result = WinningResult.decide(mainCards, subCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 본인이_21을_초과하고_상대방이_21을_초과하지_않았다면_패배이다() {
        //given
        Cards mainCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.HEART, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.TWO)
                ),
                new ScoreCalculator()
        );
        Cards subCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.KING),
                        new Card(Suit.CLUB, Rank.ACE)
                ),
                new ScoreCalculator()
        );

        //when
        WinningResult result = WinningResult.decide(mainCards, subCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 상대방이_블랙잭이고_본인이_블랙잭이_아닌_21일_때_패배이다() {
        //given
        Cards mainCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.HEART, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.ONE)
                ),
                new ScoreCalculator()
        );
        Cards subCards = new Cards(
                List.of(
                        new Card(Suit.CLUB, Rank.KING),
                        new Card(Suit.CLUB, Rank.ACE)
                ),
                new ScoreCalculator()
        );

        //when
        WinningResult result = WinningResult.decide(mainCards, subCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.LOSE);
    }
}
