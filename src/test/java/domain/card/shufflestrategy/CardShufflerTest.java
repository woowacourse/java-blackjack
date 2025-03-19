package domain.card.shufflestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardShufflerTest {

    @DisplayName("카드를 셔플할 수 있다.")
    @Test
    void shuffleCards() {
        //given
        List<Card> cards = Arrays.asList(
                new Card(Symbol.COLVER, Rank.FIVE),
                new Card(Symbol.SPADE, Rank.FOUR),
                new Card(Symbol.HEART, Rank.EIGHT),
                new Card(Symbol.DIAMOND, Rank.FIVE)
        );

        List<Card> stu1 = new ArrayList<>(cards);
        List<Card> stu2 = new ArrayList<>(cards);

        //when
        CardShufflerStub cardShufflerStub = new CardShufflerStub();
        cardShufflerStub.shuffle(stu1);
        cardShufflerStub.shuffle(stu2);

        //than
        assertThat(stu1).isEqualTo(stu2);
    }

}
