package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {

    final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
            new Card(CardShape.CLOVER, CardNumber.TEN),
            new Card(CardShape.CLOVER, CardNumber.NINE),
            new Card(CardShape.HEART, CardNumber.JACK),
            new Card(CardShape.HEART, CardNumber.NINE),
            new Card(CardShape.DIAMOND, CardNumber.FOUR));

    @Test
    @DisplayName("유저들의 현 패를 반환하는 기능 테스트")
    void getUsersStatus() {
        final Users users = new Users(List.of("필립", "홍실")
                , new Deck(new TestDeckGenerator(testCards)));

        List<Card> initialCards = users.getStatus().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());
        assertThat(initialCards).containsExactlyInAnyOrderElementsOf(testCards);
    }

    @Test
    @DisplayName("유저들의 첫 패를 반환하는 기능 테스트")
    void getUsersInitialStatus() {
        final Users users = new Users(List.of("필립", "홍실")
                , new Deck(new TestDeckGenerator(testCards)));

        List<Card> initialCards = users.getInitialStatus().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());
        assertThat(initialCards).containsExactlyInAnyOrderElementsOf(testCards.subList(0, 5));
    }

    @Test
    @DisplayName("딜러의 스코어가 16이 넘는지 확인하는 기능 테스트")
    void isDealerOverDrawLimitTest() {
        final Users users = new Users(List.of("필립"), new Deck(new TestDeckGenerator(testCards)));

        boolean dealerOverDrawLimit = users.isDealerOverDrawLimit();

        assertThat(dealerOverDrawLimit).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드를 하나 추가하는 기능 테스트")
    void drawDealerTest() {
        final Deck deck = new Deck(new TestDeckGenerator(testCards));
        final Users users = new Users(List.of("필립"), deck);

        users.drawDealer(deck);
        int dealerCardCount = users.getStatus().get("딜러").size();

        assertThat(dealerCardCount).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 리스트를 반환하는 기능 테스트")
    void getPlayersTest() {
        final Users users = new Users(List.of("필립"), new Deck(new RandomDeckGenerator()));

        final List<String> players = users.getPlayerNames();

        assertThat(players).containsExactly("필립");
    }
}
