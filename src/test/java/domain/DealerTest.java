package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.*;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    private final Deck deck = new Deck(new RandomCardsGenerator());

    @DisplayName("플레이어에게 카드를 할당할 수 있다.")
    @Test
    void 플레이어에게_카드_할당() {
        //given
        Card card = new Card(CardNumber.A, CardShape.CLOVER);
        Deck deck = new Deck(() -> new ArrayList<>(List.of(card)));

        Dealer dealer = new Dealer(Cards.empty(), deck);
        Player player = Player.init(new Nickname("플레이어"));

        //when
        dealer.giveOneCardTo(player);
        List<Card> cards = player.getCards();

        //then
        assertThat(cards).contains(card);
    }

    @DisplayName("딜러 카드의 합이 17 이상이 될 때까지 뽑은 횟수를 반환한다")
    @Test
    void 딜러가_뽑은_횟수_검증() {
        //given
        Card card1 = new Card(CardNumber.A, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.A, CardShape.HEART);
        Card card3 = new Card(CardNumber.A, CardShape.CLOVER);
        Deck deck = new Deck(() -> new ArrayList<>(List.of(card1, card2, card3)));

        Cards dealerCards = Cards.of(new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        )));
        Dealer dealer = new Dealer(dealerCards, deck);

        //when
        final int count = dealer.drawCards();

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(count).isEqualTo(2);
            softly.assertThat(dealer.getScore().value()).isEqualTo(17);
        });
    }

    @DisplayName("승/패/무승부가 명확한 경우, 게임 결과를 올바르게 반환할 수 있다.")
    @ParameterizedTest
    @MethodSource("createGameResultCase")
    void 게임_결과_반환(List<Card> playerCard, List<Card> dealerCard, GameResult expected) {
        // given
        Cards playerCards = Cards.of(playerCard);
        Cards dealerCards = Cards.of(dealerCard);
        Player player = Player.from(new Nickname("player"), playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = dealer.getResult(player);

        // then
        assertThat(actual).isSameAs(expected);
    }

    private static Stream createGameResultCase() {
        return Stream.of(
                Arguments.of(
                        // 21
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER)
                        ),
                        // 12
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        "WIN"
                ),
                Arguments.of(
                        // 12
                        List.of(
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        // 13
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        "LOSE"
                ),
                Arguments.of(
                        // 22
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        // 22
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        "LOSE"
                ),
                Arguments.of(
                        // 20
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER)
                        ),
                        // 20
                        List.of(
                                new Card(CardNumber.JACK, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER)
                        ),
                        "DRAW"
                )
        );
    }

    @DisplayName("플레이어와 딜러 모두 버스트가 아닌 경우, 합이 더 큰 사람이 승리한다")
    @Test
    void 승패_검증() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.THREE, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from(new Nickname("player"), playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = dealer.getResult(player);

        //then
        assertThat(actual).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어의 카드가 버스트인 경우 패배한다")
    @Test
    void 플레이어의_카드가_버스트인_경우_패배한다() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from(new Nickname("player"), playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = dealer.getResult(player);

        //then
        assertThat(actual).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러가 버스트인 경우, 버스트가 아닌 플레이어는 승리한다")
    @Test
    void 딜러가_버스트인_경우_승리한다() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.A, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from(new Nickname("player"), playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = dealer.getResult(player);

        //then
        assertThat(actual).isEqualTo(GameResult.WIN);
    }

    @DisplayName("모두 버스트가 아니면서 합이 같으면 비긴다")
    @Test
    void 두명_모두_버스트가_아니면서_합이_같으면_비긴다() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from(new Nickname("player"), playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = dealer.getResult(player);

        //then
        assertThat(actual).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("딜러와 플레이어 둘 다 버스트인 경우 플레이어는 패배한다")
    @Test
    void 둘_다_버스트인_경우_패배한다() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from(new Nickname("player"), playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = dealer.getResult(player);

        //then
        assertThat(actual).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("카드가 2장이면서 합이 21인 경우 블랙잭으로 판단할 수 있다.")
    @ParameterizedTest
    @MethodSource("createBlackjackCards")
    void 블랙잭_판단(List<Card> inputCards) {
        // given
        Cards cards = Cards.of(inputCards);
        Dealer dealer = new Dealer(cards, deck);

        // when
        final boolean isBlackjack = dealer.isBlackjack();

        // then
        assertThat(isBlackjack).isTrue();
    }

    private static Stream createBlackjackCards() {
        return Stream.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.TEN, CardShape.HEART)
                ),
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.KING, CardShape.HEART)
                ),
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.QUEEN, CardShape.HEART)
                ),
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.JACK, CardShape.HEART)
                )
        );
    }

    @DisplayName("블랙잭이 아닌 경우를 판단할 수 있다.")
    @ParameterizedTest
    @MethodSource("createNotBlackjackCards")
    void 블랙잭_아닌_경우_판단(List<Card> inputCards) {
        // given
        Cards cards = Cards.of(inputCards);
        Dealer dealer = new Dealer(cards, deck);

        // when
        final boolean isBlackjack = dealer.isBlackjack();

        // then
        assertThat(isBlackjack).isFalse();
    }

    private static Stream createNotBlackjackCards() {
        return Stream.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.TEN, CardShape.HEART),
                        new Card(CardNumber.FIVE, CardShape.CLOVER)
                ),
                List.of(
                        new Card(CardNumber.QUEEN, CardShape.CLOVER),
                        new Card(CardNumber.SIX, CardShape.HEART),
                        new Card(CardNumber.FIVE, CardShape.DIAMOND)
                ),
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.A, CardShape.HEART),
                        new Card(CardNumber.TEN, CardShape.DIAMOND),
                        new Card(CardNumber.NINE, CardShape.SPADE)
                ),
                List.of(
                        new Card(CardNumber.TWO, CardShape.CLOVER),
                        new Card(CardNumber.THREE, CardShape.HEART),
                        new Card(CardNumber.TWO, CardShape.SPADE),
                        new Card(CardNumber.FIVE, CardShape.HEART),
                        new Card(CardNumber.EIGHT, CardShape.SPADE)
                )
        );
    }
}
