//package team.blackjack.domain;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class BetTest {
//    private Player player;
//    private Dealer dealer;
//
//    @BeforeEach
//    void setUp() {
//        player = new Player("pobi", 10000);
//        dealer = new Dealer();
//    }
//
//    @Test
//    void 플레이어가_패배하는_경우_반환금액은_0원이_된다() {
//        List<Card> cardsWithSum20 = List.of(Card.of(Suit.CLUBS, Rank.JACK), Card.of(Suit.DIAMONDS, Rank.KING));
//        List<Card> cardsWithSum19 = List.of(Card.of(Suit.CLUBS, Rank.JACK), Card.of(Suit.DIAMONDS, Rank.NINE));
//
//        for (Card card : cardsWithSum20) {
//            dealer.hit(card);
//        }
//        for (Card card : cardsWithSum19) {
//            player.hit(card);
//        }
//
//        int payout = player.getBet().calculatePayout();
//
//        assertThat(payout).isEqualTo(0);
//    }
//
//    @Test
//    void 플레이어가_승리하는_경우_반환금액은_배팅금액의_2배가_된다() {
//        List<Card> cardsWithSum20 = List.of(Card.of(Suit.CLUBS, Rank.JACK), Card.of(Suit.DIAMONDS, Rank.KING));
//        List<Card> cardsWithSum19 = List.of(Card.of(Suit.CLUBS, Rank.JACK), Card.of(Suit.DIAMONDS, Rank.NINE));
//
//        for (Card card : cardsWithSum19) {
//            dealer.hit(card);
//        }
//        for (Card card : cardsWithSum20) {
//            player.hit(card);
//        }
//
//        int payout = player.getBet().calculatePayout();
//
//        assertThat(payout).isEqualTo(20000);
//    }
//
//    @Test
//    void 플레이어가_블랙잭으로_승리한_경우_반환금액은_배팅금액의_2_5배가_된다() {
//        List<Card> blackjackCards = List.of(Card.of(Suit.CLUBS, Rank.JACK), Card.of(Suit.DIAMONDS, Rank.ACE));
//        List<Card> cardsWithSum19 = List.of(Card.of(Suit.CLUBS, Rank.JACK), Card.of(Suit.DIAMONDS, Rank.NINE));
//
//        for (Card card : cardsWithSum19) {
//            dealer.hit(card);
//        }
//        for (Card card : blackjackCards) {
//            player.hit(card);
//        }
//
//        int payout = player.getBet().calculatePayout();
//
//        assertThat(payout).isEqualTo(25000);
//
//    }
//
//    @Test
//    void 플레이어가_무승부인_경우_배팅금액을_그대로_돌려받는다() {
//        List<Card> cardsWithSum19 = List.of(Card.of(Suit.CLUBS, Rank.JACK), Card.of(Suit.DIAMONDS, Rank.NINE));
//
//        for (Card card : cardsWithSum19) {
//            dealer.hit(card);
//        }
//        for (Card card : cardsWithSum19) {
//            player.hit(card);
//        }
//
//        int payout = player.getBet().calculatePayout();
//
//        assertThat(payout).isEqualTo(10000);
//    }
//
//    @Test
//    void 딜러와_플레이어가_동시에_블랙잭인_경우_무승부로_판별되어_베팅금액을_그대로_돌려받는다() {
//        List<Card> blackjackCards = List.of(Card.of(Suit.CLUBS, Rank.JACK), Card.of(Suit.DIAMONDS, Rank.ACE));
//
//        for (Card card : blackjackCards) {
//            dealer.hit(card);
//        }
//        for (Card card : blackjackCards) {
//            player.hit(card);
//        }
//
//        int payout = player.getBet().calculatePayout();
//
//        assertThat(payout).isEqualTo(10000);
//    }
//}
