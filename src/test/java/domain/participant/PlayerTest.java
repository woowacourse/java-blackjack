package domain.participant;

import domain.game.BettingMoney;
import domain.playingcard.Deck;
import domain.playingcard.PlayingCard;
import domain.playingcard.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.playingcard.PlayingCardShape.*;
import static domain.playingcard.PlayingCardValue.*;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerTest {

    @DisplayName("플레이어의 이름을 입력하면 참가자 인스턴스를 생성한다.")
    @Test
    void createPlayerTest() {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        BettingMoney bettingMoney = new BettingMoney(10000);

        // When
        Player player = Player.of(playerName, bettingMoney, Deck.init(PlayingCards.getValue()));

        // Then
        assertThat(player).isNotNull();
    }

    @DisplayName("플레이어가 카드를 뽑을 수 있는 상태이면 true를 반환한다.")
    @Test
    void isDrawableTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, KING));
        Hand hand = new Hand(playingCards);
        Player player = new Player(new PlayerName("kelly"), new BettingMoney(10000), hand);

        // When
        Boolean isBust = player.isDrawable();

        // Then
        assertThat(isBust).isTrue();
    }

    @DisplayName("플레이어는 덱으로부터 카드를 한 장 뽑는다.")
    @Test
    void drawTest() {
        // Given
        Deck deck = Deck.init(PlayingCards.getValue());
        Hand initHand = Hand.init();
        Player player = new Player(new PlayerName("kelly"), new BettingMoney(10000), initHand);
        int initHandCardsCount = player.getHandCards().size();

        // When
        player.draw(deck);

        // Then
        assertThat(player.getHandCards().size()).isEqualTo(initHandCardsCount + 1);
    }
    
    @DisplayName("딜러와 플레이어의 승부 결과에 따른 수익을 반환한다.")
    @MethodSource("calculateRevenueTestCase")
    @ParameterizedTest        
    void calculateRevenueTest(final List<PlayingCard> playingCards, final int expect) {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        BettingMoney bettingMoney = new BettingMoney(10000);
        Player kelly = new Player(playerName, bettingMoney, new Hand(playingCards));
        Dealer dealer = new Dealer(new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(CLOVER, FIVE))));

        // When
        int revenue = kelly.calculateRevenue(dealer);

        // Then
        assertThat(revenue).isEqualTo(expect);
    }

    private Stream<Arguments> calculateRevenueTestCase() {
        return Stream.of(
                Arguments.of(List.of(new PlayingCard(DIAMOND, ACE), new PlayingCard(HEART, TEN)), 15000),
                Arguments.of(List.of(new PlayingCard(DIAMOND, NINE), new PlayingCard(HEART, TEN)), 10000),
                Arguments.of(List.of(new PlayingCard(SPADE, FIVE), new PlayingCard(HEART, TEN)), 0),
                Arguments.of(List.of(new PlayingCard(SPADE, TWO), new PlayingCard(HEART, THREE)), -10000)
        );
    }
}
