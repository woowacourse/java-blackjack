package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {

    @DisplayName("두 참가자의 카드 합이 같은 경우, 무승부를 판단할 수 있다")
    @Test
    void 무승부_판단() {
        //given
        List<Card> cardList = List.of(new Card(CardNumber.A, CardShape.CLOVER),
                new Card(CardNumber.A, CardShape.CLOVER));
        Cards cards = Cards.of(cardList);
        Participant participant = new Participant(cards);

        List<Card> cardList2 = List.of(new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER));
        Cards cards2 = Cards.of(cardList2);
        Dealer dealer = Dealer.of(cards2);
        //when
        GameResult result = GameResult.judge(participant, dealer);
        //then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("다른 참가자보다 21에 가까운 경우, 승리를 판단할 수 있다.")
    @Test
    void 승리_판단() {
        //given
        List<Card> cardList = List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.A, CardShape.CLOVER)
        );
        Cards cards = Cards.of(cardList);
        Participant winner = new Participant(cards);

        List<Card> cardList2 = List.of(
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Cards cards2 = Cards.of(cardList2);
        Dealer loser = Dealer.of(cards2);

        //when
        GameResult result = GameResult.judge(winner, loser);

        //then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @DisplayName("참가자가 burst인 경우에는 패배로 판단한다.")
    @Test
    void burst_패배_판단() {
        //given
        List<Card> cardList = List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.QUEEN, CardShape.DIAMOND)
        );
        Cards cards = Cards.of(cardList);
        Dealer burstDealer = Dealer.of(cards);
        List<Card> cardList2 = List.of(
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Cards cards2 = Cards.of(cardList2);
        Participant winner = new Participant(cards2);

        //when
        GameResult result = GameResult.judge(winner, burstDealer);

        //then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("참가자가 burst가 아니고, 숫자가 더 작은 경우에는 패배로 판단한다.")
    @Test
    void 패배_판단() {
        //given
        List<Card> cardList = List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        );
        Cards cards = Cards.of(cardList);
        Participant loser = new Participant(cards);

        List<Card> cardList2 = List.of(
                new Card(CardNumber.THREE, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Cards cards2 = Cards.of(cardList2);
        Dealer winner = Dealer.of(cards2);

        //when
        GameResult result = GameResult.judge(loser, winner);

        //then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("승리를 패배로, 패배를 승리로 바꾼다")
    @ParameterizedTest
    @CsvSource({
            "WIN, LOSE",
            "LOSE, WIN"
    })
    void test(GameResult origin, GameResult expected) {
        //when
        GameResult actual = origin.getReverse();
        //then
        assertThat(actual).isEqualTo(expected);
    }
}