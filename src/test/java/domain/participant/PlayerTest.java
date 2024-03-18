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
        Hand initHand = Hand.init(deck);
        Player player = new Player(new PlayerName("kelly"), new BettingMoney(10000), initHand);
        int initHandCardsCount = player.getHandCards().size();

        // When
        player.draw(deck);

        // Then
        assertThat(player.getHandCards().size()).isEqualTo(initHandCardsCount + 1);
    }

    @DisplayName("플레이어가 블랙잭으로 승리하면 배팅 금액의 1.5배 수익을 반환한다.")
    @Test
    void calculateRevenueWithBlackjackWinTest() {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        BettingMoney bettingMoney = new BettingMoney(10000);
        Hand playerHand = new Hand(List.of(new PlayingCard(DIAMOND, ACE), new PlayingCard(HEART, QUEEN)));
        Player kelly = new Player(playerName, bettingMoney, playerHand);
        Dealer dealer = new Dealer(new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(CLOVER, FIVE))));

        // When
        int revenue = kelly.calculateRevenue(dealer);

        // Then
        assertThat(revenue).isEqualTo(15000);
    }

    @DisplayName("플레이어가 승리하면 배팅 금액 만큼 수익을 반환한다.")
    @MethodSource("winTestCase")
    @ParameterizedTest(name = "[{index}] {0}")
    void calculateRevenueWithBlackjackWinTest(final String description, final List<PlayingCard> dealerPlayingCards, final List<PlayingCard> playerPlayingCards) {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        BettingMoney bettingMoney = new BettingMoney(10000);
        Player kelly = new Player(playerName, bettingMoney, new Hand(playerPlayingCards));
        Dealer dealer = new Dealer(new Hand(dealerPlayingCards));

        // When
        int revenue = kelly.calculateRevenue(dealer);

        // Then
        assertThat(revenue).isEqualTo(10000);
    }

    private Stream<Arguments> winTestCase() {
        return Stream.of(
                Arguments.of(
                        "딜러가 버스트",
                        List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(HEART, TEN), new PlayingCard(CLOVER, NINE)),
                        List.of(new PlayingCard(DIAMOND, TWO), new PlayingCard(HEART, THREE))
                ),
                Arguments.of(
                        "플레이어의 손패합이 우위",
                        List.of(new PlayingCard(DIAMOND, THREE), new PlayingCard(HEART, TEN)),
                        List.of(new PlayingCard(DIAMOND, NINE), new PlayingCard(SPADE, TEN))
                )
        );
    }

    @DisplayName("플레이어가 딜러와 무승부면 0원의 수익을 반환한다.")
    @MethodSource("drawTestCase")
    @ParameterizedTest(name = "[{index}] {0}")
    void calculateRevenueWithDrawTest(final String description, final List<PlayingCard> dealerPlayingCards, final List<PlayingCard> playerPlayingCards) {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        BettingMoney bettingMoney = new BettingMoney(10000);
        Player kelly = new Player(playerName, bettingMoney, new Hand(playerPlayingCards));
        Dealer dealer = new Dealer(new Hand(dealerPlayingCards));

        // When
        int revenue = kelly.calculateRevenue(dealer);

        // Then
        assertThat(revenue).isEqualTo(0);
    }

    private Stream<Arguments> drawTestCase() {
        return Stream.of(
                Arguments.of(
                        "플레이어와 딜러가 모두 블랙잭",
                        List.of(new PlayingCard(DIAMOND, ACE), new PlayingCard(HEART, TEN)),
                        List.of(new PlayingCard(SPADE, ACE), new PlayingCard(HEART, KING))
                ),
                Arguments.of(
                        "플레이어와 딜러의 손패합이 같음",
                        List.of(new PlayingCard(DIAMOND, THREE), new PlayingCard(HEART, FIVE)),
                        List.of(new PlayingCard(DIAMOND, FOUR), new PlayingCard(SPADE, FOUR))
                )
        );
    }

    @DisplayName("플레이어가 패배하면 배팅 금액 만큼 손실을 입는다.")
    @MethodSource("loseTestCase")
    @ParameterizedTest(name = "[{index}] {0}")
    void calculateRevenueWithLoseTest(final String description, final List<PlayingCard> dealerPlayingCards, final List<PlayingCard> playerPlayingCards) {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        BettingMoney bettingMoney = new BettingMoney(10000);
        Player kelly = new Player(playerName, bettingMoney, new Hand(playerPlayingCards));
        Dealer dealer = new Dealer(new Hand(dealerPlayingCards));

        // When
        int revenue = kelly.calculateRevenue(dealer);

        // Then
        assertThat(revenue).isEqualTo(-10000);
    }

    private Stream<Arguments> loseTestCase() {
        return Stream.of(
                Arguments.of(
                        "플레이어가 버스트",
                        List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(HEART, FIVE)),
                        List.of(new PlayingCard(SPADE, QUEEN), new PlayingCard(HEART, KING), new PlayingCard(CLOVER, EIGHT))
                ),
                Arguments.of(
                        "딜러가 블랙잭이면서 플레이어가 블랙잭이 아님",
                        List.of(new PlayingCard(DIAMOND, QUEEN), new PlayingCard(HEART, ACE)),
                        List.of(new PlayingCard(DIAMOND, EIGHT), new PlayingCard(SPADE, EIGHT), new PlayingCard(CLOVER, FIVE))
                ),
                Arguments.of(
                        "딜러의 손패합이 우위",
                        List.of(new PlayingCard(DIAMOND, QUEEN), new PlayingCard(HEART, NINE)),
                        List.of(new PlayingCard(DIAMOND, TWO), new PlayingCard(SPADE, FOUR))
                )
        );
    }
}
