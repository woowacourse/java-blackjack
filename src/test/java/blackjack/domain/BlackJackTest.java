package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.game.BlackJack;
import blackjack.domain.game.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        //given
        final BlackJack blackJack = new BlackJack(List.of(new Name("a"), new Name("b"), new Name("c")), new RandomDeck());
        final Dealer dealer = blackJack.getDealer();
        final Cards cards = dealer.getCards();
        final List<Card> cardsData = cards.getCards();

        //when
        final int cardSize = cardsData.size();

        //then
        assertThat(cardSize).isEqualTo(2);
    }

    @Test
    @DisplayName("이름과 덱을 건내주면 blackJack은 해당 이름을 가진 유저에게 덱에서 카드 한 장을 뽑아서 준다.")
    void giveCardTest() {
        //given
        final Name 푸우 = new Name("푸우");
        final BlackJack blackJack = new BlackJack(List.of(푸우), new TestDeck(Arrays.asList(10, 3, 10, 10)));

        //when
        //testDeck은 기본적으로 Heart Shape 카드를 준다.
        blackJack.giveCard(푸우, new TestDeck(Arrays.asList(2)));
        final User pooh = blackJack.getUsers().finUserByName(푸우);
        final Cards poohCards = pooh.getCards();
        final List<Card> 푸우카드 = poohCards.getCards();

        final boolean contains = 푸우카드.contains(new Card(Shape.HEART, CardNumber.TWO));

        //then
        assertTrue(contains);
    }

    @Test
    @DisplayName("딜러의 최종 결론을 낸다면 17이상 혹은 버스트 상태가 되며 해당 상태까지 가가위해 뽑은 카드 수를 반환한다.")
    void finalizeDealerTest() {
        //given
        final Name 푸우 = new Name("푸우");
        final BlackJack blackJack = new BlackJack(List.of(푸우), new TestDeck(Arrays.asList(10, 3, 2, 2))); // 푸우는 10, 3 카드 딜러는 2, 2 카드를 갖고 있다.

        //when
        //딜러는 2,2 카드를 쥔 상태에서 2,2,2,2,2,2,1 카드를 얻어서 17이상 조건을 만족한다.
        final int dealerAdditionCardCount = blackJack.giveCardToDealerUntilDontNeed(new TestDeck(Arrays.asList(2, 2, 2, 2, 2, 2, 1)));

        //then
        assertThat(dealerAdditionCardCount)
                .isEqualTo(7);
    }

    @Test
    @DisplayName("블랙잭에게 이름을 기반으로 해당 유저의 버스트 여부를 알 수 있다.")
    void checkBustByTest() {
        //given
        final Name 푸우 = new Name("푸우");
        final Name 헙크 = new Name("헙크");

        Integer[] 푸우카드 = {10, 10};
        Integer[] 헙크카드 = {1, 10};
        Integer[] 딜러카드 = {1, 2};

        final List<Integer> 테스트덱 = new ArrayList<>(Arrays.asList(푸우카드));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(헙크카드)));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(딜러카드)));

        final BlackJack blackJack = new BlackJack(List.of(푸우, 헙크), new TestDeck(테스트덱));

        //when
        blackJack.giveCard(푸우, new TestDeck(Arrays.asList(2)));

        //then
        assertAll(
                () -> assertThat(blackJack.isBust(푸우)).isTrue(),
                () -> assertThat(blackJack.isBust(헙크)).isFalse()
        );
    }

    @Test
    @DisplayName("BlackJack을 통해 승리한 유저, 무승부인 유저, 패배한 유저를 얻을 수 있다.")
    void blackJackResultTest() {
        //gien
        final Name 승리자 = new Name("Win");
        final Name 비긴자 = new Name("Draw");
        final Name 패배자 = new Name("Lose");

        Integer[] 승리자카드 = {1, 10};
        Integer[] 비긴자카드 = {10, 10};
        Integer[] 패배자카드 = {10, 9};
        Integer[] 딜러카드 = {10, 10};

        final ArrayList<Integer> 테스트덱 = new ArrayList<>(Arrays.asList(승리자카드));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(비긴자카드)));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(패배자카드)));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(딜러카드)));

        final BlackJack blackJack = new BlackJack(List.of(승리자, 비긴자, 패배자), new TestDeck(테스트덱));

        //when
        final List<User> winUser = blackJack.getUserOf(Result.WIN);
        final List<User> DrawUser = blackJack.getUserOf(Result.DRAW);
        final List<User> loseUser = blackJack.getUserOf(Result.LOSE);

        //then
        assertAll(
                () -> assertThat(winUser).allSatisfy((user) -> user.isNameOf(new Name("Win"))),
                () -> assertThat(DrawUser).allSatisfy((user) -> user.isNameOf(new Name("Draw"))),
                () -> assertThat(loseUser).allSatisfy((user) -> user.isNameOf(new Name("Lose")))
        );
    }

    @Test
    @DisplayName("모든 유저의 총 결과를 알 수 있다.")
    void getAllUserResultTest() {
        final Name 블랙잭승리 = new Name("BJWin");
        final Name 승리자 = new Name("Win");
        final Name 비긴자 = new Name("Draw");
        final Name 패배자 = new Name("Lose");

        Integer[] 블랙잭승리카드 = {1, 10};
        Integer[] 승리자카드 = {10, 10};
        Integer[] 패배자카드 = {10, 8};
        Integer[] 비긴자카드 = {10, 9};
        Integer[] 딜러카드 = {10, 9};

        final ArrayList<Integer> 테스트덱 = new ArrayList<>(Arrays.asList(블랙잭승리카드));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(승리자카드)));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(패배자카드)));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(비긴자카드)));
        테스트덱.addAll(new ArrayList<>(Arrays.asList(딜러카드)));

        final BlackJack blackJack = new BlackJack(List.of(블랙잭승리, 승리자, 비긴자, 패배자), new TestDeck(테스트덱));

        final Map<Result, List<User>> allUsersResult = blackJack.getAllUsersResult();
        assertAll(() -> {
                    assertThat(allUsersResult.get(Result.BLACK_JACK_WIN)).size().isEqualTo(1);
                },
                () -> {
                    assertThat(allUsersResult.get(Result.WIN)).size().isEqualTo(1);
                },
                () -> {
                    assertThat(allUsersResult.get(Result.DRAW)).size().isEqualTo(1);

                },
                () -> {
                    assertThat(allUsersResult.get(Result.LOSE)).size().isEqualTo(1);
                }
        );
    }
}
