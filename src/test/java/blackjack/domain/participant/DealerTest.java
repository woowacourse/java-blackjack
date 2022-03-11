package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러가 정상적으로 생성되는지 확인")
    void create() {
    	Dealer dealer = new Dealer();
        assertThat(dealer).isNotNull();
    }

    @Test
    @DisplayName("딜러가 카드를 정상적으로 주는지 확인")
    void drawCard() {
        Dealer dealer = new Dealer();
        Card card = dealer.drawCard();
        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("딜러는 카드의 수가 16이하 일 때 한 장의 카드를 더 받는다")
    void dealerReceiveCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.JACK, Suit.DIAMOND));
        cards.add(new Card(Denomination.SIX, Suit.DIAMOND));

        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(deck);
        dealer.selfDraw();
        dealer.selfDraw();

        Assertions.assertThat(dealer.shouldReceive()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 수가 17이상일 떄 카드를 받을 수 없다")
    void dealerCannotReceiveCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.JACK, Suit.DIAMOND));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMOND));

        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(deck);
        dealer.selfDraw();
        dealer.selfDraw();

        Assertions.assertThat(dealer.shouldReceive()).isFalse();
    }

    @Test
    @DisplayName("딜러는 자신의 카드 한장을 정상적으로 오픈 하는지 확인")
    void openDealerCard() {
        List<Card> cards = new ArrayList<>();
        Card excepted = new Card(Denomination.JACK, Suit.DIAMOND);
        cards.add(excepted);
        cards.add(new Card(Denomination.ACE, Suit.DIAMOND));

        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(deck);
        dealer.selfDraw();
        dealer.selfDraw();

        assertThat(dealer.getOpenCard()).isEqualTo(excepted);
    }

    @Test
    @DisplayName("플레이어들에게 2장씩 기본 카드 세팅을 해주는 확인")
    void drawCardToPlayers() {
        List<Player> players = new ArrayList<>();
        Player p1 = new Player("승팡");
        Player p2 = new Player("필즈");
        players.add(p1);
        players.add(p2);

        Dealer dealer = new Dealer();

        dealer.drawCardToPlayers(players);
        assertAll(
                () -> assertThat(p1.getCards().size()).isEqualTo(2),
                () -> assertThat(p2.getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("플레이어에게 카드 한장을 정상적으로 주는지 확인")
    void giveCard() {
        List<Card> cards = new ArrayList<>();
        Card excepted = new Card(Denomination.JACK, Suit.DIAMOND);
        cards.add(excepted);
        cards.add(new Card(Denomination.ACE, Suit.DIAMOND));

        Dealer dealer = new Dealer(new Deck(cards));
        Player player = new Player("승팡");

        dealer.giveCard(player);
        assertThat(player.getCards()).containsExactly(excepted);
    }
}
