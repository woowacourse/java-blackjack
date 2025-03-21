package card;

import card.*;
import score.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @DisplayName("Cards 내의 카드 총 점수 계산 확인")
    @Test
    void calculateCardsScoreTest() {
        //given
        List<Card> addCards = List.of(
                new Card(Suit.CLUBS, NormalRank.FIVE),
                new Card(Suit.CLUBS, NormalRank.FIVE),
                new Card(Suit.CLUBS, NormalRank.FIVE)
        );
        Cards cards = addCards(addCards);

        //when
        Score score = cards.calculateScore();

        //then
        assertThat(score.getValue()).isEqualTo(15);
    }

    @DisplayName("카드 총 점수가 버스트인 경우 : true")
    @Test
    void bustTest() {
        //given
        List<Card> addCards = List.of(
                new Card(Suit.CLUBS, AceRank.SOFT_ACE),
                new Card(Suit.CLUBS, NormalRank.JACK),
                new Card(Suit.CLUBS, AceRank.HARD_ACE)
        );
        Cards cards = addCards(addCards);

        //when
        //then
        assertThat(cards.isBust()).isTrue();
    }

    @DisplayName("카드 총 점수가 버스트가 아닌 경우: false ")
    @Test
    void nonBustTest() {
        //given
        List<Card> addCards = List.of(
                new Card(Suit.CLUBS, AceRank.SOFT_ACE),
                new Card(Suit.CLUBS, NormalRank.JACK)
        );
        Cards cards = addCards(addCards);

        //when
        //then
        assertThat(cards.isBust()).isFalse();
    }

    @DisplayName("카트 총 점수가 특정 조건 미만이면 Hit: true")
    @Test
    void overConditionTest() {
        //given
        List<Card> addCards = List.of(
                new Card(Suit.CLUBS, AceRank.SOFT_ACE),
                new Card(Suit.CLUBS, NormalRank.FIVE)
        );
        Cards cards = addCards(addCards);

        //when
        //then
        assertThat(cards.isHit(17)).isTrue();
    }

    @DisplayName("카트 총 점수가 특정 조건이 이상이면: false")
    @Test
    void nonOverConditionTest() {
        //given
        List<Card> addCards = List.of(
                new Card(Suit.CLUBS, AceRank.SOFT_ACE),
                new Card(Suit.CLUBS, NormalRank.SEVEN)
        );
        Cards cards = addCards(addCards);

        //when
        //then
        assertThat(cards.isHit(17)).isFalse();
    }

    @DisplayName("SOFT ACE를 가지고 있는 경우: True")
    @Test
    void containAceTest() {
        //given
        List<Card> addCards = List.of(
                new Card(Suit.CLUBS, AceRank.SOFT_ACE),
                new Card(Suit.CLUBS, NormalRank.SEVEN)
        );
        Cards cards = addCards(addCards);

        //when
        //then
        assertThat(cards.hasSoftAce()).isTrue();
    }

    @DisplayName("SOFT ACE를 가지고 있지 않은 경우: false")
    @Test
    void notContainAceTest() {
        //given
        List<Card> addCards = List.of(
                new Card(Suit.CLUBS, AceRank.HARD_ACE),
                new Card(Suit.CLUBS, NormalRank.SEVEN)
        );
        Cards cards = addCards(addCards);

        //when
        //then
        assertThat(cards.hasSoftAce()).isFalse();
    }

    @DisplayName("SoftAce를 가지고 있는 경우 HardAce로 변경")
    @Test
    void convertSoftAceToHardAceTest() {
        //given
        List<Card> addCards = List.of(
                new Card(Suit.CLUBS, AceRank.SOFT_ACE),
                new Card(Suit.CLUBS, NormalRank.SEVEN)
        );
        Cards cards = addCards(addCards);

        //when
        cards.convertSoftAceToHardAce();

        //then
        assertThat(cards.hasSoftAce()).isFalse();
    }


    private Cards addCards(List<Card> addCards) {
        Cards cards = new Cards();
        for (Card card : addCards) {
            cards.add(card);
        }
        return cards;
    }

    @DisplayName("블랙잭인 경우 : true")
    @Test
    void blackjackTrueTest() {
        Cards cards = new Cards();
        cards.add(new Card(Suit.DIAMONDS,AceRank.SOFT_ACE));
        cards.add(new Card(Suit.DIAMONDS, NormalRank.TEN));
        assertThat(cards.isBlackJack()).isTrue();
    }

    @DisplayName("블랙잭인 경우 : false")
    @Test
    void blackjackFalseTest() {
        Cards cards = new Cards();
        cards.add(new Card(Suit.DIAMONDS,NormalRank.FIVE));
        cards.add(new Card(Suit.DIAMONDS, NormalRank.SIX));
        cards.add(new Card(Suit.DIAMONDS, NormalRank.TEN));
        assertThat(cards.isBlackJack()).isFalse();
    }
}
