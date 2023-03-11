package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DealerTest {

    @Test
    void 카드뭉치의_합이_16_이하인지_확인할_수_있다() {
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("A하트", 11));
        cardsByCardBox.add(new Card("3하트", 3));
        Cards cards = new Cards(cardsByCardBox);

        Dealer dealer = new Dealer(cards);

        assertThat(dealer.isSumUnderStandard()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A하트,5하트,11,5,WIN",
            "9하트,10하트,9,10,DRAW",
            "A하트,10하트,11,10,LOSE"
    })
    void 플레이어보다_21에_가깝다면_승리(String card1, String card2, int cardValue1, int cardValue2, Result expected) {
        List<Card> dealerCardsByCardBox = new ArrayList<>();
        dealerCardsByCardBox.add(new Card("9하트", 9));
        dealerCardsByCardBox.add(new Card("10하트", 10));
        Cards dealerCards = new Cards(dealerCardsByCardBox);

        Dealer dealer = new Dealer(dealerCards);

        Name playerName = new Name("wuga");
        List<Card> playerCardsByCardBox = new ArrayList<>();
        playerCardsByCardBox.add(new Card(card1, cardValue1));
        playerCardsByCardBox.add(new Card(card2, cardValue2));
        Cards playerCards = new Cards(playerCardsByCardBox);
        Betting betting = new Betting(10000,0);

        Player player = new Player(playerCards, playerName,betting);

        assertThat(dealer.checkWinningResult(dealer,player)).isEqualTo(expected);
    }

    @Test
     void 딜러의_카드총합이_16이하인지_판단(){
        //given
        List<Card> dealerCardsByCardBox = new ArrayList<>();
        dealerCardsByCardBox.add(new Card("5하트", 5));
        dealerCardsByCardBox.add(new Card("10하트", 10));
        Cards dealerCards = new Cards(dealerCardsByCardBox);

        Dealer dealer = new Dealer(dealerCards);
        //then
        assertThat(dealer.isSumUnderStandard()).isTrue();
    }
}
