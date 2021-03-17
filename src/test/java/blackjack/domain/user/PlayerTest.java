package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;
import blackjack.domain.money.Money;
import blackjack.domain.state.BasicState;
import blackjack.domain.state.BlackJack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Card one = Card.of("J", "클로버");
    private Card two = Card.of("5", "하트");
    private UserDeck userDeck = new UserDeck();

    {
        userDeck.add(one);
        userDeck.add(two);
    }

    @Test
    @DisplayName("플레이어 점수 테스트")
    void getPlayerPoint() {
        String name = "Sorong";
        Player player = new Player(name, userDeck, new Money(0));
        int predictScore = 15;

        int playerScore = player.getPoint();

        assertThat(playerScore).isEqualTo(predictScore);
    }

    @Test
    @DisplayName("플레이어 드로우 성공 테스트")
    void getAvailableDraw() {
        String name = "Sorong";
        Player player = new Player(name, userDeck, new Money(0));

        boolean actualDraw = player.isAvailableDraw();

        assertThat(actualDraw).isTrue();
    }

    @Test
    @DisplayName("플레이어 드로우 실패 테스트")
    void getUnavailableDraw() {
        String name = "Sorong";
        Card card3 = Card.of("J", "다이아몬드");
        Player player = new Player(name, userDeck, new Money(0));
        player.draw(card3);

        boolean actualDraw = player.isAvailableDraw();

        assertThat(!actualDraw).isTrue();
    }

    @Test
    @DisplayName("플레이어 블랙잭 체크")
    void playerBlackJack() {
        int betMoney = 1000;
        UserDeck blackJackUserDeck = new UserDeck();
        blackJackUserDeck.add(Card.of("J", "하트"));
        blackJackUserDeck.add(Card.of("A", "하트"));
        Player player = new Player("sorong", userDeck, new Money(betMoney));
        Card dealerCard = Card.of("J", "클로버");
        UserDeck dealerDeck = new UserDeck();
        dealerDeck.add(dealerCard);
        Dealer dealer = new Dealer(dealerDeck);

        String playerResult = player.betResult(dealer)
            .getResult();
        Money money = player.getMoney();

        assertThat(playerResult).isEqualTo("승");
        assertThat(money.getValue()).isEqualTo(betMoney + (int )BlackJack.BLACK_JACK_RATE * betMoney);
    }

    @Test
    @DisplayName("플레이어 승리 체크")
    void playerWin() {
        int betMoney = 1000;
        Player player = new Player("sorong", userDeck, new Money(betMoney));
        Card dealerCard = Card.of("J", "클로버");
        UserDeck dealerDeck = new UserDeck();
        dealerDeck.add(dealerCard);
        Dealer dealer = new Dealer(dealerDeck);

        String playerResult = player.betResult(dealer)
            .getResult();
        Money money = player.getMoney();

        assertThat(playerResult).isEqualTo("승");
        assertThat(money.getValue()).isEqualTo(betMoney + (int) BasicState.NORMAL_RATE * betMoney);
    }

    @Test
    @DisplayName("플레이어 딜러 블랙잭 체크")
    void playerDealerBlackJack() {
        int betMoney = 1000;
        UserDeck blackJackUserDeck = new UserDeck();
        blackJackUserDeck.add(Card.of("J", "하트"));
        blackJackUserDeck.add(Card.of("A", "하트"));
        Player player = new Player("sorong", blackJackUserDeck, new Money(betMoney));
        UserDeck dealerDeck = new UserDeck();
        dealerDeck.add(Card.of("Q", "다이아몬드"));
        dealerDeck.add(Card.of("A", "하트"));
        Dealer dealer = new Dealer(dealerDeck);

        String playerResult = player.betResult(dealer)
            .getResult();
        Money money = player.getMoney();

        assertThat(playerResult).isEqualTo("무");
        assertThat(money.getValue()).isEqualTo(betMoney);
    }

    @Test
    @DisplayName("플레이어 무승부시 승패와 체크")
    void playerTie() {
        int betMoney = 1000;
        Player player = new Player("sorong", userDeck, new Money(betMoney));
        Card dealerCard = Card.of("J", "클로버");
        Card dealerCard2 = Card.of("5", "하트");
        UserDeck dealerDeck = new UserDeck();
        dealerDeck.add(dealerCard);
        dealerDeck.add(dealerCard2);
        Dealer dealer = new Dealer(dealerDeck);

        String playerResult = player.betResult(dealer)
            .getResult();
        Money money = player.getMoney();

        assertThat(playerResult).isEqualTo("무");
        assertThat(money.getValue()).isEqualTo(betMoney);
    }

    @Test
    @DisplayName("플레이어 버스트 패배 체크")
    void playerBurst() {
        int betMoney = 1000;
        Card card3 = Card.of("J", "다이아몬드");
        userDeck.add(card3);
        Player player = new Player("sorong", userDeck, new Money(betMoney));
        Card dealerCard = Card.of("J", "클로버");
        UserDeck dealerDeck = new UserDeck();
        dealerDeck.add(dealerCard);
        Dealer dealer = new Dealer(dealerDeck);

        String playerResult = player.betResult(dealer)
            .getResult();
        Money money = player.getMoney();

        assertThat(playerResult).isEqualTo("패");
        assertThat(money.getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어 패배 체크")
    void playerLose() {
        int betMoney = 1000;
        Player player = new Player("sorong", userDeck, new Money(betMoney));
        Card dealerCard = Card.of("J", "클로버");
        Card dealerCard2 = Card.of("K", "하트");
        UserDeck dealerDeck = new UserDeck();
        dealerDeck.add(dealerCard);
        dealerDeck.add(dealerCard2);
        Dealer dealer = new Dealer(dealerDeck);

        String playerResult = player.betResult(dealer)
            .getResult();
        Money money = player.getMoney();

        assertThat(playerResult).isEqualTo("패");
        assertThat(money.getValue()).isEqualTo(0);
    }
}
