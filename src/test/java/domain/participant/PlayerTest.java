package domain.participant;

import domain.BetMoney;
import domain.BlackjackGame;
import domain.Rank;
import domain.Suit;
import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {
    private static final Dealer DEALER_BLACKJACK = new Dealer(BlackjackGame.DEALER_NAME);
    private static final Dealer DEALER_BUST = new Dealer(BlackjackGame.DEALER_NAME);
    private static final Dealer DEALER_21 = new Dealer(BlackjackGame.DEALER_NAME);
    private static final Dealer DEALER_20 = new Dealer(BlackjackGame.DEALER_NAME);
    private static final Player PLAYER_BLACKJACK = new Player("BLACKJACK", 10000);
    private static final Player PLAYER_BUST = new Player("BUST", 10000);
    private static final Player PLAYER_21 = new Player("21", 10000);
    private static final Player PLAYER_20 = new Player("20", 10000);

    static {

        List<Card> scoreBlackjackCards = List.of(new Card(Suit.CLUB, Rank.Q), new Card(Suit.HEART, Rank.ACE));
        List<Card> scoreBustCards = List.of(new Card(Suit.CLUB, Rank.Q), new Card(Suit.HEART, Rank.Q), new Card(Suit.DIAMOND, Rank.Q));
        List<Card> score20Cards = List.of(new Card(Suit.CLUB, Rank.Q), new Card(Suit.HEART, Rank.Q));
        List<Card> score21Cards = List.of(new Card(Suit.CLUB, Rank.EIGHT), new Card(Suit.CLUB, Rank.TWO), new Card(Suit.HEART, Rank.ACE));


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
        assertDoesNotThrow(() -> new Player("minseo"));
    }

    @Test
    void 블랙잭_여부를_확인할_수_있다_성공() {
        Player player = new Player("jeje");

        player.addCard(new Card(Suit.CLUB, Rank.Q));
        player.addCard(new Card(Suit.CLUB, Rank.ACE));
        Assertions.assertThat(player.isBlackjack()).isEqualTo(true);
    }

    @Test
    void 블랙잭_여부를_확인할_수_있다_실패() {
        Player player = new Player("jeje");

        player.addCard(new Card(Suit.CLUB, Rank.TWO));
        player.addCard(new Card(Suit.CLUB, Rank.ACE));
        Assertions.assertThat(player.isBlackjack()).isEqualTo(false);
    }

    @Test
    void 버스트_여부를_확인할_수_있다_성공() {
        Player player = new Player("jeje");

        player.addCard(new Card(Suit.CLUB, Rank.Q));
        player.addCard(new Card(Suit.CLUB, Rank.K));
        player.addCard(new Card(Suit.CLUB, Rank.J));
        Assertions.assertThat(player.isBust()).isEqualTo(true);
    }

    @Test
    void 버스트_여부를_확인할_수_있다_실패() {
        Player player = new Player("jeje");

        player.addCard(new Card(Suit.CLUB, Rank.ACE));
        Assertions.assertThat(player.isBust()).isEqualTo(false);
    }

    @Test
    void 플레이어만_블랙잭이면_블랙잭으로_승리한다() {
        Assertions.assertThat(PLAYER_BLACKJACK.getResult(DEALER_20)).isEqualTo(BetMoney.of(15000.0));
    }

    @Test
    void 둘_다_블랙잭이면_무승부로_처리한다() {
        Assertions.assertThat(PLAYER_BLACKJACK.getResult(DEALER_BLACKJACK)).isEqualTo(BetMoney.of(0));
    }

    @Test
    void 딜러만_블랙잭이면_패배한다() {
        Assertions.assertThat(PLAYER_20.getResult(DEALER_BLACKJACK)).isEqualTo(BetMoney.of(-10000));
    }

    @Test
    void 플레이어가_버스트이면_패배한다() {
        Assertions.assertThat(PLAYER_BUST.getResult(DEALER_BLACKJACK)).isEqualTo(BetMoney.of(-10000));
    }

    @Test
    void 플레이어가_버스트가_아닐때_딜러가_버스트이면_승리한다() {
        Assertions.assertThat(PLAYER_20.getResult(DEALER_BUST)).isEqualTo(BetMoney.of(10000.0));
    }

    @Test
    void 둘_다_버스트가_아니고_스코어가_같으면_무승부로_처리한다() {
        Assertions.assertThat(PLAYER_20.getResult(DEALER_20)).isEqualTo(BetMoney.of(0));
    }

    @Test
    void 둘_다_버스트가_아니고_플레이어의_스코어가_더_높으면_승리한다() {
        Assertions.assertThat(PLAYER_21.getResult(DEALER_20)).isEqualTo(BetMoney.of(10000.0));
    }

    @Test
    void 둘_다_버스트가_아니고_딜러의_스코어가_더_높으면_패배한다() {
        Assertions.assertThat(PLAYER_20.getResult(DEALER_21)).isEqualTo(BetMoney.of(-10000));
    }
}
