package blackjack.model.player;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackJackPlayerTest {

    private BlackJackPlayer blackJackPlayer;

    private static Stream<Arguments> 비겼는지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        RawBlackJackPlayer.withCards(
                                "challenger",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        RawBlackJackPlayer.withCards(
                                "challenger",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        true
                )
        );
    }

    private static Stream<Arguments> 이겼는지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        RawBlackJackPlayer.withCards(
                                "challenger",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        RawBlackJackPlayer.withCards(
                                "challenger",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        RawBlackJackPlayer.withCards(
                                "challenger",
                                new Cards(
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
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        RawBlackJackPlayer.withCards(
                                "challenger",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        RawBlackJackPlayer.withCards(
                                "challenger",
                                new Cards(
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

    private static Stream<Arguments> 최적의_포인트를_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                createCard(CardNumber.EIGHT),
                                createCard(CardNumber.ACE)
                        ),
                        19
                ),
                Arguments.of(
                        new Cards(
                                createCard(CardNumber.EIGHT),
                                createCard(CardNumber.ACE),
                                createCard(CardNumber.NINE)
                        ),
                        18
                ),
                Arguments.of(
                        new Cards(
                                createCard(CardNumber.EIGHT),
                                createCard(CardNumber.ACE),
                                createCard(CardNumber.NINE),
                                createCard(CardNumber.SIX)
                        ),
                        24
                )
        );
    }

    @BeforeEach
    void setUp() {
        blackJackPlayer = new RawBlackJackPlayer("pobi");
    }

    @Test
    void 자신의_최저_포인트를_계산한다() {
        blackJackPlayer.receiveCards(new Cards(
                List.of(createCard(CardNumber.JACK), createCard(CardNumber.QUEEN), createCard(CardNumber.ACE))
        ));

        assertThat(blackJackPlayer.getMinimumPoint()).isEqualTo(21);
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        Cards cards = new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        );
        blackJackPlayer.receiveCards(cards);

        assertThat(blackJackPlayer.getCards().getValues()).hasSize(3);
    }

    @Test
    void 자신의_모든_카드를_오픈한다() {
        Cards cards = new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        );
        blackJackPlayer.receiveCards(cards);

        assertThat(blackJackPlayer.openAllCards()).isEqualTo(cards);
    }

    @ParameterizedTest
    @MethodSource("최적의_포인트를_반환한다_테스트_케이스")
    void 최적의_포인트를_반환한다(final Cards cards, final int expected) {
        blackJackPlayer.receiveCards(cards);

        assertThat(blackJackPlayer.calculateOptimalPoint()).isEqualTo(expected);
    }

    private static class RawBlackJackPlayer extends BlackJackPlayer {

        RawBlackJackPlayer(String name) {
            super(name);
        }

        static RawBlackJackPlayer withCards(final String name, final Cards cards) {
            RawBlackJackPlayer rawPlayer = new RawBlackJackPlayer(name);
            rawPlayer.receiveCards(cards);

            return rawPlayer;
        }

        @Override
        public Cards openInitialCards() {
            throw new IllegalStateException("이 메서드는 Player 에서 테스트할 수 없습니다.");
        }

        @Override
        public boolean canDrawMoreCard() {
            throw new IllegalStateException("이 메서드는 Player 에서 테스트할 수 없습니다.");
        }
    }
}
