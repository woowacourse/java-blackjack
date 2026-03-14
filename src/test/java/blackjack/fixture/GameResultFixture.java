package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;

import java.util.List;

public class GameResultFixture {

    public static GameResults 플레이어가_딜러에게_지는_게임_결과(Player player, Dealer dealer) {
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SIX));
        return GameResults.create(new Players(List.of(player)), dealer);
    }

    public static GameResults 플레이어가_딜러에게_이기는_게임_결과(Player player, Dealer dealer) {
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SIX));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));
        return GameResults.create(new Players(List.of(player)), dealer);
    }

    public static GameResults 플레이어가_딜러에게_비기는_게임_결과(Player player, Dealer dealer) {
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));
        return GameResults.create(new Players(List.of(player)), dealer);
    }
}
