package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.Deck;
import blackjack.domain.card.JustBlackjackDeck;
import blackjack.domain.card.JustTenSpadeDeck;
import blackjack.domain.card.JustTwoSpadeDeck;
import blackjack.domain.card.RandomDeck;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParticipantTest {

    @Test
    @DisplayName("Player 클래스는 이름을 입력받으면 정상적으로 생성된다.")
    void create_player() {
        Name name = new Name("aki");
        Deck deck = new RandomDeck();
        BetMoney betMoney = new BetMoney(10);

        assertThatCode(() -> new Participant(name, deck, betMoney)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Participant는 딜러가 아니다.")
    void check_dealer() {
        Deck deck = new RandomDeck();
        BetMoney betMoney = new BetMoney(10);

        Player participant = new Participant(new Name("alien"), deck, betMoney);

        assertThat(participant.isDealer()).isFalse();
    }

    @Test
    @DisplayName("배팅 금액을 반환한다.")
    void get_bet_money() {
        Deck deck = new RandomDeck();
        int money = 10;
        BetMoney inputBetMoney = new BetMoney(money);
        Participant participant = new Participant(new Name("alien"), deck, inputBetMoney);

        BetMoney betMoney = participant.getBetMoney();

        assertThat(betMoney).isEqualTo(inputBetMoney);
    }

    @ParameterizedTest(name = "{displayName} : {2}")
    @MethodSource("getProfitTestSet")
    @DisplayName("승패에 따른 수익률을 계산하여 반환한다.")
    void get_profit(Participant participant, int profitMoney, String result) {
        Deck deck = new JustTenSpadeDeck();
        Dealer dealer = new Dealer(deck);

        if (participant.canHit()) {
            participant.stay();
        }
        Outcome outcome = Outcome.matchAboutPlayer(dealer, participant);
        Profit profit = participant.getProfit(outcome);

        assertThat(profit.get()).isEqualTo(profitMoney);
    }

    private static Stream<Arguments> getProfitTestSet() {
        int money = 10;
        return Stream.of(
                Arguments.of(new Participant(new Name("alien"), new JustBlackjackDeck(),
                        new BetMoney(money)), (int) (money * 1.5), "블랙잭 승리"),
                Arguments.of(new Participant(new Name("alien"), new JustTenSpadeDeck(),
                        new BetMoney(money)), 0, "무승부"),
                Arguments.of(new Participant(new Name("alien"), new JustTwoSpadeDeck(),
                        new BetMoney(money)), money * -1, "패배")
        );
    }

    @Test
    @DisplayName("canHit 메서드는 카드의 총합이 21미만이면 참이 반환된다.")
    void validate_range_true() {
        Deck deck = new JustTenSpadeDeck();
        BetMoney betMoney = new BetMoney(10);
        Player participant = new Participant(new Name("alien"), deck, betMoney);

        assertThat(participant.canHit()).isTrue();
    }
}
