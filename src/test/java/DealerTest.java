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
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        cardDeck = new CardDeck();
    }

    @Test
    @DisplayName("첫 딜에 딜러는 패를 2장 갖는다.")
    public void dealer_should_open_1_card (){
        List<Card> cards = new ArrayList<>();
        cards.add(cardDeck.deal());
        cards.add(cardDeck.deal());
        dealer.receiveInitCard(cards);
        assertThat(dealer.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러는 처음 받은 2장의 합이 16 이하면 1장을 추가로 받아야 한다")
    public void if_dealer_card_sum_under16_must_one_more(){
        List<Card> cards = List.of(Card.CLUB_ACE, Card.CLUB_EIGHT, Card.CLUB_SIX);
        dealer.receiveInitCard(cards);
        dealer.calculateScore();
        boolean result = dealer.isHit();
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러는 처음 받은 2장의 합이 17 이상이면 추가로 받을 수 없다")
    public void if_dealer_card_sum_over17_stop(){
        List<Card> cards = List.of(Card.CLUB_THREE, Card.CLUB_QUEEN, Card.CLUB_SIX);
        dealer.receiveInitCard(cards);
        dealer.calculateScore();
        boolean result = dealer.isHit();
        assertThat(result).isFalse();
    }
}
