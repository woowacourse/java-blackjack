package blackjack.domain;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BlackjackTest {

    @Test
    @DisplayName("7개 이상의 이름을 입력한 경우 예외가 발생한다.")
    void tooManyPlayers() {
        String[] names = {"a", "b", "c", "d", "e", "f", "g", "h"};
        assertThatThrownBy(() -> new Blackjack(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최대")
                .hasMessageContaining("명까지 플레이 가능합니다.");
    }

    @Test
    @DisplayName("중복되는 이름을 입력한 경우 예외가 발생한다.")
    void duplicatedName() {
        String[] names = {"pobi", "pobi"};
        assertThatThrownBy(() -> new Blackjack(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복되는 이름은 허용되지 않습니다.");
    }

    @Test
    @DisplayName("모든 플레이어는 2장의 카드로 21 이상의 값을 가질 수 없으므로 hit할 기회가 있어야 한다.")
    void playersWhoCanHit() {
        String[] names = {"pobi", "woni"};
        Blackjack blackjack = new Blackjack(names);
        blackjack.firstDistribute();

        int count=0;
        while(blackjack.getPlayerWhoCanHit()!=null){
            count++;
        }

        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("hit을 선택하면 카드 한장 추가")
    void hit(){
        String[] names = {"pobi"};
        Blackjack blackjack = new Blackjack(names);
        blackjack.firstDistribute();
        Player player = blackjack.getPlayerWhoCanHit();
        blackjack.hit(player);

        assertThat(player.getCards().size()).isEqualTo(3);
    }
}
