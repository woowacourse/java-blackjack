package model.game;

import card.*;
import game.BlackJack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.Dealer;
import participant.Player;
import participant.Players;

import java.util.ArrayList;
import java.util.List;

public class BlackJackTest {

    private final Players players = Players.from(
            List.of("hippo")
    );
    private final Dealer dealer = new Dealer();
    private final DeckGenerator deckGenerator = new DeckGenerator() {
        @Override
        public List<Card> generateDeck() {
            return new ArrayList<>(List.of(
                    new Card(Suit.DIAMONDS, NormalRank.TWO),
                    new Card(Suit.DIAMONDS, NormalRank.THREE),
                    new Card(Suit.DIAMONDS, NormalRank.FOUR),
                    new Card(Suit.DIAMONDS, NormalRank.FIVE)
            )
            );
        }
    };
    private final CardDeck deck = new CardDeck(deckGenerator.generateDeck());
    private final BlackJack blackJack = new BlackJack(players, dealer, deck);


    @DisplayName("게임 첫 턴에 카드 2장 씩 나눠주는 지")
    @Test
    void dealInitialCardsTest() {
        //given
        //when
        blackJack.dealInitialCards();
        //then
        for (Player player : players.getPlayers()) {
            Assertions.assertThat(player.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.FOUR));
            Assertions.assertThat(player.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.FIVE));
        }
        Assertions.assertThat(dealer.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.TWO));
        Assertions.assertThat(dealer.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.THREE));
    }

    @DisplayName("입력한 수량 만큼 카드가 나누어 지는 지 : 딜러")
    @Test
    void dealCardToDealerTest() {
        //given
        //when
        blackJack.dealCard(dealer, 2);
        //then
        Assertions.assertThat(dealer.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.TWO));
        Assertions.assertThat(dealer.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.THREE));
    }

    @DisplayName("입력한 수량 만큼 카드가 나누어 지는 지 : 플레이어")
    @Test
    void dealCardToPlayerTest() {
        //given
        //when
        for (Player player : players.getPlayers()) {
            blackJack.dealCard(player, 2);
            Assertions.assertThat(player.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.TWO));
            Assertions.assertThat(player.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.THREE));
        }
        //then
    }

    @DisplayName("한장 씩 더 받는 상황에서 카드가 잘 받아지는 지: 딜러")
    @Test
    void receiveAdditionalCardOfDealerTest() {
        //given
        //when
        blackJack.receiveAdditionalCard(dealer);
        //then
        Assertions.assertThat(dealer.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.TWO));
    }

    @DisplayName("한장 씩 더 받는 상황에서 카드가 잘 받아지는 지: 플레이어")
    @Test
    void receiveAdditionalCardOfPlayerTest() {
        for (Player player : players.getPlayers()) {
            blackJack.receiveAdditionalCard(player);
            Assertions.assertThat(player.getCards()).contains(new Card(Suit.DIAMONDS, NormalRank.TWO));
        }
    }
}
