package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayersTest {

    @ParameterizedTest
    @CsvSource({"pobi,THREE,true", "jason,ACE,true", "neo,KING,false"})
    @DisplayName("플레이어의 카드 추가 가능 여부 테스트")
    void canHitTest(String name, Rank rankName, boolean expected) {
        // given
        Players players = Players.of(List.of("pobi", "jason", "neo"));
        List<Rank> ranks = List.of(Rank.JACK, Rank.JACK, Rank.JACK);
        List<Card> cards = ranks.stream().map(rank -> new Card(rank, Suit.HEART)).toList();
        players.addCardToAll(cards);
        players.addCardTo(name, new Card(rankName, Suit.HEART));
        players.addCardTo("neo", new Card(Rank.THREE, Suit.HEART));

        // when
        boolean actual = players.canHit(name);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어 인원 수에 맞게 카드 나눠주기 성공")
    void addCardToAllTest() {
        // given
        Players players = Players.of(List.of("pobi", "jason", "neo"));
        List<Rank> ranks = List.of(Rank.JACK, Rank.ACE, Rank.THREE);
        List<Card> cards = ranks.stream().map(rank -> new Card(rank, Suit.HEART)).toList();

        // when & then
        assertThatCode(() -> players.addCardToAll(cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어 인원 수보다 카드를 적게 나눠주는 경우 예외 발생")
    void throwExceptionWithNotEnoughCardsTest() {
        // given
        Players players = Players.of(List.of("pobi", "jason", "neo"));
        List<Rank> ranks = List.of(Rank.JACK, Rank.ACE);
        List<Card> cards = ranks.stream().map(rank -> new Card(rank, Suit.HEART)).toList();

        // when & then
        assertThatThrownBy(() -> players.addCardToAll(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 총 3장의 카드가 필요합니다.");
    }


    @ParameterizedTest
    @CsvSource({"pobi,THREE,10000,-10000", "jason,FIVE,20000,0", "neo,SEVEN,30000,30000"})
    @DisplayName("플레이어들의 수익 계산 기능 테스트")
    void calculateProfitsTest(String name, Rank rankName, int bet, int expectedProfit) {
        // given
        Players players = Players.of(List.of("pobi", "jason", "neo"));
        players.initializeBet((nickname) -> bet);
        List<Rank> ranks = List.of(Rank.JACK, Rank.JACK, Rank.JACK);
        List<Card> cards = ranks.stream().map(rank -> new Card(rank, Suit.HEART)).toList();
        players.addCardToAll(cards);
        players.addCardTo(name, new Card(rankName, Suit.HEART));

        Dealer dealer = new Dealer("딜러");
        dealer.addCard(new Card(Rank.JACK, Suit.HEART));
        dealer.addCard(new Card(Rank.FIVE, Suit.HEART));

        // when
        Map<String, Integer> profits = players.calculateProfits(dealer);
        int actualProfit = profits.get(name);

        // then
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }
}
