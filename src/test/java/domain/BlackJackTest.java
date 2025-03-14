package domain;

import domain.card.*;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackTest {
    private final Deck deck = new Deck(new RandomCardsGenerator());

    @DisplayName("플레이어들의 승패를 결정한다")
    @Test
    void test() {
        //given
        Cards dealerCards = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.THREE, CardShape.CLOVER)
                )
        );
        Dealer dealer = new Dealer(dealerCards, deck);

        Cards player1Cards = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.TWO, CardShape.CLOVER)
                )
        );
        Player player1 = Player.from(new Nickname("플레이어1"), player1Cards);

        Cards player2Cards = Cards.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.FOUR, CardShape.CLOVER)
                )
        );
        Player player2 = Player.from(new Nickname("플레이어2"), player2Cards);

        Map<Player, GameResult> expected = Map.of(
                player1, GameResult.LOSE,
                player2, GameResult.WIN
        );

        BlackJack blackJack = new BlackJack(dealer, Players.withoutBetting(Set.of(player1, player2)));

        //when
        Map<Player, GameResult> actual = blackJack.getPlayersResult();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
