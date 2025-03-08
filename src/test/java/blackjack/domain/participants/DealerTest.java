package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.FixCardsShuffler;
import blackjack.domain.card.Rank;
import blackjack.domain.card.ScoreCalculator;
import blackjack.domain.card.Suit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 블랙잭_게임을_준비한다() {
        //given
        Players players = new Players(
                List.of(new Player("pobi", new Cards(new ArrayList<>(), new ScoreCalculator())),
                        new Player("surf", new Cards(new ArrayList<>(), new ScoreCalculator()))
                ));
        Deque<Card> cards = new ArrayDeque<>(List.of(
                new Card(Suit.CLUB, Rank.FOUR),
                new Card(Suit.CLUB, Rank.FIVE),
                new Card(Suit.CLUB, Rank.ONE),
                new Card(Suit.DIAMOND, Rank.ONE),
                new Card(Suit.HEART, Rank.ONE),
                new Card(Suit.SPADE, Rank.ONE)
        ));

        Dealer dealer = new Dealer(
                players,
                new Deck(cards),
                new ScoreCalculator());

        //when
        dealer.prepareBlackjack(new FixCardsShuffler());

        //then
        Assertions.assertThat(dealer.getCards().getCards())
                .isEqualTo(List.of(
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE)
                ));
        Assertions.assertThat(players.getPlayers().get(0).getCards().getCards())
                .isEqualTo(List.of(
                        new Card(Suit.CLUB, Rank.ONE),
                        new Card(Suit.DIAMOND, Rank.ONE)));
        Assertions.assertThat(players.getPlayers().get(1).getCards().getCards())
                .isEqualTo(List.of(
                        new Card(Suit.HEART, Rank.ONE),
                        new Card(Suit.SPADE, Rank.ONE)));

    }

    @Test
    void 딜러의_카드쌍의_합이_16이하인_경우_16초과가_될_때_까지_카드를_추가로_뽑는다() {
        //given
        Players players = new Players(
                List.of(
                        new Player("pobi", new Cards(new ArrayList<>(), new ScoreCalculator())),
                        new Player("surf", new Cards(new ArrayList<>(), new ScoreCalculator())),
                        new Player("fora", new Cards(new ArrayList<>(), new ScoreCalculator()))
                )
        );
        Deque<Card> cards = new ArrayDeque<>(List.of(
                new Card(Suit.CLUB, Rank.FIVE),
                new Card(Suit.CLUB, Rank.FOUR),
                new Card(Suit.CLUB, Rank.THREE),
                new Card(Suit.CLUB, Rank.TEN)
        ));
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(players, deck, new ScoreCalculator());

        //when
        dealer.pickAdditionalCard();

        //then
        Assertions.assertThat(dealer.getCards().getCards())
                .isEqualTo(List.of(
                        new Card(Suit.CLUB, Rank.FIVE),
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.TEN)
                ));
    }
}
