package domain.participant;

import domain.blackjack.BetAmount;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlayersTest {

    private static final int MIN_PARTICIPANT_COUNT = 2;
    private static final int MAX_PARTICIPANT_COUNT = 8;

    @DisplayName("9명 이상 입력할 경우 예외를 발생시킨다.")
    @Test
    void validateMaxSize() {
        List<String> names = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        List<Player> players = names.stream().map(name -> new Player(new Name(name), new BetAmount(1000))).toList();

        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("최소 %d명 최대 %d명까지 입력받을 수 있습니다.", MIN_PARTICIPANT_COUNT, MAX_PARTICIPANT_COUNT));
    }

    @DisplayName("1명 이하 입력할 경우 예외를 발생시킨다.")
    @Test
    void validateMinSize() {
        List<Player> names = List.of(new Player(new Name("one"), new BetAmount(1000)));

        Assertions.assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("최소 %d명 최대 %d명까지 입력받을 수 있습니다.", MIN_PARTICIPANT_COUNT, MAX_PARTICIPANT_COUNT));
        ;
    }

    @DisplayName("중복된 이름이 있을 경우 예외를 발생시킨다.")
    @Test
    void validateDuplicateNames() {
        List<Player> names = List.of(new Player(new Name("pobi"), new BetAmount(1000)), new Player(new Name("pobi"), new BetAmount(1000)));

        Assertions.assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복될 수 없습니다.");
    }

    @DisplayName("딜러의 수익을 계산한다.")
    @Test
    void getDealerPayout() {
        Player one = new Player(new Name("one"), new BetAmount(10_000));
        one.receiveCard(new Card(Shape.CLOVER, Rank.TEN));
        one.receiveCard(new Card(Shape.DIA, Rank.JACK));

        Player two = new Player(new Name("two"), new BetAmount(15_000));
        two.receiveCard(new Card(Shape.DIA, Rank.KING));

        Player three = new Player(new Name("three"), new BetAmount(10_000));
        three.receiveCard(new Card(Shape.SPADE, Rank.KING));
        three.receiveCard(new Card(Shape.SPADE, Rank.ACE));

        Players players = new Players(List.of(one, two, three));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.JACK));
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.NINE));

        double payout = players.getDealerPayout(dealer);

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }
}
