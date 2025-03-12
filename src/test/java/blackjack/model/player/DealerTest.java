package blackjack.model.player;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import java.util.List;
import java.util.stream.Stream;
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

    private static Stream<Arguments> 자신이_카드를_더_뽑을_수_있는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SIX)
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        false
                )
        );
    }

    private static Stream<Arguments> 비겼는지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        false
                )
        );
    }

    private static Stream<Arguments> 이겼는지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        true
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
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
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
                        false
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
                        true
                )
        );
    }

    private static Dealer makeDealer(final BlackJackCards blackJackCards) {
        Dealer dealer = new Dealer(new DefaultCardDeckInitializer());
        dealer.receiveCards(blackJackCards);
        return dealer;
    }

    private static Player makeUser(final String name, final BlackJackCards blackJackCards) {
        Player player = new Player(name);
        player.receiveCards(blackJackCards);
        return player;
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

    //
    //    public void drawMoreCard(final BlackJackPlayer blackJackPlayer) {
    //        if (canDrawMoreCard(blackJackPlayer)) {
    //            blackJackPlayer.receiveCards(drawCard(SINGLE_DRAW_AMOUNT));
    //            return;
    //        }
    //        throw new IllegalStateException("카드를 더 뽑을 수 없습니다.");
    //    }

    static Stream<Arguments> 블랙잭_플레이어가_카드를_더_뽑을_수_있는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Dealer(new DefaultCardDeckInitializer()),
                        new FakeBlackJackPlayer("pobi", true),
                        true
                ),
                Arguments.of(
                        new Dealer(new DefaultCardDeckInitializer()),
                        new FakeBlackJackPlayer("pobi", false),
                        false
                )
        );
    }

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new DefaultCardDeckInitializer());
    }

    @Test
    void 게임_시작시_자신과_플레이어들에게_카드_두장을_나눠준다() {
        CardDeck cardDeck = new CardDeck(new BlackJackCards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR),
                        createCard(CardNumber.FIVE))
        ));
        Dealer dealer = new Dealer("딜러", cardDeck);
        Player player = new Player("pobi");

        dealer.dealInitialCards(List.of(player));

        assertThat(dealer.getCards().getValues()).hasSize(2);
        assertThat(player.getCards().getValues()).hasSize(2);
    }

    @ParameterizedTest
    @MethodSource("블랙잭_플레이어가_카드를_더_뽑을_수_있는지_반환한다_테스트_케이스")
    void 블랙잭_플레이어가_카드를_더_뽑을_수_있는지_반환한다(final Dealer dealer, final BlackJackPlayer blackJackPlayer,
                                       final boolean expected) {
        assertThat(dealer.canDrawMoreCard(blackJackPlayer)).isEqualTo(expected);
    }

    @Test
    void 블랙잭_플레이어의_카드를_더_뽑는다() {
        Dealer dealer = new Dealer(new DefaultCardDeckInitializer());
        BlackJackPlayer blackJackPlayer = new FakeBlackJackPlayer("pobi", true);

        dealer.drawMoreCard(blackJackPlayer);

        assertThat(blackJackPlayer.getCards().getValues()).hasSize(1);
    }

    @Test
    void 블랙잭_플레이어가_카드를_더_뽑을_수_없는_경우_예외를_던진다() {
        Dealer dealer = new Dealer(new DefaultCardDeckInitializer());
        BlackJackPlayer blackJackPlayer = new FakeBlackJackPlayer("pobi", false);

        assertThatIllegalStateException()
                .isThrownBy(() -> dealer.drawMoreCard(blackJackPlayer));
    }

    @ParameterizedTest
    @MethodSource("자신의_카드를_뽑는다_테스트_케이스")
    void 자신의_카드를_뽑는다(final Dealer dealer, final boolean expected) {

        boolean actual = dealer.drawSelf();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        dealer.receiveCards(new BlackJackCards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(dealer.getCards().getValues()).hasSize(3);
    }

    @Test
    void 자신의_최저_포인트를_계산한다() {
        dealer.receiveCards(new BlackJackCards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.ACE))
        ));

        assertThat(dealer.getMinimumPoint()).isEqualTo(16);
    }

    @ParameterizedTest
    @MethodSource("최초로_받은_카드를_오픈한다_테스트_케이스")
    void 최초로_받은_카드를_오픈한다(final BlackJackCards blackJackCards, final BlackJackCards expected) {

        dealer.receiveCards(blackJackCards);

        assertThat(dealer.openInitialCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("자신이_카드를_더_뽑을_수_있는지_반환한다_테스트_케이스")
    void 자신이_카드를_더_뽑을_수_있는지_반환한다(final BlackJackCards blackJackCards, final boolean expected) {

        dealer.receiveCards(blackJackCards);

        assertThat(dealer.canDrawMoreCard()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("비겼는지_확인한다_테스트_케이스")
    void 비겼는지_확인한다(final BlackJackCards blackJackCards, final Player player, final boolean expected) {
        dealer.receiveCards(blackJackCards);

        assertThat(dealer.isDraw(player)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("이겼는지_확인한다_테스트_케이스")
    void 이겼는지_확인한다(final BlackJackCards blackJackCards, final Player player, final boolean expected) {
        dealer.receiveCards(blackJackCards);

        assertThat(dealer.isWin(player)).isEqualTo(expected);
    }

    private static class FakeBlackJackPlayer extends BlackJackPlayer {

        private final boolean canDrawMoreCard;

        public FakeBlackJackPlayer(final String name, final boolean canDrawMoreCard) {
            super(name);
            this.canDrawMoreCard = canDrawMoreCard;
        }

        @Override
        public BlackJackCards openInitialCards() {
            throw new IllegalStateException("이 메서드는 테스트할 수 없습니다.");
        }

        @Override
        protected boolean canDrawMoreCard() {
            return canDrawMoreCard;
        }
    }
}
