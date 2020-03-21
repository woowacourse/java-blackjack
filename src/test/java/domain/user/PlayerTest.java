package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Cards;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PlayerTest {
    private Player normalPlayer = new Player("히히", 500_000);

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Player 생성 시 이름 인자의 null, empty 체크")
    void nullAndEmptyTest(String name) {
        assertThatThrownBy(() -> new Player(name, 100_000))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("초기 2장의 카드 받기 테스트")
    void receiveFirstCards() {
        Deck deck = CardFactory.createDeck();

        normalPlayer.receiveFirstCards(deck);
        assertThat(normalPlayer.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("한 장의 카드를 더 받기")
    void receiveCard() {
        Deck deck = CardFactory.createDeck();
        normalPlayer.receiveFirstCards(deck);
        int sizeBeforeReceiveCard = normalPlayer.getCards().size();
        normalPlayer.receiveCard(deck);
        assertThat(normalPlayer.getCards().size()).isEqualTo(sizeBeforeReceiveCard + 1);
    }

    @Test
    @DisplayName("카드를 더 받을수 없는 상태인지 잘 파악하는지 테스트")
    void cannotReceiveCard() {
        Deck deck = CardFactory.createDeck();
        normalPlayer.receiveFirstCards(deck);
        while (!normalPlayer.isLargerThan(Cards.BLACKJACK_SCORE)) {
            normalPlayer.receiveCard(deck);
        }
        assertThat(normalPlayer.canReceiveCard()).isFalse();
    }

    private static List<Card> makeCardList(Card card1, Card card2, Card card3) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        return cards;
    }

    private static List<Card> makeCardList(Card card1, Card card2) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return cards;
    }

    @Test
    @DisplayName("결과계산: 두명의 점수가 같은 경우 무승부")
    void calculateResultWhenScoresSame() {
        Dealer dealer = new Dealer();
        List<Card> deckForTest = new ArrayList<>();

        deckForTest.add(new Card(Symbol.ACE, Type.HEART));
        dealer.receiveCard(new Deck(deckForTest));

        deckForTest.add(new Card(Symbol.ACE, Type.HEART));
        normalPlayer.receiveCard(new Deck(deckForTest));

        assertThat(normalPlayer.calculateResult(dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("결과계산: 두명의 점수가 모두 21을 넘기는 경우")
    void calculateResultWhenBothOverBlackJack() {
        Dealer dealer = new Dealer();

        List<Card> deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
            new Card(Symbol.JACK, Type.HEART), new Card(Symbol.KING, Type.HEART));
        dealer.receiveFirstCards(new Deck(deckForTest));
        dealer.receiveCard(new Deck(deckForTest));

        deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
            new Card(Symbol.JACK, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        normalPlayer.receiveFirstCards(new Deck(deckForTest));
        normalPlayer.receiveCard(new Deck(deckForTest));

        assertThat(normalPlayer.calculateResult(dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("결과계산: 플레이어만 21을 넘기는 경우")
    void testWhenOnlyPlayerOverBlackJack() {
        Dealer dealer = new Dealer();

        List<Card> deckForTest
            = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.JACK, Type.HEART));
        dealer.receiveFirstCards(new Deck(deckForTest));

        deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
            new Card(Symbol.JACK, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        normalPlayer.receiveFirstCards(new Deck(deckForTest));
        normalPlayer.receiveCard(new Deck(deckForTest));

        assertThat(normalPlayer.calculateResult(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("결과계산: 딜러만 21을 넘기는 경우")
    void testWhenOnlyDealerOverBlackJack() {
        Dealer dealer = new Dealer();
        List<Card> deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
            new Card(Symbol.JACK, Type.HEART), new Card(Symbol.NINE, Type.HEART));

        dealer.receiveFirstCards(new Deck(deckForTest));
        dealer.receiveCard(new Deck(deckForTest));

        deckForTest
            = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.JACK, Type.HEART));
        normalPlayer.receiveFirstCards(new Deck(deckForTest));

        assertThat(normalPlayer.calculateResult(dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("결과계산: 딜러와 플레이어 둘 다 21이하인 경우")
    void testWhenBothLowerThanBlackJack() {
        Dealer dealer = new Dealer();
        List<Card> deckForTest
            = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        dealer.receiveFirstCards(new Deck(deckForTest));

        deckForTest
            = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.JACK, Type.HEART));
        normalPlayer.receiveFirstCards(new Deck(deckForTest));

        assertThat(normalPlayer.calculateResult(dealer)).isEqualTo(Result.WIN);
    }
}
