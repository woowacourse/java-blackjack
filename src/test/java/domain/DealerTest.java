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
        Name name = new Name("hamad");
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("A하트", 11));
        cardsByCardBox.add(new Card("3하트", 3));
        Cards cards = new Cards(cardsByCardBox);

        Dealer dealer = new Dealer(name, cards);

        assertThat(dealer.isSumUnderStandard()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A하트,5하트,11,5,9하트,10하트,9,10,1",
            "9하트,10하트,9,10,9하트,10하트,9,10,0",
            "A하트,10하트,11,10,9하트,10하트,9,10,-1",
    })
    void 플레이어랑_카드합을_비교해_반환한다(String playerCard1, String playerCard2, int playerCardValue1, int playerCardValue2,
                             String dealerCard1, String dealerCard2, int dealerCardValue1, int dealerCardValue2,
                             int expected) {
        Name dealerName = new Name("hamad");
        List<Card> dealerCardsByCardBox = new ArrayList<>();
        dealerCardsByCardBox.add(new Card(dealerCard1, dealerCardValue1));
        dealerCardsByCardBox.add(new Card(dealerCard2, dealerCardValue2));
        Cards dealerCards = new Cards(dealerCardsByCardBox);

        Dealer dealer = new Dealer(dealerName, dealerCards);

        Name playerName = new Name("wuga");
        List<Card> playerCardsByCardBox = new ArrayList<>();
        playerCardsByCardBox.add(new Card(playerCard1, playerCardValue1));
        playerCardsByCardBox.add(new Card(playerCard2, playerCardValue2));
        Cards playerCards = new Cards(playerCardsByCardBox);

        Player player = new Player(playerName, playerCards);

        assertThat(dealer.checkWinningResult(player)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Q하트,10하트,J하트,11,10,10,3하트,10하트,2하트,3,10,2,1",
            "Q하트,5하트,J하트,11,5,10,9하트,10하트,J하트,9,10,10,0",
            "9하트,10하트,2하트,9,10,2,9하트,10하트,J하트,9,10,10,-1"
    })
    void 카드합을_비교해_둘다_21을_넘으면_0_딜러만_21을_넘으면_마이너스1_플레이어만_21을_넘으면_1을반환한다(
            String playerCard1, String playerCard2, String playerCard3, int playerCardValue1, int playerCardValue2,
            int playerCardValue3,
            String dealerCard1, String dealerCard2, String dealerCard3, int dealerCardValue1, int dealerCardValue2,
            int dealerCardValue3,
            int expected) {
        Name dealerName = new Name("hamad");
        List<Card> dealerCardsByCardBox = new ArrayList<>();
        dealerCardsByCardBox.add(new Card(dealerCard1, dealerCardValue1));
        dealerCardsByCardBox.add(new Card(dealerCard2, dealerCardValue2));
        dealerCardsByCardBox.add(new Card(dealerCard3, dealerCardValue3));
        Cards dealerCards = new Cards(dealerCardsByCardBox);

        Dealer dealer = new Dealer(dealerName, dealerCards);

        Name playerName = new Name("wuga");
        List<Card> playerCardsByCardBox = new ArrayList<>();
        playerCardsByCardBox.add(new Card(playerCard1, playerCardValue1));
        playerCardsByCardBox.add(new Card(playerCard2, playerCardValue2));
        playerCardsByCardBox.add(new Card(playerCard3, playerCardValue3));
        Cards playerCards = new Cards(playerCardsByCardBox);

        Player player = new Player(playerName, playerCards);

        assertThat(dealer.checkWinningResult(player)).isEqualTo(expected);
    }
}
