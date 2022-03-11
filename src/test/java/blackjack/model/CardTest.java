package blackjack.model;

import static blackjack.model.Rank.ACE;
import static blackjack.model.Suit.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource({"EIGHT,DIAMOND,8", "JACK,HEART,10", "ACE,CLOVER,1", "KING,SPADE,10", "QUEEN,HEART,10", "FIVE,DIAMOND,5",
            "FOUR,HEART,4"})
    @DisplayName("카드 hard rank 정보 반환 테스트")
    void getHardRank(Rank rank, Suit suit, int expect) {
        Card card = new Card(rank, suit);
        assertThat(card.hardRank()).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource({"ACE,DIAMOND,true", "FIVE,HEART,false"})
    @DisplayName("ACE인지 판별하는 테스트")
    void isAce(Rank rank, Suit suit, boolean expect) {
        Card card = new Card(rank, suit);
        assertThat(card.isAce()).isEqualTo(expect);
    }

    @Test
    @DisplayName("Softhand Rank 반환 테스트")
    void getSoftRank() {
        Card card = new Card(ACE, DIAMOND);
        assertThat(card.softRank()).isEqualTo(11);
    }

    @Test
    @DisplayName("카드의 rank 반환")
    void rank() {
        Card card = new Card(ACE, DIAMOND);
        assertThat(card.getRank()).isEqualTo(ACE);
    }

    @Test
    @DisplayName("카드의 suit 반환")
    void suit() {
        Card card = new Card(ACE, DIAMOND);
        assertThat(card.getSuit()).isEqualTo(DIAMOND);
    }

}
