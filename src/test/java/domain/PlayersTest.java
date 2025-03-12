package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.card.Rank;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Players;
import domain.player.User;
import domain.player.Users;
import domain.profit.NormalProfitStrategy;
import domain.profit.Profit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 참여자_모두에게_카드를_2장씩_분배한다() {
        // given
        Dealer dealer = new Dealer();
        Users users = new Users(List.of(
                new User("시소"),
                new User("헤일러"),
                new User("부기"),
                new User("사나")
        ));
        Players players = new Players(dealer, users);
        Deck deck = DeckGenerator.generateDeck();

        // when
        players.distributeInitialCards(deck);

        // then
        Assertions.assertThat(players.getPlayers().stream()
                .filter(player -> player.getCards().size() == 2)
                .count()).isEqualTo(5);
    }

    @Test
    void 참여자들의_승부_결과를_반환한다() {
        // given
        Dealer dealer = new Dealer();

        User siso = new User("시소");
        User heiler = new User("헤일러");
        User boogie = new User("부기");
        User sana = new User("사나");

        Users users = new Users(List.of(siso, heiler, boogie, sana));
        Players players = new Players(dealer, users);

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE)
        )));
        players.distributeInitialCards(deck);

        // when
        Map<User, BattleResult> usersBattleResult
                = players.computeUsersBattleResult();

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(usersBattleResult.get(siso)).isEqualTo(BattleResult.NORMAL_WIN);
            softAssertions.assertThat(usersBattleResult.get(heiler)).isEqualTo(BattleResult.LOSE);
            softAssertions.assertThat(usersBattleResult.get(boogie)).isEqualTo(BattleResult.LOSE);
            softAssertions.assertThat(usersBattleResult.get(sana)).isEqualTo(BattleResult.DRAW);
        });
    }

    @Test
    void 참여자들의_수익을_계산한다() {
        // given
        Dealer dealer = new Dealer();

        User siso = new User("시소", 1000);
        User heiler = new User("헤일러", 2000);
        User boogie = new User("부기", 30000);
        User sana = new User("사나", 4000);

        Users users = new Users(List.of(siso, heiler, boogie, sana));
        Players players = new Players(dealer, users);

        Deck deck = new Deck(new ArrayList<>(List.of(
                // sana
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE),
                // boogie
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.THREE),
                // heiler
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.TEN),
                // siso
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.SEVEN),
                // dealer
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE)
        )));
        players.distributeInitialCards(deck);

        // when
        Map<User, Profit> userProfit = players.computeUsersProfit(NormalProfitStrategy.getInstance());

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(userProfit.get(siso).getProfit()).isEqualTo(1000);
            softAssertions.assertThat(userProfit.get(heiler).getProfit()).isEqualTo(3000);
            softAssertions.assertThat(userProfit.get(boogie).getProfit()).isEqualTo(-30000);
            softAssertions.assertThat(userProfit.get(sana).getProfit()).isEqualTo(0);
        });
    }

    @Test
    void 딜러의_수익을_계산한다() {
        // given
        Dealer dealer = new Dealer();

        User siso = new User("시소", 1000);
        User heiler = new User("헤일러", 2000);
        User boogie = new User("부기", 30000);
        User sana = new User("사나", 4000);

        Users users = new Users(List.of(siso, heiler, boogie, sana));
        Players players = new Players(dealer, users);

        Deck deck = new Deck(new ArrayList<>(List.of(
                // sana
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE),
                // boogie
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.THREE),
                // heiler
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.TEN),
                // siso
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.SEVEN),
                // dealer
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE)
        )));
        players.distributeInitialCards(deck);

        // when
        Map<Dealer, Profit> dealerProfit = players.computeDealerProfit();
        Profit profit = dealerProfit.get(dealer);

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(profit.getProfit()).isEqualTo(26000);
        });
    }
}