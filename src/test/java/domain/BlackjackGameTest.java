package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Deck;
import domain.participant.*;
import dto.PlayerCreationInfo;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    void 게임_시작시_딜러와_플레이어에게_카드2장_배분() {
        List<PlayerCreationInfo> playerCreationInfos = List.of(
                new PlayerCreationInfo(Name.from("pobi"), BettingMoney.of(1000)),
                new PlayerCreationInfo(Name.from("jason"), BettingMoney.of(2000))
        );

        BlackjackGame blackjackGame = BlackjackGame.start(playerCreationInfos);

        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();

        assertThat(dealer.getHandCards()).hasSize(2);
        assertThat(players.getPlayers()).hasSize(2);

        for (Player player : players.getPlayers()) {
            assertThat(player.getHandCards()).hasSize(2);
        }
    }

    @Test
    void 플레이어가_카드를_추가로_뽑으면_카드수가_1장_증가한다() {
        Deck deck = Deck.from(List.of(
                Card.of(CardNumber.TEN, CardShape.SPADE),     // dealer 1
                Card.of(CardNumber.SIX, CardShape.HEART),     // dealer 2
                Card.of(CardNumber.NINE, CardShape.CLOVER),   // player 1
                Card.of(CardNumber.SEVEN, CardShape.DIAMOND), // player 2
                Card.of(CardNumber.TWO, CardShape.SPADE)      // player draw
        ));

        List<PlayerCreationInfo> playerCreationInfos = List.of(
                new PlayerCreationInfo(Name.from("pobi"), BettingMoney.of(1000))
        );

        BlackjackGame blackjackGame = BlackjackGame.start(playerCreationInfos, deck);
        Player player = blackjackGame.getPlayersValue().getFirst();

        int before = player.getHandCards().size();
        blackjackGame.addPlayerCard(player);

        assertThat(player.getHandCards()).hasSize(before + 1);
    }

    @Test
    void 모든_플레이어가_버스트면_딜러턴은_진행되지_않는다() {
        Deck deck = Deck.from(List.of(
                Card.of(CardNumber.TEN, CardShape.SPADE),     // dealer 1
                Card.of(CardNumber.SIX, CardShape.HEART),     // dealer 2 -> 16
                Card.of(CardNumber.TEN, CardShape.CLOVER),    // player 1
                Card.of(CardNumber.NINE, CardShape.DIAMOND),  // player 2 -> 19
                Card.of(CardNumber.THREE, CardShape.SPADE)    // player draw -> 22
        ));

        List<PlayerCreationInfo> playerCreationInfos = List.of(
                new PlayerCreationInfo(Name.from("pobi"), BettingMoney.of(1000))
        );
        BlackjackGame blackjackGame = BlackjackGame.start(playerCreationInfos, deck);
        Player player = blackjackGame.getPlayersValue().getFirst();
        Dealer dealer = blackjackGame.getDealer();

        int dealerCardCountBefore = dealer.getHandCards().size();

        blackjackGame.addPlayerCard(player);
        boolean result = blackjackGame.playDealerTurn();

        assertThat(player.isBust()).isTrue();
        assertThat(result).isFalse();
        assertThat(dealer.getHandCards()).hasSize(dealerCardCountBefore);
    }

    @Test
    void 플레이어가_살아있고_딜러점수가_16이하면_딜러턴이_진행된다() {
        Deck deck = Deck.from(List.of(
                Card.of(CardNumber.TEN, CardShape.SPADE),     // dealer 1
                Card.of(CardNumber.SIX, CardShape.HEART),     // dealer 2 -> 16
                Card.of(CardNumber.NINE, CardShape.CLOVER),   // player 1
                Card.of(CardNumber.SEVEN, CardShape.DIAMOND), // player 2 -> 16
                Card.of(CardNumber.TWO, CardShape.SPADE)      // dealer draw -> 18
        ));

        List<PlayerCreationInfo> playerCreationInfos = List.of(
                new PlayerCreationInfo(Name.from("pobi"), BettingMoney.of(1000))
        );

        BlackjackGame blackjackGame = BlackjackGame.start(playerCreationInfos, deck);
        Dealer dealer = blackjackGame.getDealer();

        int dealerCardCountBefore = dealer.getHandCards().size();
        boolean result = blackjackGame.playDealerTurn();

        assertThat(result).isTrue();
        assertThat(dealer.getHandCards().size()).isGreaterThan(dealerCardCountBefore);
    }

    @Test
    void 딜러점수가_17이상이면_딜러턴은_진행되지_않는다() {
        Deck deck = Deck.from(List.of(
                Card.of(CardNumber.TEN, CardShape.SPADE),     // dealer 1
                Card.of(CardNumber.SEVEN, CardShape.HEART),   // dealer 2 -> 17
                Card.of(CardNumber.NINE, CardShape.CLOVER),   // player 1
                Card.of(CardNumber.SIX, CardShape.DIAMOND)    // player 2 -> 15
        ));

        List<PlayerCreationInfo> playerCreationInfos = List.of(
                new PlayerCreationInfo(Name.from("pobi"), BettingMoney.of(1000))
        );

        BlackjackGame blackjackGame = BlackjackGame.start(playerCreationInfos, deck);
        Dealer dealer = blackjackGame.getDealer();

        int dealerCardCountBefore = dealer.getHandCards().size();
        boolean result = blackjackGame.playDealerTurn();

        assertThat(result).isFalse();
        assertThat(dealer.getHandCards()).hasSize(dealerCardCountBefore);
    }
}
