package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Cards;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("초기 2장의 카드 받기 테스트")
    void receiveFirstCards() {
        Deck deck = CardFactory.createDeck();
        Dealer dealer = new Dealer();
        dealer.receiveFirstCards(deck);
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("입력된 스코어가 딜러의 스코어와 같은지 테스트")
    void isScoreSame() {
        Dealer dealer = new Dealer();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Symbol.THREE, Type.SPADE));
        cards.add(new Card(Symbol.TWO, Type.SPADE));

        dealer.receiveCard(new Deck(cards));
        dealer.receiveCard(new Deck(cards));

        assertThat(dealer.isScoreSame(5)).isTrue();
    }

    @Test
    @DisplayName("카드를 더 받을수 없는 상태인지 잘 파악하는지 테스트")
    void cannotReceiveCard() {
        Deck deck = CardFactory.createDeck();
        Dealer dealer = new Dealer();
        dealer.receiveFirstCards(deck);
        while (!dealer.isLargerThan(Cards.MAX_SUM_FOR_DEALER_MORE_CARD)) {
            dealer.receiveCard(deck);
        }
        assertThat(dealer.canReceiveCard()).isFalse();
    }

    @Test
    @DisplayName("카드를 더 받을수 있는 상태인지를 잘 파악하는지 테스트")
    void canReceiveCard() {
        Deck deck = CardFactory.createDeck();
        Dealer dealer = new Dealer();
        dealer.receiveFirstCards(deck);
        assertThat(dealer.canReceiveCard()).isTrue();
    }
}
