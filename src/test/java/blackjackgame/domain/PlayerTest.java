package blackjackgame.domain;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardValue;
import blackjackgame.domain.card.Symbol;

class PlayerTest {
    Card ace = Card.of(Symbol.SPADE, CardValue.ACE);
    Card five = Card.of(Symbol.SPADE, CardValue.FIVE);
    Card nine = Card.of(Symbol.SPADE, CardValue.NINE);
    Card ten = Card.of(Symbol.SPADE, CardValue.JACK);
/*
    static Stream<Arguments> cardDummy() {
        Card ace = Card.of(Symbol.SPADE, CardValue.ACE);
        Card five = Card.of(Symbol.SPADE, CardValue.FIVE);
        Card nine = Card.of(Symbol.SPADE, CardValue.NINE);
        Card ten = Card.of(Symbol.SPADE, CardValue.JACK);

        return Stream.of(
            Arguments.arguments(new ArrayList<>(List.of(ace, ten)), Collections.emptyList(), 21),
            Arguments.arguments(new ArrayList<>(List.of(ace, five)), Collections.emptyList(), 16),
            Arguments.arguments(new ArrayList<>(List.of(five, ace)), List.of(ace), 17),
            Arguments.arguments(new ArrayList<>(List.of(ten, ten)), List.of(ace), 21),
            Arguments.arguments(new ArrayList<>(List.of(ten, nine)), List.of(five), 24),
            Arguments.arguments(new ArrayList<>(List.of(ace, nine)), List.of(ace, five), 16),
            Arguments.arguments(new ArrayList<>(List.of(five, nine)), List.of(ace, nine), 24)
        );
    }

    @DisplayName("플레이어(게스트, 딜러) 생성시 2개의 카드를 갖는다.")
    @Test
    void Should_ConstructPlayer_WhenWithTwoCard() {
        assertDoesNotThrow(() -> new Guest(new Name("guestName"), List.of(five, ten)));
        assertDoesNotThrow(() -> new Dealer(List.of(five, ten)));
    }

    @DisplayName("플레이어(게스트, 딜러) 생성시 3개의 카드를 받으면 오류가 난다.")
    @Test
    void Should_ThrowException_WhenWithThreeCard() {
        assertThatThrownBy(() -> new Guest(new Name("guestName"), new ArrayList<>(List.of(five, ten, ace))))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("시작시 카드는 두장만 분배되어야 합니다.");

        assertThatThrownBy(() -> new Dealer(new ArrayList<>(List.of(five, ten, ace))))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("시작시 카드는 두장만 분배되어야 합니다.");
    }

    @DisplayName("참여자가 카드를 추가하면 가지고 있는 카드의 개수가 늘어난다")
    @Test
    void Should_SizePlusOne_When_AddCard() {
        Player player = new Guest(new Name("guestName"), new ArrayList<>(List.of(five, nine)));
        player.addCard(ace);
        player.addCard(ace);
        player.addCard(ten);

        assertThat(player.getSize()).isEqualTo(5);
    }

    @DisplayName("플레이어가 카드 중에 에이스가 있을때 총합이 10을 넘지 않으면 총합에 10을 더한다.")
    @ParameterizedTest(name = "플레이어가 가진 카드의 합은 {2}이다.")
    @MethodSource("cardDummy")
    void Should_ReturnScore_When_Request(List<Card> initCards, List<Card> additionCards, int expected) {
        Player player = new Dealer(initCards);
        for (Card card : additionCards) {
            player.addCard(card);
        }

        assertThat(player.getScore()).isEqualTo(expected);
    }*/
}
