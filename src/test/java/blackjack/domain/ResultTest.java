package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @DisplayName("버스트를 확인한다. - 사용자 승")
    @Test
    public void decideBustUserWin() {
        Dealer dealer = new Dealer();
        User user = new User("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        assertThat(Result.decide(dealer, user)).isEqualTo(Result.WIN);
    }

    @DisplayName("버스트를 확인한다. - 무승부")
    @Test
    public void decideBustDraw() {
        Dealer dealer = new Dealer();
        User user = new User("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        assertThat(Result.decide(dealer, user)).isEqualTo(Result.DRAW);
    }

    @DisplayName("버스트를 확인한다. - 사용자 패")
    @Test
    public void decideBustUserLose() {
        Dealer dealer = new Dealer();
        User user = new User("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        assertThat(Result.decide(dealer, user)).isEqualTo(Result.LOSE);
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
        List<Result> results = Arrays.asList(Result.WIN, Result.WIN, Result.DRAW, Result.LOSE);

        assertThat(win.count(results)).isEqualTo(2);
    }
}
