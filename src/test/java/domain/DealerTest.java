package domain;

import static fixture.CardFixture.*;
import static fixture.ShapeFixture.하트;
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

        cardsByCardBox.add(하트에이스);
        cardsByCardBox.add(하트3);
        Cards cards = new Cards(cardsByCardBox);

        Dealer dealer = new Dealer(name, cards);

        assertThat(dealer.isSumUnderStandard()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A,TEN,NINE,TEN,1",
            "NINE,TEN,NINE,TEN,0",
            "A,FIVE,NINE,TEN,-1",
    })
    void 플레이어랑_카드합을_비교해_반환한다(CardInfo dealerCardInfo1, CardInfo dealerCardInfo2, CardInfo playerCardInfo1,
                             CardInfo playerCardInfo2, int expected) {
        Name dealerName = new Name("hamad");
        List<Card> dealerCardsByCardBox = new ArrayList<>();
        dealerCardsByCardBox.add(new Card(하트, dealerCardInfo1));
        dealerCardsByCardBox.add(new Card(하트, dealerCardInfo2));
        Cards dealerCards = new Cards(dealerCardsByCardBox);

        Dealer dealer = new Dealer(dealerName, dealerCards);

        Name playerName = new Name("wuga");
        List<Card> playerCardsByCardBox = new ArrayList<>();
        playerCardsByCardBox.add(new Card(하트, playerCardInfo1));
        playerCardsByCardBox.add(new Card(하트, playerCardInfo2));
        Cards playerCards = new Cards(playerCardsByCardBox);

        Player player = new Player(playerName, playerCards);

        assertThat(dealer.checkWinningResult(player)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A,TEN,J,THREE,TEN,TWO,1",
            "Q,FIVE,J,NINE,TEN,J,0",
            "J,TEN,TWO,NINE,TEN,TWO,-1",
    })
    void 카드합을_비교해_둘다_21을_넘으면_0_딜러만_21을_넘으면_마이너스1_플레이어만_21을_넘으면_1을반환한다(
            CardInfo dealerCardInfo1, CardInfo dealerCardInfo2, CardInfo dealerCardInfo3,
            CardInfo playerCardInfo1, CardInfo playerCardInfo2, CardInfo playerCardInfo3,
            int expected) {
        Name dealerName = new Name("hamad");
        List<Card> dealerCardsByCardBox = new ArrayList<>();
        dealerCardsByCardBox.add(new Card(하트, dealerCardInfo1));
        dealerCardsByCardBox.add(new Card(하트, dealerCardInfo2));
        dealerCardsByCardBox.add(new Card(하트, dealerCardInfo3));
        Cards dealerCards = new Cards(dealerCardsByCardBox);

        Dealer dealer = new Dealer(dealerName, dealerCards);

        Name playerName = new Name("wuga");
        List<Card> playerCardsByCardBox = new ArrayList<>();
        playerCardsByCardBox.add(new Card(하트, playerCardInfo1));
        playerCardsByCardBox.add(new Card(하트, playerCardInfo2));
        playerCardsByCardBox.add(new Card(하트, playerCardInfo3));
        Cards playerCards = new Cards(playerCardsByCardBox);

        Player player = new Player(playerName, playerCards);

        assertThat(dealer.checkWinningResult(player)).isEqualTo(expected);
    }
}
