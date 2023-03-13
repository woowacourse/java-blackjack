package domain;

import static domain.BlackJackWinningResult.LOSE;
import static domain.BlackJackWinningResult.WIN;
import static domain.card.CardInfo.*;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.bank.BettingMoney;
import domain.card.Card;
import domain.card.CardBox;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    @Test
    void 카드뭉치의_합이_16_이하인지_확인할_수_있다() {
        List<Card> cards = new ArrayList<>();

        //when
        cards.add(new Card(HEART, A));

        Dealer dealer = new Dealer(new CardBox(), new Cards(cards));

        //then
        assertThat(dealer.isSumUnderStandard()).isTrue();

        //when
        cards.add(new Card(HEART, TEN));

        //then
        assertThat(dealer.isSumUnderStandard()).isFalse();
    }

    @Test
    void 카드를_받아_자신의_카드뭉치에_더할수_있다() {
        List<Card> cards = new ArrayList<>();

        //when
        cards.add(new Card(HEART, A));

        Dealer dealer = new Dealer(new CardBox(), new Cards(cards));

        //중복카드면 false
        assertThat(dealer.addCard(new Card(HEART, A))).isFalse();
        //중복카드가 아니면 true
        assertThat(dealer.addCard(new Card(HEART, FOUR))).isTrue();
    }

    @Test
    void 본인의_카드뭉치의_총합을_반환한다() {
        List<Card> cards = new ArrayList<>();

        //when
        cards.add(new Card(HEART, A));

        Dealer dealer = new Dealer(new CardBox(), new Cards(cards));

        assertThat(dealer.sumOfCards()).isEqualTo(11);
    }

    @Test
    void 플레이어가_버스트일때_딜러상관없이_WIN반환() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(HEART, A));

        Dealer dealer = new Dealer(new CardBox(), new Cards(cards));
        Player player = new Player(new Name("우가"), new Cards(List.of(new Card(HEART, TEN), new Card(SPADE, TEN),
                new Card(CLOVER, TEN))), new BettingMoney(1000));

        assertThat(dealer.checkWinningResult(player)).isEqualTo(WIN);

        cards.add(new Card(HEART, TEN));

        assertThat(dealer.checkWinningResult(player)).isEqualTo(WIN);

        cards.add(new Card(HEART, TEN));
        cards.add(new Card(HEART, TEN));

        assertThat(dealer.checkWinningResult(player)).isEqualTo(WIN);
    }

    @Test
    void 플레이어가_버스트가_아니고_딜러가_버스트일때_LOSE반환() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(HEART, A));
        cards.add(new Card(HEART, TEN));
        cards.add(new Card(SPADE, TEN));
        cards.add(new Card(CLOVER, TEN));

        Dealer dealer = new Dealer(new CardBox(), new Cards(cards));
        Player player = new Player(new Name("우가"), new Cards(List.of(new Card(HEART, TEN))), new BettingMoney(1000));

        assertThat(dealer.checkWinningResult(player)).isEqualTo(LOSE);
    }

    @Test
    void 딜러는_최종수익의_반대값을_받아_교체한다() {
        Dealer dealer = new Dealer(new CardBox(), new Cards(List.of(new Card(HEART, A))));

        assertDoesNotThrow(
                () -> dealer.changeMoney(20000)
        );
    }

    @Test
    void 딜러는_카드를_뽑는다() {
        Dealer dealer = new Dealer(new CardBox(), new Cards(List.of(new Card(HEART, A))));

        assertDoesNotThrow(
                dealer::draw
        );
    }
}
