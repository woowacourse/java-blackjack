package domain.participant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.BetMoney;
import domain.Rank;
import domain.Suit;
import domain.card.Card;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private static final Dealer DEALER_BLACKJACK = Dealer.createReady();
    private static final Dealer DEALER_BUST = Dealer.createReady();
    private static final Dealer DEALER_21 = Dealer.createReady();
    private static final Dealer DEALER_20 = Dealer.createReady();
    private static final Player PLAYER_BLACKJACK = Player.of("BLACKJACK", "10000");
    private static final Player PLAYER_BUST = Player.of("BUST", "10000");
    private static final Player PLAYER_21 = Player.of("21", "10000");
    private static final Player PLAYER_20 = Player.of("20", "10000");

    static {

        List<Card> scoreBlackjackCards = List.of(Card.of(Suit.CLUB, Rank.Q), Card.of(Suit.HEART, Rank.ACE));
        List<Card> scoreBustCards = List.of(Card.of(Suit.CLUB, Rank.Q), Card.of(Suit.HEART, Rank.Q),
                Card.of(Suit.DIAMOND, Rank.Q));
        List<Card> score20Cards = List.of(Card.of(Suit.CLUB, Rank.Q), Card.of(Suit.HEART, Rank.Q));
        List<Card> score21Cards = List.of(Card.of(Suit.CLUB, Rank.EIGHT), Card.of(Suit.CLUB, Rank.TWO),
                Card.of(Suit.HEART, Rank.ACE));

        DEALER_BLACKJACK.addCards(scoreBlackjackCards);
        DEALER_BUST.addCards(scoreBustCards);
        DEALER_21.addCards(score21Cards);
        DEALER_20.addCards(score20Cards);
        PLAYER_BLACKJACK.addCards(scoreBlackjackCards);
        PLAYER_BUST.addCards(scoreBustCards);
        PLAYER_21.addCards(score21Cards);
        PLAYER_20.addCards(score20Cards);
    }

    @Test
    void 플레이어가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> Player.of("jeje", "1000"));
    }

    @Test
    void 블랙잭_여부를_확인할_수_있다_성공() {
        Assertions.assertThat(PLAYER_BLACKJACK.isBlackjack()).isEqualTo(true);
    }

    @Test
    void 블랙잭_여부를_확인할_수_있다_실패() {
        Assertions.assertThat(PLAYER_20.isBlackjack()).isEqualTo(false);
    }

    @Test
    void 버스트_여부를_확인할_수_있다_성공() {
        Assertions.assertThat(PLAYER_BUST.isBust()).isEqualTo(true);
    }

    @Test
    void 버스트_여부를_확인할_수_있다_실패() {
        Assertions.assertThat(PLAYER_20.isBust()).isEqualTo(false);
    }

    @Test
    void 플레이어만_블랙잭이면_블랙잭으로_승리한다() {
        Assertions.assertThat(PLAYER_BLACKJACK.getResult(DEALER_20)).isEqualTo(BetMoney.valueOf(15000.0));
    }

    @Test
    void 둘_다_블랙잭이면_무승부로_처리한다() {
        Assertions.assertThat(PLAYER_BLACKJACK.getResult(DEALER_BLACKJACK)).isEqualTo(BetMoney.valueOf(0));
    }

    @Test
    void 딜러만_블랙잭이면_패배한다() {
        Assertions.assertThat(PLAYER_20.getResult(DEALER_BLACKJACK)).isEqualTo(BetMoney.valueOf(-10000));
    }

    @Test
    void 플레이어가_버스트이면_패배한다() {
        Assertions.assertThat(PLAYER_BUST.getResult(DEALER_BLACKJACK)).isEqualTo(BetMoney.valueOf(-10000));
    }

    @Test
    void 플레이어가_버스트가_아닐때_딜러가_버스트이면_승리한다() {
        Assertions.assertThat(PLAYER_20.getResult(DEALER_BUST)).isEqualTo(BetMoney.valueOf(10000.0));
    }

    @Test
    void 둘_다_버스트가_아니고_스코어가_같으면_무승부로_처리한다() {
        Assertions.assertThat(PLAYER_20.getResult(DEALER_20)).isEqualTo(BetMoney.valueOf(0));
    }

    @Test
    void 둘_다_버스트가_아니고_플레이어의_스코어가_더_높으면_승리한다() {
        Assertions.assertThat(PLAYER_21.getResult(DEALER_20)).isEqualTo(BetMoney.valueOf(10000.0));
    }

    @Test
    void 둘_다_버스트가_아니고_딜러의_스코어가_더_높으면_패배한다() {
        Assertions.assertThat(PLAYER_20.getResult(DEALER_21)).isEqualTo(BetMoney.valueOf(-10000));
    }
}
