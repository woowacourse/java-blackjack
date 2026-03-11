package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private List<Card> cards;

    @Test
    @DisplayName("일반 카드들의 합산 점수를 계산한다")
    void calculateBasicScore() {
        //given
        User user1 = User.from("json");
        cards = List.of(Card.CLUB_ACE, Card.CLUB_JACK, Card.CLUB_SIX);

        //when
        user1.receiveInitCard(cards);
        user1.calculateScore();

        //then
        assertThat(user1.getScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("페이스 카드가 포함된 점수를 계산한다")
    void calculate_ace_as_eleven() {
        // given
        cards = List.of(Card.CLUB_ACE, Card.CLUB_JACK, Card.CLUB_QUEEN, Card.CLUB_KING);
        User user2 = User.from("poby");

        //when
        user2.receiveInitCard(cards);
        user2.calculateScore();

        //then
        assertThat(user2.getScore()).isEqualTo(31);
    }

    @Test
    @DisplayName("Ace를 제외한 점수의 합이 10 이하면 Ace 점수는 11이 된다.")
    public void if_remain_score_under10_ace_score_11() {
        //given
        User user = User.from("abc");
        int sum = 10;

        //when
        int aceScore = user.getAceBonus(sum);

        //then
        assertThat(aceScore).isEqualTo(10);
    }

    @Test
    @DisplayName("Ace를 제외한 점수의 합이 11 이상이면 Ace 점수는 1이 된다.")
    public void if_remain_score_over11_ace_score_1() {
        //given
        User user = User.from("abc");
        int sum = 11;
        int aceScore = user.getAceBonus(sum);
        assertThat(aceScore).isEqualTo(10);
    }

    @Test
    @DisplayName("카드의 합이 21이 넘을 시 버스트 판정이다")
    public void if_card_sum_over21_burst(){
        //given
        User user = User.from("json");
        List<Card> cards = List.of(Card.CLUB_THREE, Card.CLUB_KING, Card.CLUB_QUEEN);
        user.receiveInitCard(cards);


        //when
        user.calculateScore();
        boolean result = user.isBurst();


        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21이 넘지 않으면 버스트가 아니다")
    public void if_card_sum_under21_burst(){
        //given
        User user = User.from("json");
        List<Card> cards = List.of(Card.CLUB_THREE, Card.CLUB_TWO, Card.CLUB_TWO);

        //when
        user.calculateScore();
        boolean result = user.isBurst();

        //then
        assertThat(result).isFalse();
    }

}