//package blackjack.domain.user;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//import blackjack.domain.card.Card;
//import blackjack.domain.card.UserDeck;
//import blackjack.domain.result.BlackjackResult;
////import blackjack.domain.result.Result;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//public class ResultTest {
//
//    private final Card one = new Card("J", "클로버");
//    private final Card two = new Card("5", "하트");
//    private final UserDeck userDeck = new UserDeck();
//
//    {
//        userDeck.draw(one);
//        userDeck.draw(two);
//    }
//
//    private final Money money = new Money(100);
//
//    @Test
//    @DisplayName("플레이어 승리 체크")
//    void playerWin() {
//        Player player = new Player(userDeck, "sorong", money);
//
//        Card dealerCard = new Card("J", "클로버");
//        UserDeck dealerDeck = new UserDeck();
//        dealerDeck.draw(dealerCard);
//        Dealer dealer = new Dealer(dealerDeck);
//
//        assertThat(Result.getResult(player, dealer)).isEqualTo(BlackjackResult.WIN);
//    }
//
//    @Test
//    @DisplayName("플레이어 무승부 체크")
//    void playerTie() {
//        Player player = new Player(userDeck, "sorong", money);
//
//        Card dealerCard = new Card("J", "클로버");
//        Card dealerCard2 = new Card("5", "하트");
//        UserDeck dealerDeck = new UserDeck();
//        dealerDeck.draw(dealerCard);
//        dealerDeck.draw(dealerCard2);
//        Dealer dealer = new Dealer(dealerDeck);
//
//        assertThat(Result.getResult(player, dealer)).isEqualTo(BlackjackResult.TIE);
//    }
//
//    @Test
//    @DisplayName("플레이어 버스트 패배 체크")
//    void playerBurst() {
//        Card card3 = new Card("J", "다이아몬드");
//        userDeck.draw(card3);
//        Player player = new Player(userDeck, "sorong", money);
//
//        Card dealerCard = new Card("J", "클로버");
//        UserDeck dealerDeck = new UserDeck();
//        dealerDeck.draw(dealerCard);
//        Dealer dealer = new Dealer(dealerDeck);
//
//        assertThat(Result.getResult(player, dealer)).isEqualTo(BlackjackResult.BUST);
//    }
//
//    @Test
//    @DisplayName("플레이어 패배 체크")
//    void playerLose() {
//        Player player = new Player(userDeck, "sorong", money);
//
//        Card dealerCard = new Card("J", "클로버");
//        Card dealerCard2 = new Card("K", "하트");
//        UserDeck dealerDeck = new UserDeck();
//        dealerDeck.draw(dealerCard);
//        dealerDeck.draw(dealerCard2);
//        Dealer dealer = new Dealer(dealerDeck);
//
//        assertThat(Result.getResult(player, dealer)).isEqualTo(BlackjackResult.LOSE);
//    }
//}
