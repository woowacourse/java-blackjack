package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vo.Bet;
import vo.GameResult;
import vo.Name;
import vo.Rank;
import vo.Suit;

public class BlackjackGameFlowTest {
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        Deck fixedDeck = new Deck(List.of(
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.FIVE),
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.HEART, Rank.THREE),
                new Card(Suit.DIAMOND, Rank.SIX),
                new Card(Suit.CLUB, Rank.SEVEN),
                new Card(Suit.CLUB, Rank.TEN)
        ));
        blackjackGame = new TestBlackjackGame(fixedDeck);
        List<Name> names = List.of(new Name("영기"), new Name("라이"));
        Map<Name, Bet> bets = new LinkedHashMap<>();
        names.forEach(name -> bets.put(name, new Bet("1000")));
        blackjackGame.prepare(bets);
    }

    @Test
    void 초기_배분_후_영기_카드_확인() {
        // when
        List<Card> cards = blackjackGame.getUsers().get(0).getCards();

        // then
        assertThat(cards.get(0).getRankName()).isEqualTo("K");
        assertThat(cards.get(0).getSuitName()).isEqualTo("스페이드");
        assertThat(cards.get(1).getRankName()).isEqualTo("A");
    }

    @Test
    void 초기_배분_후_라이_카드_확인() {
        // when
        List<Card> cards = blackjackGame.getUsers().get(1).getCards();

        // then
        assertThat(cards.get(0).getRankName()).isEqualTo("2");
        assertThat(cards.get(0).getSuitName()).isEqualTo("하트");
    }

    @Test
    void 라이_히트_후_카드_추가됨() {
        // when
        User user = blackjackGame.getUsers().get(1);
        blackjackGame.processPlayerDecision(user);

        // then
        assertThat(user.getCards()).hasSize(3);
    }

    @Test
    void 딜러_점수_16이하_히트_발생() {
        // when
        boolean dealt = blackjackGame.dealToDealer();

        // then
        assertThat(dealt).isTrue();
    }

    @Test
    void 영기_블랙잭으로_승리() {
        // given
        blackjackGame.dealToDealer();
        User user = blackjackGame.getUsers().getFirst();

        // when & then
        assertThat(blackjackGame.getResult().getUserResults().get(user)).isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    void 라이_히트_후_딜러보다_낮아_패배() {
        // given
        blackjackGame.processPlayerDecision(blackjackGame.getUsers().get(1));
        blackjackGame.dealToDealer();
        User user = blackjackGame.getUsers().get(1);

        // when & then
        assertThat(blackjackGame.getResult().getUserResults().get(user)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 영기_블랙잭_딜러_수익_마이너스() {
        // given
        blackjackGame.dealToDealer();

        // when
        GameSummary summary = blackjackGame.getResult();

        // then
        assertThat(summary.getDealerProfit()).isEqualTo(new BigDecimal("-500.0"));
    }
}
