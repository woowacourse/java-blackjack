package blackjack.model.participants;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import blackjack.model.state.BlackJack;
import blackjack.model.state.Bust;
import blackjack.model.state.Stand;
import blackjack.model.state.State;
import blackjack.vo.Money;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {
    private Player player;
    private Cards comparisonCards;

    @BeforeEach
    void setUp() {
        player = new Player("ella");
        List<Card> initialPlayerCards = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.SIX, CardShape.CLOVER)
        );
        player.addCards(initialPlayerCards);
        player.betMoney(new Money(1000));

        comparisonCards = new Cards(List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.SIX, CardShape.CLOVER)
        ));
    }

    @DisplayName("플레이어 이름은 공백일 수 없다")
    @Test
    void validateName() {
        String emptyName = "";

        assertThatThrownBy(() -> new Player(emptyName)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어는 베팅을 할 수 있다")
    @Test
    void betMoney() {
        Player player = new Player("daon");
        player.betMoney(new Money(3000));

        assertThat(player).extracting("betAmount").isEqualTo(new Money(3000));
    }

    @DisplayName("플레이어가 카드 한장을 지급받는다")
    @Test
    void addCard() {
        player.addCard(new Card(CardNumber.FIVE, CardShape.DIAMOND));

        assertThat(player.getCards().getCards()).hasSize(3);
    }

    @DisplayName("카드 점수가 기준 점수보다 낮으면 카드를 추가로 받을 수 있다")
    @Test
    void checkDrawCardState() {
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("카드 점수가 기준점수보다 크면 카드를 추가로 받을 수 없다")
    @Test
    void checkDrawCardStateOverWinningScore() {
        player.addCard(new Card(CardNumber.TEN, CardShape.SPADE));

        assertThat(player.canHit()).isFalse();
    }

    @DisplayName("플레이어 상태가 bust이면 베팅 금액을 잃는다")
    @Test
    void evaluateProfitBust() {
        Card card = new Card(CardNumber.TEN, CardShape.SPADE);
        player.addCard(card);
        State comparisonState = new Bust(comparisonCards);

        Money profit = player.evaluateProfit(comparisonState);

        assertThat(profit).isEqualTo(new Money(-1000));
    }

    @DisplayName("플레이어 카드가 기준 점수보다 낮고 비교 카드가 bust이면 베팅 금액만큼 얻는다")
    @Test
    void compareScore2() {
        Card card = new Card(CardNumber.TEN, CardShape.DIAMOND);
        comparisonCards.add(card);
        player.finishTurn();
        State comparisonState = new Bust(comparisonCards);

        Money profit = player.evaluateProfit(comparisonState);

        assertThat(profit).isEqualTo(new Money(1000));
    }

    @DisplayName("플레이어 카드와 비교 카드 점수가 기준 점수보다 낮으면 두 점수의 대소관계로 수익이 정해진다")
    @ParameterizedTest
    @CsvSource(value = {"ACE,TWO,-1000", "THREE,TWO,1000", "FOUR,FOUR,0"})
    void compareScore3(CardNumber cardNumber, CardNumber otherNumber, int expected) {
        player.addCard(new Card(cardNumber, CardShape.HEART));
        player.finishTurn();
        comparisonCards.add(new Card(otherNumber, CardShape.HEART));
        State comparisionState = new Stand(comparisonCards);

        Money profit = player.evaluateProfit(comparisionState);

        assertThat(profit).isEqualTo(new Money(expected));
    }

    @DisplayName("플레이어만 블랙잭이면 베팅 금액의 1.5배를 얻는다")
    @Test
    void winBlackJack() {
        List<Card> comparison = List.of(
                new Card(CardNumber.ACE, CardShape.HEART),
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.EIGHT, CardShape.SPADE)
        );
        Cards comparisonCards = new Cards(comparison);

        Player player = new Player("ella");
        List<Card> given = List.of(
                new Card(CardNumber.ACE, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        player.addCards(given);
        player.betMoney(new Money(1000));
        State comparisionState = new Stand(comparisonCards);

        Money profit = player.evaluateProfit(comparisionState);

        assertThat(profit).isEqualTo(new Money(1500));
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭이면 베팅 금액을 돌려받는다")
    @Test
    void pushWithBlackJack() {
        List<Card> comparison = List.of(
                new Card(CardNumber.ACE, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        Cards comparisonCards = new Cards(comparison);

        Player player = new Player("daon");
        List<Card> given = List.of(
                new Card(CardNumber.ACE, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        player.addCards(given);
        player.betMoney(new Money(1000));
        State comparisionState = new BlackJack(comparisonCards);

        Money profit = player.evaluateProfit(comparisionState);

        assertThat(profit).isEqualTo(new Money(0));
    }
}
