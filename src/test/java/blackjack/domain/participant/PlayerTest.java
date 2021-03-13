package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.utils.CardDeck;
import blackjack.utils.FixedCardDeck;
import blackjack.domain.exception.IllegalNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {
    @Test
    @DisplayName("Player 생성후 이름 확인")
    void create1() {
        Participant player = new Player("john", new FixedCardDeck());
        assertThat(player.getName()).isEqualTo("john");
    }

    @Test
    @DisplayName("Player 생성후 이름 확인")
    void create2() {
        Participant player = new Player("sarah", new FixedCardDeck());
        assertThat(player.getName()).isEqualTo("sarah");
    }

    @Test
    @DisplayName("이름이 빈문자열이면 예외 발생 확인")
    void create4() {
        assertThatThrownBy(() -> new Player("", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("이름이 null이거나 빈 문자열일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 null이면 예외 발생 확인")
    void create5() {
        assertThatThrownBy(() -> new Player(null, new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("이름이 null이거나 빈 문자열일 수 없습니다.");
    }

    @Test
    @DisplayName("이름에 한글이 섞여있을 때 예외 발생 확인")
    void create6() {
        assertThatThrownBy(() -> new Player("john한글", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("이름은 알파벳 대소문자로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("이름애 숫자가 섞여있을 때 예외 발생 확인")
    void create7() {
        assertThatThrownBy(() -> new Player("john123", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("이름은 알파벳 대소문자로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("플레이어에게 2장 지급후 추가로 받을 수 있는지 확인")
    void isAvailableToTake() {
        final CardDeck cardDeck = new FixedCardDeck();
        List<Card> cards = cardDeck.initCards();
        Participant player = new Player("sarah", cards);
        assertThat(player.isAvailableToTake()).isEqualTo(true);
    }

    @Test
    @DisplayName("플레이어의 베팅 금액을 설정")
    void setBetMoney() {
        Player player = new Player("john", new FixedCardDeck());
        assertThatCode(() -> player.setBetMoney(1000)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("수익 계산 - Player: Bust, Dealer: Hit")
    void calculateProfit1() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("Q다이아몬드"));
        Player player = new Player("john", cards);
        player.takeCard(Card.from("2다이아몬드"));
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("Q하트"));
        Dealer dealer = new Dealer(cards);

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(-betMoney);
    }

    @Test
    @DisplayName("수익 계산 - Player: Bust, Dealer: Blackjack")
    void calculateProfit2() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("Q다이아몬드"));
        Player player = new Player("john", cards);
        player.takeCard(Card.from("2다이아몬드"));
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("A하트"));
        Dealer dealer = new Dealer(cards);

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(-betMoney);
    }

    @Test
    @DisplayName("수익 계산 - Player: Bust, Dealer: Bust")
    void calculateProfit3() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("Q다이아몬드"));
        Player player = new Player("john", cards);
        player.takeCard(Card.from("2다이아몬드"));
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("Q하트"));
        Dealer dealer = new Dealer(cards);
        dealer.takeCard(Card.from("2하트"));

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(-betMoney);
    }

    @Test
    @DisplayName("수익 계산 - Player: Blackjack, Dealer: Hit")
    void calculateProfit4() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("A다이아몬드"));
        Player player = new Player("john", cards);
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("Q하트"));
        Dealer dealer = new Dealer(cards);
        dealer.takeCard(Card.from("A하트"));

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(1.5 * betMoney);
    }

    @Test
    @DisplayName("수익 계산 - Player: Blackjack, Dealer: Bust")
    void calculateProfit5() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("A다이아몬드"));
        Player player = new Player("john", cards);
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("Q하트"));
        Dealer dealer = new Dealer(cards);
        dealer.takeCard(Card.from("2하트"));

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(1.5 * betMoney);
    }

    @Test
    @DisplayName("수익 계산 - Player: Blackjack, Dealer: Blackjack")
    void calculateProfit6() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("A다이아몬드"));
        Player player = new Player("john", cards);
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("A하트"));
        Dealer dealer = new Dealer(cards);

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(0);
    }

    @Test
    @DisplayName("수익 계산 - Player: Hit, Dealer: Blackjack")
    void calculateProfit7() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("Q다이아몬드"));
        Player player = new Player("john", cards);
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("A하트"));
        Dealer dealer = new Dealer(cards);

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(-betMoney);
    }

    @Test
    @DisplayName("수익 계산 - Player: Hit, Dealer: Bust")
    void calculateProfit8() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("Q다이아몬드"));
        Player player = new Player("john", cards);
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("Q하트"));
        Dealer dealer = new Dealer(cards);
        dealer.takeCard(Card.from("2하트"));

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(betMoney);
    }

    @Test
    @DisplayName("수익 계산 - Player: Hit > Dealer: Hit")
    void calculateProfit9() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("Q다이아몬드"));
        Player player = new Player("john", cards);
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("9하트"));
        Dealer dealer = new Dealer(cards);

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(betMoney);
    }

    @Test
    @DisplayName("수익 계산 - Player: Hit == Dealer: Hit")
    void calculateProfit10() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("Q다이아몬드"));
        Player player = new Player("john", cards);
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("Q하트"));
        Dealer dealer = new Dealer(cards);

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(0);
    }

    @Test
    @DisplayName("수익 계산 - Player: Hit < Dealer: Hit")
    void calculateProfit11() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"), Card.from("9다이아몬드"));
        Player player = new Player("john", cards);
        int betMoney = 1000;
        player.setBetMoney(betMoney);

        cards = Arrays.asList(Card.from("K하트"), Card.from("Q하트"));
        Dealer dealer = new Dealer(cards);

        assertThat(player.calculateProfitFromState(dealer)).isEqualTo(-betMoney);
    }
}
