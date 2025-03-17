package blackjack.model.blackjack_player.dealer;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.blackjack_player.Hand;
import blackjack.model.blackjack_player.player.Player;
import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    private Dealer dealer;

    private static Stream<Arguments> 최초로_받은_카드를_오픈한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE))),
                        new BlackJackCards(List.of(createCard(CardNumber.TEN)))
                ),
                Arguments.of(
                        new BlackJackCards(List.of(createCard(CardNumber.NINE), createCard(CardNumber.FIVE),
                                createCard(CardNumber.TWO))),
                        new BlackJackCards(List.of(createCard(CardNumber.NINE)))
                )
        );
    }

    private static Stream<Arguments> 자신의_카드를_뽑는다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        makeDealer(new BlackJackCards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.SIX)))),
                        true
                ),
                Arguments.of(
                        makeDealer(new BlackJackCards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.SEVEN),
                                createCard(CardNumber.FOUR)))),
                        false
                )
        );
    }

    private static Stream<Arguments> 플레이어와_싸운다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        0,
                        0
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        1,
                        -1
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.ACE)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.THREE)
                                        )
                                )
                        ),
                        1,
                        -1
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.THREE)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.NINE),
                                                createCard(CardNumber.ACE)
                                        )
                                )
                        ),
                        1,
                        -1
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.FIVE)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.NINE),
                                                createCard(CardNumber.SIX),
                                                createCard(CardNumber.SEVEN)
                                        )
                                )
                        ),
                        -1,
                        1
                )
        );
    }

    private static Dealer makeDealer(final BlackJackCards blackJackCards) {
        Dealer dealer = new Dealer(new DefaultCardDeckInitializer());
        dealer.getAllCards().addAll(blackJackCards);
        return dealer;
    }

    private static Player makeUser(final String name, final BlackJackCards blackJackCards) {
        Player player = new Player(name, 1);
        player.receiveCards(blackJackCards);
        return player;
    }

    private static Stream<Arguments> 플레이어의_보상을_계산한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.ACE)
                                )
                        ),
                        10,
                        15
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.JACK),
                                        createCard(CardNumber.ACE)
                                )
                        ),
                        10,
                        15
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.FIVE)
                                )
                        ),
                        10,
                        10
                )
        );
    }

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new DefaultCardDeckInitializer());
    }

    @Test
    void 자신의_시작_카드_두_장_뽑아_저장한다() {
        dealer.dealStartingHand();

        assertThat(dealer.getAllCards().getValues()).hasSize(2);
    }

    @Test
    void 플레이어들에게_나눠_줄_시작_카드를_두_장_뽑는다() {
        CardDeck cardDeck = new CardDeck(new BlackJackCards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR),
                        createCard(CardNumber.FIVE))
        ));
        Dealer dealer = new Dealer(Hand.empty(), cardDeck);

        assertThat(dealer.drawPlayerStartingCards().getValues()).hasSize(2);
    }

    @Test
    void 블랙잭_플레이어의_카드를_더_뽑는다() {
        Dealer dealer = new Dealer(new DefaultCardDeckInitializer());

        assertThat(dealer.drawPlayerCards().getValues()).hasSize(1);
    }

    @ParameterizedTest
    @MethodSource("자신의_카드를_뽑는다_테스트_케이스")
    void 자신의_카드를_뽑는다(final Dealer dealer, final boolean expected) {

        boolean actual = dealer.dealSelf();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 자신의_카드를_한_장_뽑는다() {
        dealer.dealSelf();

        assertThat(dealer.getAllCards().getValues()).hasSize(1);
    }

    @ParameterizedTest
    @MethodSource("최초로_받은_카드를_오픈한다_테스트_케이스")
    void 최초로_받은_카드를_오픈한다(final BlackJackCards blackJackCards, final BlackJackCards expected) {

        dealer.getAllCards().addAll(blackJackCards);

        assertThat(dealer.openCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("플레이어와_싸운다_테스트_케이스")
    void 플레이어와_싸운다(
            final BlackJackCards blackJackCards,
            final Player player,
            final int expectedDealerMoney,
            final int expectedPlayerMoney
    ) {
        dealer.getAllCards().addAll(blackJackCards);

        dealer.fight(player);
        int dealerProfit = (-1) * player.getProfit();

        SoftAssertions.assertSoftly(
                softly -> {
                    softly.assertThat(dealerProfit).isEqualTo(expectedDealerMoney);
                    softly.assertThat(player.getProfit()).isEqualTo(expectedPlayerMoney);
                }
        );
    }

    @ParameterizedTest
    @MethodSource("플레이어의_보상을_계산한다_테스트_케이스")
    void 플레이어의_보상을_계산한다(final BlackJackCards blackJackCards, final int bettingMoney, final int expected) {
        Player player = new Player("player", bettingMoney);
        player.receiveCards(blackJackCards);

        assertThat(dealer.calculatePlayerReward(player)).isEqualTo(expected);
    }
}
