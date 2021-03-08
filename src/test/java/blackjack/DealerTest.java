package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.GameTable;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participant;
import blackjack.utils.FixedCardDeck;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("dealer 초기에 카드 두장을 갖고 있는지 확인")
    void create3() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());

        List<Card> cards = gameTable.initCards();
        Participant dealer = new Dealer(cards);

        List<Card> playerCards = dealer.getUnmodifiableCards();
        assertThat(playerCards).contains(
            Card.from(Suits.CLOVER, Denominations.ACE),
            Card.from(Suits.CLOVER, Denominations.TWO));
    }

    @Test
    @DisplayName("dealer 카드 추가 지급")
    void add_card() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());

        List<Card> cards = gameTable.initCards();
        Participant dealer = new Dealer(cards);

        gameTable.giveCard(dealer);
        assertThat(dealer.getUnmodifiableCards()).contains(
            Card.from(Suits.CLOVER, Denominations.ACE),
            Card.from(Suits.CLOVER, Denominations.TWO),
            Card.from(Suits.CLOVER, Denominations.THREE));
    }

    @Test
    @DisplayName("dealer 지급된 카드 합계")
    void sum_cards() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());

        List<Card> cards = gameTable.initCards();
        Participant dealer = new Dealer(cards);

        int score = dealer.sumCards();
        assertThat(score).isEqualTo(3);
    }

    @Test
    @DisplayName("결과를 위한 플레이어에게 지급된 카드 합계")
    void sum_cards_for_result() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.DIAMOND, Denominations.SIX));
        Participant dealer = new Dealer(cards);
        int score = dealer.sumCardsForResult();
        assertThat(score).isEqualTo(17);
    }

    @Test
    @DisplayName("Dealer 16 초과한 경우")
    void sum_cards_for_result1() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.KING));
        Participant dealer = new Dealer(cards);

        assertThat(dealer.isAvailableToTake()).isEqualTo(false);
    }

    @Test
    @DisplayName("Dealer가 이긴 경우")
    void result1() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.KING));
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.resultOfPlayer(18)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("Dealer가 이긴 경우")
    void result2() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.KING));
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.resultOfPlayer(22)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("Dealer가 진 경우")
    void result3() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.KING));
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.resultOfPlayer(21)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("Dealer가 진 경우")
    void result4() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.SIX));
        Dealer dealer = new Dealer(cards);

        dealer.takeCard(Card.from(Suits.SPADE, Denominations.SIX));

        assertThat(dealer.resultOfPlayer(21)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("무승부인 경우")
    void result5() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.SIX));
        Dealer dealer = new Dealer(cards);

        dealer.takeCard(Card.from(Suits.SPADE, Denominations.SIX));

        assertThat(dealer.resultOfPlayer(23)).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("무승부인 경우")
    void result6() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.SIX));
        Dealer dealer = new Dealer(cards);

        dealer.takeCard(Card.from(Suits.SPADE, Denominations.FIVE));

        assertThat(dealer.resultOfPlayer(21)).isEqualTo(Outcome.DRAW);
    }

}
