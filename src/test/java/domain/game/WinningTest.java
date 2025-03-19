package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.HandCards;
import domain.card.Rank;
import domain.card.Shape;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
class WinningTest {

    Card aceClover = new Card(Rank.ACE, Shape.CLOVER);
    Card aceDiamond = new Card(Rank.ACE, Shape.DIAMOND);
    Card tenClover = new Card(Rank.TEN, Shape.CLOVER);
    Card tenDiamond = new Card(Rank.TEN, Shape.DIAMOND);
    Card tenHeart  = new Card(Rank.TEN, Shape.HEART);
    Card tenSpade = new Card(Rank.TEN, Shape.SPADE);
    Card nineClover = new Card(Rank.NINE, Shape.CLOVER);
    Card nineDiamond = new Card(Rank.NINE, Shape.DIAMOND);

    @Test
    void 플레이어와_딜러_모두_블랙잭일경우_무승부() {
        HandCards playerHands = new HandCards();
        playerHands.setUpCards(aceClover, tenClover);
        HandCards opponentHands = new HandCards();
        opponentHands.setUpCards(aceDiamond, tenDiamond);

        Winning winning = Winning.determine(playerHands, opponentHands);
        assertThat(winning).isEqualTo(Winning.DRAW);
    }

    @Test
    void 플레이어만_블랙잭일_경우_플레이어_블랙잭() {
        HandCards playerHands = new HandCards();
        playerHands.setUpCards(aceClover, tenClover);
        HandCards opponentHands = new HandCards();
        opponentHands.setUpCards(nineClover, tenDiamond);
        Winning winning = Winning.determine(playerHands, opponentHands);
        assertThat(winning).isEqualTo(Winning.BLACKJACK);
    }

    @Test
    void 딜러만_블랙잭일_경우_플레이어_패배() {
        HandCards playerHands = new HandCards();
        playerHands.setUpCards(nineClover, tenClover);
        HandCards opponentHands = new HandCards();
        opponentHands.setUpCards(aceClover, tenDiamond);
        Winning winning = Winning.determine(playerHands, opponentHands);
        assertThat(winning).isEqualTo(Winning.LOSE);
    }

    @Test
    void 플레이어가_21점을_넘으면_버스트로_패배() {
        HandCards playerHands = new HandCards();
        playerHands.setUpCards(nineClover, tenClover);
        playerHands.takeMore(tenClover);
        HandCards opponentHands = new HandCards();
        opponentHands.setUpCards(aceClover, aceDiamond);
        Winning winning = Winning.determine(playerHands, opponentHands);
        assertThat(winning).isEqualTo(Winning.LOSE);
    }

    @Test
    void 플레이어와_딜러_모두_버스트인_경우_플레이어_패배(){
        HandCards playerHands = new HandCards();
        playerHands.setUpCards(nineClover, tenClover);
        playerHands.takeMore(tenHeart);
        HandCards opponentHands = new HandCards();
        opponentHands.setUpCards(tenDiamond, tenSpade);
        opponentHands.takeMore(nineDiamond);
        Winning winning = Winning.determine(playerHands, opponentHands);
        assertThat(winning).isEqualTo(Winning.LOSE);
    }

    @Test
    void 플레이어가_21점이하고_딜러가_21점을_넘으면_딜러_버스트로_승리() {
        HandCards playerHands = new HandCards();
        playerHands.setUpCards(nineClover, tenClover);
        HandCards opponentHands = new HandCards();
        opponentHands.setUpCards(tenDiamond, tenSpade);
        opponentHands.takeMore(nineDiamond);
        Winning winning = Winning.determine(playerHands, opponentHands);
        assertThat(winning).isEqualTo(Winning.WIN);
    }
}
