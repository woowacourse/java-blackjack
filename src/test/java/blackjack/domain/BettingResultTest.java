package blackjack.domain;

import org.junit.jupiter.api.Test;

class BettingResultTest {

    private Player createPlayerWithCards(TrumpCard... cards) {
        Player player = Player.of(Name.of("테스트"));
        player = player.withBetAmount(BetAmount.of(10000));
        for (TrumpCard card : cards) {
            player.receiveCard(card);
        }
        return player;
    }

    @Test
    void 플레이어만_블랙잭이면_BLACKJACK이다() {
        Player player = createPlayerWithCards(
                TrumpCard.of(Suit.SPADE, Rank.ACE),
                TrumpCard.of(Suit.SPADE, Rank.KING)
        );
        assertThat(BettingResult.of(MatchResult.WIN, player))
                .isEqualTo(BettingResult.BLACKJACK);
    }

    @Test
    void 일반_승리면_WIN이다() {
        Player player = createPlayerWithCards(
                TrumpCard.of(Suit.SPADE, Rank.NINE),
                TrumpCard.of(Suit.SPADE, Rank.KING)
        );
        assertThat(BettingResult.of(MatchResult.WIN, player))
                .isEqualTo(BettingResult.WIN);
    }

    @Test
    void 무승부면_DRAW다() {
        Player player = createPlayerWithCards(
                TrumpCard.of(Suit.SPADE, Rank.NINE),
                TrumpCard.of(Suit.SPADE, Rank.KING)
        );
        assertThat(BettingResult.of(MatchResult.DRAW, player))
                .isEqualTo(BettingResult.DRAW);
    }

    @Test
    void 패배면_LOSE다() {
        Player player = createPlayerWithCards(
                TrumpCard.of(Suit.SPADE, Rank.NINE),
                TrumpCard.of(Suit.SPADE, Rank.KING)
        );
        assertThat(BettingResult.of(MatchResult.LOSE, player))
                .isEqualTo(BettingResult.LOSE);
    }

    @Test
    void BLACKJACK이면_배팅금액의_1_5배_수익이다() {
        assertThat(BettingResult.BLACKJACK.calculateProfit(BetAmount.of(10000)))
                .isEqualTo(15000);
    }

    @Test
    void WIN이면_배팅금액만큼_수익이다() {
        assertThat(BettingResult.WIN.calculateProfit(BetAmount.of(10000)))
                .isEqualTo(10000);
    }

    @Test
    void DRAW면_수익이_0이다() {
        assertThat(BettingResult.DRAW.calculateProfit(BetAmount.of(10000)))
                .isEqualTo(0);
    }

    @Test
    void LOSE면_배팅금액만큼_잃는다() {
        assertThat(BettingResult.LOSE.calculateProfit(BetAmount.of(10000)))
                .isEqualTo(-10000);
    }
}