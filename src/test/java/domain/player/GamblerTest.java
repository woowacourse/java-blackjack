package domain.player;

import domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GamblerTest {
    @Test
    @DisplayName("참가자 이름과 배팅액 정보를 이용해 다수의 참가자 객체를 만들 수 있다.")
    void generateMultipleGamblers() {
        Map<Name, Bet> nameAndBet = Map.of(
                Name.of("여우"), Bet.from(3000),
                Name.of("아벨"), Bet.from(6000),
                Name.of("폴로"), Bet.from(90000)
        );
        List<Gambler> gamblers = Gambler.from(nameAndBet);

        assertThat(gamblers).hasSize(3)
                .contains(new Gambler(Hand.withEmptyHolder(), Name.of("여우"), Bet.from(3000)))
                .contains(new Gambler(Hand.withEmptyHolder(), Name.of("아벨"), Bet.from(6000)))
                .contains(new Gambler(Hand.withEmptyHolder(), Name.of("폴로"), Bet.from(90000)));
    }
}