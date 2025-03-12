package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.User;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BattleResultTest {
    @Test
    void 블랙잭이_아니지만_유저가_이기는_경우_승을_반환한다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.ACE),

                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.ACE)
        )));
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(deck);
        User user = new User("새로이", 9000);
        user.receiveInitialCards(deck);

        // when
        BattleResult result = BattleResult.fight(dealer, user);

        // then
        Assertions.assertThat(result)
                .isEqualTo(BattleResult.NORMAL_WIN);
    }

    @Test
    void 블랙잭인_유저가_이기는_경우_블랙잭_상태를_반환한다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.ACE),

                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.ACE)
        )));
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(deck);
        User user = new User("라젤", 9000);
        user.receiveInitialCards(deck);

        // when
        BattleResult result = BattleResult.fight(dealer, user);

        // then
        Assertions.assertThat(result)
                .isEqualTo(BattleResult.BLACKJACK);
    }


    @Test
    void 유저가_지는_경우_패배를_반환한다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.NINE),
                new Card(Suit.SPADE, Rank.ACE),

                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.ACE)
        )));
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(deck);
        User user = new User("유저", 9000);
        user.receiveInitialCards(deck);

        // when
        BattleResult result = BattleResult.fight(dealer, user);

        // then
        Assertions.assertThat(result)
                .isEqualTo(BattleResult.LOSE);
    }

    @Test
    void 딜러와_유저가_둘_다_블랙잭인_경우_무승부를_반환한다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.ACE)
        )));
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(deck);
        User user = new User("칼리", 9000);
        user.receiveInitialCards(deck);

        // when
        BattleResult result = BattleResult.fight(dealer, user);

        // then
        Assertions.assertThat(result)
                .isEqualTo(BattleResult.DRAW);
    }
}