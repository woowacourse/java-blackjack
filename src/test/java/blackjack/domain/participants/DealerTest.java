package blackjack.domain.participants;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import java.util.Stack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 블랙잭_게임을_준비한다() {
        //given
        Players players = new Players(
                List.of(new Player("pobi", new Cards()),
                        new Player("surf", new Cards())
                ));
        Stack<Card> cards = new Stack<>();
        cards.addAll(List.of(
                new Card(Suit.CLUB, Rank.FOUR),
                new Card(Suit.CLUB, Rank.FIVE),
                new Card(Suit.CLUB, Rank.ONE),
                new Card(Suit.DIAMOND, Rank.ONE),
                new Card(Suit.HEART, Rank.ONE),
                new Card(Suit.SPADE, Rank.ONE)
        ));

        Dealer dealer = new Dealer(players, new Deck(cards));

        //when
        dealer.prepareBlackjack();

        //then
        assertAll(
                () -> assertEquals(dealer.getCards(),
                        new Cards(List.of(
                                new Card(Suit.SPADE, Rank.ONE),
                                new Card(Suit.HEART, Rank.ONE)
                        ))
                ),
                () -> assertEquals(players.getPlayers().get(0).getCards(),
                        new Cards(List.of(
                                new Card(Suit.DIAMOND, Rank.ONE),
                                new Card(Suit.CLUB, Rank.ONE)
                        ))
                ),
                () -> assertEquals(players.getPlayers().get(1).getCards(),
                        new Cards(List.of(
                                new Card(Suit.CLUB, Rank.FIVE),
                                new Card(Suit.CLUB, Rank.FOUR)
                        ))
                )
        );
    }

    @Test
    void 딜러의_카드쌍의_합이_16이하인_경우_16초과가_될_때_까지_카드를_추가로_뽑는다() {
        //given
        Players players = new Players(
                List.of(
                        new Player("pobi", new Cards()),
                        new Player("surf", new Cards()),
                        new Player("fora", new Cards())
                )
        );
        Stack<Card> cards = new Stack<>();
        cards.addAll(
                List.of(
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.CLUB, Rank.ONE),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE))
        );
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(players, deck);

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

    @Test
    void 존재하지_않는_플레이어에게_카드를_나누어_줄_수_없다() {
        //given
        Player foraPlayer = new Player("fora", new Cards());

        Players players = new Players(
                List.of(
                        new Player("pobi", new Cards()),
                        new Player("surf", new Cards())
                )
        );
        Stack<Card> cards = new Stack<>();
        cards.addAll(
                List.of(
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.CLUB, Rank.ONE),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE))
        );
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(players, deck);

        //when & then
        Assertions.assertThatThrownBy(() -> dealer.sendCardToPlayer(foraPlayer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 플레이어는 존재하지 않습니다.");
    }

    @Test
    void 카드_합이_21이_넘는_플레이어에게_카드를_나누어_줄_수_없다() {
        //given
        Player pobiPlayer = new Player("pobi", new Cards((
                List.of(
                        new Card(Suit.CLUB, Rank.ONE),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE))
        )));

        Players players = new Players(
                List.of(
                        pobiPlayer,
                        new Player("surf", new Cards()),
                        new Player("fora", new Cards())
                )
        );
        Stack<Card> cards = new Stack<>();
        cards.addAll(
                List.of(
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.CLUB, Rank.ONE),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE))
        );
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(players, deck);

        //when & then
        Assertions.assertThatThrownBy(() -> dealer.sendCardToPlayer(pobiPlayer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 플레이어가 가질 수 있는 카드 합의 최대는 21입니다.");
    }

    @Test
    void 플레이어에게_카드를_나누어_줄_수_있다() {
        //given
        Player pobiPlayer = new Player("pobi", new Cards());

        Players players = new Players(
                List.of(
                        pobiPlayer,
                        new Player("surf", new Cards()),
                        new Player("fora", new Cards())
                )
        );
        Stack<Card> cards = new Stack<>();
        cards.addAll(
                List.of(
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.CLUB, Rank.ONE),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE))
        );
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(players, deck);

        //when & then
        Assertions.assertThatCode(() -> dealer.sendCardToPlayer(pobiPlayer)).doesNotThrowAnyException();
    }
}
