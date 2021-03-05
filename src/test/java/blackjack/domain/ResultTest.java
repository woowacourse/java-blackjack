package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @DisplayName("버스트가 존재하는지 확인한다. - 있는 경우")
    @Test
    public void isExistBustTrue() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(Result.isExistBust(dealer, player)).isTrue();
    }

    @DisplayName("버스트가 존재하는지 확인한다. - 없는 경우")
    @Test
    public void isExistBustFalse() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(Result.isExistBust(dealer, player)).isFalse();
    }

    @DisplayName("딜러에 버스트가 있고 사용자에 버스트가 없는 경우를 확인한다. - 사용자 승")
    @Test
    public void decideByBustPlayerWin() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(Result.decideByBust(dealer, player)).isEqualTo(Result.WIN);
    }

    @DisplayName("딜러에 버스트가 있고 사용자에도 버스트가 있는 경우를 확인한다. - 무승부")
    @Test
    public void decideByBustDraw() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));

        assertThat(Result.decideByBust(dealer, player)).isEqualTo(Result.STANDOFF);
    }

    @DisplayName("딜러에 버스트가 없고 사용자에 버스트가 있는 경우를 확인한다. - 사용자 패")
    @Test
    public void decideByBustPlayerLose() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));

        assertThat(Result.decideByBust(dealer, player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("결과를 뒤집는다. (ex. 승을 패로, 패를 승으로)")
    @Test
    public void reverse() {
        Result result = Result.WIN;

        assertThat(result.reverse(result)).isEqualTo(Result.LOSE);
    }

    @DisplayName("각 객체는 리스트 안에 자신이 몇 개가 있는지 계산한다.")
    @Test
    public void count() {
        Result win = Result.WIN;
        List<Result> results = Arrays.asList(Result.WIN, Result.WIN, Result.STANDOFF, Result.LOSE);

        assertThat(win.count(results)).isEqualTo(2);
    }
}
