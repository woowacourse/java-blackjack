package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.CardFactory;
import domain.card.Cards;
import domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PlayerTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Player 생성 시 이름 인자의 null, empty 체크")
    void nullAndEmptyTest(String input) {
        assertThatThrownBy(() -> new Player(input)).
            isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("초기 2장의 카드 받기 테스트")
    void receiveFirstCards() {
        Deck deck = CardFactory.createDeck();
        Player player = new Player("pobi");
        player.receiveFirstCards(deck);
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("한 장의 카드를 더 받기")
    void receiveCard() {
        Deck deck = CardFactory.createDeck();
        Player player = new Player("pobi");
        player.receiveFirstCards(deck);
        int sizeBeforeReceiveCard = player.getCards().size();
        player.receiveCard(deck);
        assertThat(player.getCards().size()).isEqualTo(sizeBeforeReceiveCard + 1);
    }

    @Test
    @DisplayName("카드를 더 받을수 없는 상태인지 잘 파악하는지 테스트")
    void cannotReceiveCard() {
        Deck deck = CardFactory.createDeck();
        Player player = new Player("pobi");
        player.receiveFirstCards(deck);
        while (!player.isLargerThan(Cards.BLACKJACK_SCORE)) {
            player.receiveCard(deck);
        }
        assertThat(player.canReceiveCard()).isFalse();
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
