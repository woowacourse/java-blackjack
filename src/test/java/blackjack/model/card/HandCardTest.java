package blackjack.model.card;

import blackjack.model.ResultState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class HandCardTest {

    public static final Card CLUB_ACE = Card.of(CardSuit.CLUB, CardNumber.ACE);
    public static final Card DIA_EIGHT = Card.of(CardSuit.DIAMOND, CardNumber.EIGHT);
    public static final Card DIA_NINE = Card.of(CardSuit.DIAMOND, CardNumber.NINE);
    public static final Card DIA_ACE = Card.of(CardSuit.DIAMOND, CardNumber.ACE);
    public static final Card HEART_ACE = Card.of(CardSuit.HEART, CardNumber.ACE);

    @Test
    @DisplayName("ace가 1개일 경우의 점수 생성 테스트 - 성공 : ace를 11로 계산하면 버스트라면, 1로 계산해 점수를 생성")
    void generate_card_score_when_ace_count_1() {
        //given
        List<Card> cards = List.of(CLUB_ACE, DIA_EIGHT, DIA_NINE); // 1+8+9 = 18
        HandCard handCard = new HandCard(cards);
        // when
        CardScore score = handCard.cardScore(ResultState.STAND);
        //then
        Assertions.assertThat(score.getScore()).isEqualTo(18);
    }

    @Test
    @DisplayName("ace가 2개일 경우의 점수 생성 테스트 - 성공 : ace를 11로 계산하면 버스트라면, 1로 계산해 점수를 생성")
    void generate_card_score_when_ace_count_2() {
        //given
        List<Card> cards = List.of(CLUB_ACE, DIA_ACE, DIA_NINE); // 1+11+9 = 21
        HandCard handCard = new HandCard(cards);
        // when
        CardScore score = handCard.cardScore(ResultState.STAND);
        //then
        Assertions.assertThat(score.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("ace가 3개일 경우의 점수 생성 테스트 - 성공 : ace를 11로 계산하면 버스트라면, 1로 계산해 점수를 생성")
    void generate_card_score_when_ace_count_3() {
        //given
        List<Card> cards = List.of(CLUB_ACE, DIA_ACE, HEART_ACE); // 1+11+1 = 13
        HandCard handCard = new HandCard(cards);
        // when
        CardScore score = handCard.cardScore(ResultState.STAND);
        //then
        Assertions.assertThat(score.getScore()).isEqualTo(13);
    }
}
