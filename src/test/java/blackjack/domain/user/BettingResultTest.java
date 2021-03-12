package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BettingResultTest {

    @DisplayName("블랙잭인 경우 수익 : 1.5배")
    @Test
    void getEarningMoney_blackJack() {
        List<Card> cards = Stream.<Card>builder()
            .add(new Card(Denomination.JACK, Suit.SPADES)).add(new Card(Denomination.ACE, Suit.SPADES))
            .build()
            .collect(Collectors.toList());

        Player player = new Player("A", 20000, cards, 21);
        BettingResult bettingResult = new BettingResult(player.name, MatchResult.WIN_BLACKJACK.calculateEarningMoney(player.bettingMoney));

        assertThat(bettingResult.getEarningMoney()).isEqualTo(30000);
        assertThat(bettingResult.getName()).isEqualTo(player.getName());
    }

    @DisplayName("이긴 경우 수익 : 1배")
    @Test
    void getEarningMoney_win() {
        List<Card> cards = Stream.<Card>builder()
            .add(new Card(Denomination.NINE, Suit.SPADES)).add(new Card(Denomination.ACE, Suit.SPADES))
            .build()
            .collect(Collectors.toList());

        Player player = new Player("A", 20000, cards, 21);
        BettingResult bettingResult = new BettingResult(player.name, MatchResult.WIN_NORMAL.calculateEarningMoney(player.bettingMoney));

        assertThat(bettingResult.getEarningMoney()).isEqualTo(20000);
    }

    @DisplayName("비긴 경우 수익 : 수익 없음")
    @Test
    void getEarningMoney_draw() {
        List<Card> cards = Stream.<Card>builder()
            .add(new Card(Denomination.NINE, Suit.SPADES)).add(new Card(Denomination.ACE, Suit.SPADES))
            .build()
            .collect(Collectors.toList());

        Player player = new Player("A", 20000, cards, 21);
        BettingResult bettingResult = new BettingResult(player.name, MatchResult.DRAW.calculateEarningMoney(player.bettingMoney));

        assertThat(bettingResult.getEarningMoney()).isEqualTo(0);
    }

    @DisplayName("진 경우 수익 : 마이너스")
    @Test
    void getEarningMoney_lose() {
        List<Card> cards = Stream.<Card>builder()
            .add(new Card(Denomination.NINE, Suit.SPADES)).add(new Card(Denomination.ACE, Suit.SPADES))
            .build()
            .collect(Collectors.toList());

        Player player = new Player("A", 20000, cards, 21);
        BettingResult bettingResult = new BettingResult(player.name, MatchResult.LOSE.calculateEarningMoney(player.bettingMoney));

        assertThat(bettingResult.getEarningMoney()).isEqualTo(-20000);
    }
}