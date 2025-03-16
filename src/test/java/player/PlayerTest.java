package player;

import blackjack.Blackjack;
import card.Card;
import card.CardRank;
import card.CardSuit;
import card.Deck;
import card.DeckGenerator;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void 딜러는_초기에_카드를_2장씩_받는다() {
        // given
        Player player = new Dealer();
        Deck deck = DeckGenerator.generateDeck();

        // when
        player.receiveInitialCards(deck);

        // then
        Assertions.assertThat(player.getHandCards().size())
                .isEqualTo(2);
    }

    @Test
    void 참여자는_초기에_카드를_2장씩_받는다() {
        // given
        Player player = new Participant("훌라");
        Deck deck = DeckGenerator.generateDeck();

        // when
        player.receiveInitialCards(deck);

        // then
        Assertions.assertThat(player.getHandCards().size())
                .isEqualTo(2);
    }

    @Test
    void 이름이_같다면_같은_참여자다() {
        // given
        final String targetName = "훌라";
        Player player = new Participant(targetName);

        // when & then
        Assertions.assertThat(player)
                .isEqualTo(new Participant(targetName));
    }

    @Test
    void 해당_플레이어가_버스트_되었다면_TRUE를_반환한다() {
        // given
        Players players = new Players(List.of(
                new Dealer(),
                new Participant("시소")
        ));

        Player player = players.getPlayerByName("시소");

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN)
        )));
        Blackjack blackjack = new Blackjack(players, deck);
        blackjack.distributeInitialCards();
        blackjack.addCard(player);
        blackjack.addCard(player);
        blackjack.addCard(player);

        // when
        boolean isBust = player.isBust();

        // then
        Assertions.assertThat(isBust).isTrue();
    }

    @Test
    void 해당_플레이어가_버스트_되었다면_FALSE를_반환한다() {
        // given
        Players players = new Players(List.of(
                new Dealer(),
                new Participant("시소")
        ));

        Player player = players.getPlayerByName("시소");

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN)
        )));
        Blackjack blackjack = new Blackjack(players, deck);
        blackjack.distributeInitialCards();

        // when
        boolean isBust = player.isBust();

        // then
        Assertions.assertThat(isBust).isFalse();
    }
}
