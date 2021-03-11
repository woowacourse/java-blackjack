package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.cardopen.AllCardsOpenStrategy;
import blackjack.domain.player.cardopen.OneCardOpenStrategy;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("카드를 추가로 받을수 있는지 가능여부 테스트 - 0일 때 true")
    @Test
    void canDrawCardWhen0() {
        Card card = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        Dealer dealer = new Dealer(card, card);
        assertThat(!dealer.isFinished()).isTrue();
    }

    @DisplayName("카드를 추가로 받을수 있는지 가능여부 테스트 - 16일 때 true")
    @Test
    void canDrawCardWhen16() {
        Card eightCard = Card.valueOf(CardShape.DIAMOND, CardNumber.EIGHT);
        Dealer dealer = new Dealer(eightCard, eightCard);
        assertThat(!dealer.isFinished()).isTrue();
    }

    @DisplayName("카드를 추가로 받을수 있는지 가능여부 테스트 - 17일 때 false")
    @Test
    void cannotDrawCardWhen17() {
        Card tenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TEN);
        Card sevenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN);
        Dealer dealer = new Dealer(tenCard, sevenCard);
        assertThat(!dealer.isFinished()).isFalse();
    }

    @DisplayName("카드 반환 개수 전략패턴 적용 테스트 - 1개")
    @Test
    void oneCardOpenStrategy() {
        Card card = Card.valueOf(CardShape.DIAMOND, CardNumber.ACE);
        Dealer dealer = new Dealer(card, card);
        dealer.setCardOpen(new OneCardOpenStrategy());
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN));
        assertThat(dealer.getCards()).hasSize(1);
    }

    @DisplayName("카드 반환 개수 전략패턴 적용 테스트 - 3개")
    @Test
    void twoCardOpenStrategy() {
        Card aceCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        Dealer dealer = new Dealer(aceCard, aceCard);
        dealer.drawRandomOneCard();
        dealer.setCardOpen(new AllCardsOpenStrategy());
        assertThat(dealer.getCards()).hasSize(3);
    }

    @DisplayName("딜러 최종수익 테스트")
    @Test
    void dealerResult() {
        Map<Name, Integer> result = new HashMap<>();
        result.put(new Name("pobi"), 10000);
        result.put(new Name("jason"), 15000);
        result.put(new Name("mungto"), -5000);

        Dealer dealer = new Dealer();
        assertThat(dealer.getResult(result)).isEqualTo(-20000);
    }
}