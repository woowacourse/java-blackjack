import domain.Card;
import domain.CardDeck;
import domain.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck();
    }

    @Test
    @DisplayName("첫 딜에 딜러는 패를 2장 갖는다.")
    public void dealer_should_open_1_card (){
        Dealer dealer = new Dealer();
        List<Card> cards = new ArrayList<>();
        cards.add(cardDeck.draw());
        cards.add(cardDeck.draw());
        dealer.receiveInitCard(cards);
        assertThat(dealer.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("첫 배부 때 합이 21 나오면 블랙잭 판정이다")
    public void if_card_sum_equals21_blackjack(){
        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));
        boolean result = dealer.isBlackjack();
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("첫 배부 때 합이 21이 안 나오면 블랙잭 판정이 아니다")
    public void if_card_sum_not21_blackjack(){
        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_NINE));
        boolean result = dealer.isBlackjack();
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21이 넘을 시 버스트 판정이다")
    public void if_card_sum_over21_burst(){
        int score = 22;
        Dealer dealer = new Dealer();
        boolean result = dealer.isBurst(score);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21이 넘지 않으면 버스트가 아니다")
    public void if_card_sum_under21_burst(){
        int score = 20;
        Dealer dealer = new Dealer();
        boolean result = dealer.isBurst(score);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("딜러는 처음 받은 2장의 합이 16 이하면 1장을 추가로 받아야 한다")
    public void if_dealer_card_sum_under16_must_one_more(){
        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SIX));
        boolean result = dealer.isHit();
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러는 처음 받은 2장의 합이 17 이상이면 추가로 받을 수 없다")
    public void if_dealer_card_sum_over17_stop(){
        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        boolean result = dealer.isHit();
        assertThat(result).isFalse();
    }
}
