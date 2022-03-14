package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;
import static blackjack.utils.CardCreationUtil.createCardList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.RandomCardsGenerateStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {

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

    @ParameterizedTest
    @MethodSource("supplyDealerCardNotMoreThen16")
    @DisplayName("딜러는 카드의 수가 16이하 일 때 한 장의 카드를 더 받는다")
    void dealerReceiveCard(LinkedList<Card> cards) {

        Dealer dealer = new Dealer();

        for (Card card : cards) {
            dealer.receiveCard(card);
        }

        assertThat(dealer.shouldReceive()).isTrue();
    }

    private static Stream<Arguments> supplyDealerCardNotMoreThen16() {
        return Stream.of(
                Arguments.of(createCardList(JACK, SIX)),
                Arguments.of(createCardList(JACK, SIX)),
                Arguments.of(createCardList(ACE, ACE, FOUR)),
                Arguments.of(createCardList(JACK, ACE, ACE, TWO)),
                Arguments.of(createCardList(JACK, FIVE))
        );
    }

    @ParameterizedTest
    @MethodSource("supplyDealerCardMoreThen17")
    @DisplayName("딜러는 카드의 수가 17이상일 떄 카드를 받을 수 없다")
    void dealerCannotReceiveCard(LinkedList<Card> cards) {

        Dealer dealer = new Dealer();

        for (Card card : cards) {
            dealer.receiveCard(card);
        }

        assertThat(dealer.shouldReceive()).isFalse();
    }

    private static Stream<Arguments> supplyDealerCardMoreThen17() {
        return Stream.of(
                Arguments.of(createCardList(ACE, SIX)),
                Arguments.of(createCardList(ACE, ACE, FIVE)),
                Arguments.of(createCardList(ACE, ACE, ACE, FOUR)),
                Arguments.of(createCardList(ACE, KING, NINE, TWO)),
                Arguments.of(createCardList(KING, SEVEN)),
                Arguments.of(createCardList(KING, EIGHT))
        );
    }

    @Test
    @DisplayName("딜러는 자신의 카드 한장을 정상적으로 오픈 하는지 확인")
    void openDealerCard() {
        List<Card> cards = new ArrayList<>();
        Card excepted = new Card(JACK, DIAMOND);
        Card card = new Card(ACE, DIAMOND);

        cards.add(excepted);
        cards.add(card);

        Dealer dealer = new Dealer();
        dealer.receiveCard(excepted);
        dealer.receiveCard(card);

        dealer.selfDraw();
        dealer.selfDraw();

        assertThat(dealer.getOpenCard()).isEqualTo(excepted);
    }

    @Test
    @DisplayName("플레이어들에게 2장씩 기본 카드 세팅을 해주는 확인")
    void drawCardToPlayers() {
        List<Player> players = new ArrayList<>();
        Player playerA = new Player("승팡");
        Player playerB = new Player("필즈");
        players.add(playerA);
        players.add(playerB);

        Dealer dealer = new Dealer();

        dealer.drawCardToPlayers(players);
        assertAll(
                () -> assertThat(playerA.getCards().size()).isEqualTo(2),
                () -> assertThat(playerB.getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("플레이어에게 카드 한장을 정상적으로 주는지 확인")
    void giveCard() {
        List<Card> cards = new ArrayList<>();
        Card excepted = new Card(JACK, DIAMOND);
        cards.add(excepted);
        cards.add(new Card(ACE, DIAMOND));

        Dealer dealer = new Dealer(new Deck(new RandomCardsGenerateStrategy()));
        Player player = new Player("승팡");

        dealer.giveCard(player);
        assertThat(player.getCards().size()).isEqualTo(1);
    }
}
