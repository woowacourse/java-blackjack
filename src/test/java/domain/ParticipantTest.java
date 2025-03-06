package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

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
        Participant participant2 = new Participant(cards2);
        //when
        GameResult result = participant.calculateGameResult(participant2);
        //then
        Assertions.assertThat(result).isEqualTo(GameResult.DRAW);
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
        Participant loser = new Participant(cards2);

        //when
        GameResult result = winner.calculateGameResult(loser);

        //then
        Assertions.assertThat(result).isEqualTo(GameResult.WIN);
    }
}