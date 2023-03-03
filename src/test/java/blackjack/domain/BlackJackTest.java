package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BlackJackTest {

    @Test
    @DisplayName("블랙잭은 시작과 동시에 유저들에게 카드 2장씩 배분한다.")
    void blackJackInitializeTest() {
        final BlackJack blackJack = new BlackJack(List.of(new Name("a"), new Name("b"), new Name("c")), new RandomDeck());
        final List<User> users = blackJack.getUsers().getUsers();

        for (User user : users) {
            assertThat(user.getCards().getCards().size() == 2).isTrue();
        }
    }

    @Test
    @DisplayName("블랙잭은 시작과 동시에 딜러에게 카드 2장을 배분한다.")
    void blackJackInitializeTest2() {
        final BlackJack blackJack = new BlackJack(List.of(new Name("a"), new Name("b"), new Name("c")), new RandomDeck());

        assertThat(blackJack)
                .extracting("dealer")
                .extracting("cards")
                .extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size()
                .isEqualTo(2);
    }

    @Test
    @DisplayName("이름과 덱을 건내주면 blackJack은 해당 이름을 가진 유저에게 덱에서 카드 한 장을 뽑아서 준다.")
    void giveCardTest() {
        final Name 푸우 = new Name("푸우");
        final BlackJack blackJack = new BlackJack(List.of(푸우), new TestDeck(Arrays.asList(10, 3, 10, 10)));
        blackJack.giveCard(푸우, new TestDeck(Arrays.asList(2)));

        final List<Card> 푸우카드 = blackJack.getUsers().getCardsOf(푸우);

        assertThat(푸우카드.contains(new Card(Shape.HEART, CardNumber.of(2)))).isTrue();
    }

    @Test
    @DisplayName("딜러의 최종 결론을 낸다면 17이상 혹은 버스트 상태가 되며 해당 상태까지 가가위해 뽑은 카드 수를 반환한다.")
    void finalizeDealerTest() {
        final Name 푸우 = new Name("푸우");
        final BlackJack blackJack = new BlackJack(List.of(푸우), new TestDeck(Arrays.asList(10, 3, 2, 2)));
        assertThat(blackJack.finalizeDealer(new TestDeck(Arrays.asList(2, 2, 2, 2, 2, 2, 1))))
                .isEqualTo(7);
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 있는 상태라면 Dealer객체를 가져오지 못한다.")
    void getDealerStatusTest() {
        final Name 푸우 = new Name("푸우");

        Integer[] 푸우카드 = {1, 2};
        Integer[] 딜러카드 = {2, 2};

        final ArrayList<Integer> 테스트덱 = new ArrayList<>(Arrays.asList(푸우카드));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(딜러카드)));

        final BlackJack blackJack = new BlackJack(List.of(푸우), new TestDeck(테스트덱));
        assertThatThrownBy(() -> {
            blackJack.getDealer();
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러가 아직 카드의 결론이 나지 않았습니다.");
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 없는 상태라면 Dealer객체를 가져올 수 있다.")
    void getDealerStatusTest2() {
        final Name 푸우 = new Name("푸우");

        Integer[] 푸우카드 = {1, 2};
        Integer[] 딜러카드 = {10, 10};

        final ArrayList<Integer> 테스트덱 = new ArrayList<>(Arrays.asList(푸우카드));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(딜러카드)));

        final BlackJack blackJack = new BlackJack(List.of(푸우), new TestDeck(테스트덱));


        assertDoesNotThrow(() -> blackJack.getDealer());
    }

    @Test
    @DisplayName("딜러의 첫 번째 카드를 가져올 수 있다.")
    void getDealerFirstCardTest() {
        final Name 푸우 = new Name("푸우");

        Integer[] 푸우카드 = {1, 2};
        Integer[] 딜러카드 = {1, 2};

        final ArrayList<Integer> 테스트덱 = new ArrayList<>(Arrays.asList(푸우카드));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(딜러카드)));

        final BlackJack blackJack = new BlackJack(List.of(푸우), new TestDeck(테스트덱));

        assertThat(blackJack.getDealerFirstCard().equals(new Card(Shape.HEART, CardNumber.of(1)))).isTrue();
    }

    @Test
    @DisplayName("블랙잭에게 이름을 기반으로 해당 유저의 버스트 여부를 알 수 있다.")
    void checkBustByTest() {
        final Name 푸우 = new Name("푸우");
        final Name 헙크 = new Name("헙크");

        Integer[] 푸우카드 = {10, 10};
        Integer[] 헙크카드 = {1, 10};
        Integer[] 딜러카드 = {1, 2};

        final ArrayList<Integer> 테스트덱 = new ArrayList<>(Arrays.asList(푸우카드));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(헙크카드)));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(딜러카드)));

        final BlackJack blackJack = new BlackJack(List.of(푸우, 헙크), new TestDeck(테스트덱));

        blackJack.giveCard(푸우, new TestDeck(Arrays.asList(2)));

        assertAll(
                () -> assertThat(blackJack.checkBustBy(푸우)).isTrue(),
                () -> assertThat(blackJack.checkBustBy(헙크)).isFalse()
        );
    }
}
