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

    private GameService gameService;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck();
        gameService = new GameService();
    }

    @Test
    @DisplayName("첫 딜에 딜러는 패를 2장 갖는다.")
    public void dealer_should_open_1_card (){
        Dealer dealer = new Dealer();
        List<Card> cards = new ArrayList<>();
        cards.add(cardDeck.deal());
        cards.add(cardDeck.deal());
        dealer.receiveInitCard(cards);
        assertThat(dealer.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러는 처음 받은 2장의 합이 16 이하면 1장을 추가로 받아야 한다")
    public void if_dealer_card_sum_under16_must_one_more(){
        int score = 16;
        boolean result = gameService.isHit(score);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러는 처음 받은 2장의 합이 17 이상이면 추가로 받을 수 없다")
    public void if_dealer_card_sum_over17_stop(){
        int score = 18;
        boolean result = gameService.isHit(score);
        assertThat(result).isFalse();
    }
}
