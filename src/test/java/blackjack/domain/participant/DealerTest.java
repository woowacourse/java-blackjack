package blackjack.domain.participant;

import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.StubDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러가 정상적으로 생성되는지 확인")
    void create() {
        Dealer dealer = new Dealer(new Hand());
        assertThat(dealer).isNotNull();
    }

    @Test
    @DisplayName("딜러가 카드를 정상적으로 받는지 확인")
    void receiveCard() {
        StubDeck deck = new StubDeck(List.of(jackCard, sixCard));
        Dealer dealer = new Dealer();
        dealer.receiveCard(deck.draw());
        Card openCard = dealer.getOpenCard();

        assertThat(openCard.getDenomination()).isEqualTo("J");
    }

    @Test
    @DisplayName("딜러는 카드의 수가 16이하 일 때 한 장의 카드를 더 받는다")
    void dealerReceiveCard() {
        StubDeck deck = new StubDeck(List.of(jackCard, sixCard));
        Dealer dealer = new Dealer();
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());

        Assertions.assertThat(dealer.shouldReceive()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 수가 17이상일 떄 카드를 받을 수 없다")
    void dealerCannotReceiveCard() {
        StubDeck deck = new StubDeck(List.of(jackCard, sevenCard));
        Dealer dealer = new Dealer();
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());

        Assertions.assertThat(dealer.shouldReceive()).isFalse();
    }

    @Test
    @DisplayName("딜러는 자신의 카드 한장을 정상적으로 오픈 하는지 확인")
    void openDealerCard() {
        StubDeck deck = new StubDeck(List.of(jackCard, aceCard));
        Dealer dealer = new Dealer();
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());

        assertThat(dealer.getOpenCard()).isEqualTo(jackCard);
    }

    @Test
    @DisplayName("딜러를 생성할때 이름이 null을 포함한채 생성하면 예외발생")
    void validateNameIsNull() {
        Hand cardHand = new Hand();

        assertThatThrownBy(() -> new Dealer(null, cardHand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 이름과 카드패가 null일 수 없습니다.");
    }

    @Test
    @DisplayName("딜러를 생성할때 카드패가 null을 포함한채 생성하면 예외발생")
    void validateCardHandIsNull() {
        Name name = new Name("딜러");

        assertThatThrownBy(() -> new Dealer(name, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 이름과 카드패가 null일 수 없습니다.");
    }

    @Test
    @DisplayName("카드패의 총합이 정확한지 확인")
    void getCardTotalScore() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(aceCard);
        dealer.receiveCard(jackCard);

        assertThat(dealer.getCardTotalScore()).isEqualTo(21);
    }
}
