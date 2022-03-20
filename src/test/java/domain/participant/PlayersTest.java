package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.Symbol;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class PlayersTest {
    private Map<Name, String> nameAndMoneys;

    @BeforeEach
    void setUp() {
        nameAndMoneys = new LinkedHashMap<>();
        nameAndMoneys.put(Name.from("kun"), "1000");
        nameAndMoneys.put(Name.from("runa"), "2000");
    }

    @Test
    @DisplayName("입력 받은 플레이어 이름만큼 Player 객체가 잘 만들어졌는지 확인한다.")
    void playersCreateTest() {
        Players players = Players.of(nameAndMoneys);

        assertThat(players.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("입력 받은 플레이어 이름이 쉼표만 있을 경우 에러를 발생시킨다.")
    void playersCommaErrorTest() {
        nameAndMoneys.put(Name.from(""), "3000");
        nameAndMoneys.put(Name.from("   "), "4300");

        assertThatThrownBy(() -> Players.of(nameAndMoneys))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }

    @Test
    @DisplayName("첫 턴에서 모든 참가자가 카드를 두개씩 뽑는지 확인")
    void initialTurnTest() {
        Players players = Players.of(nameAndMoneys);
        Deck deck = Deck.initDeck(Card.values());

        players.runInitialTurn(deck);
        int actual = (int) players.getPlayers().stream()
            .filter(participant -> participant.getCards().size() == 2)
            .count();

        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("Players의 이름이 잘 변환이 되는지 확인한다.")
    void toNamesTest() {

        Players players = Players.of(nameAndMoneys);
        List<String> playerNames = players.toNames();

        assertThat(playerNames).contains("kun");
        assertThat(playerNames).contains("runa");
    }

    @Test
    @DisplayName("Player들이 딜러와 비교한 수익이 잘 계산이 되는지 확인한다.")
    void calculateIncomesTest() {
        Players players = Players.of(nameAndMoneys);
        Dealer dealer = new Dealer();

        Deck kunDeck = Deck.initDeck(List.of(Card.valueOf(Symbol.HEART, Denomination.ACE)));
        Deck runaDeck = Deck.initDeck(List.of(Card.valueOf(Symbol.CLOVER, Denomination.SEVEN)));
        Deck dealerDeck = Deck.initDeck(List.of(Card.valueOf(Symbol.DIAMOND, Denomination.TEN)));

        players.getPlayers().get(0).hit(kunDeck);
        players.getPlayers().get(1).hit(runaDeck);
        dealer.hit(dealerDeck);

        assertThat(players.calculateIncomes(dealer)).contains(1000);
        assertThat(players.calculateIncomes(dealer)).contains(-2000);


    }

}