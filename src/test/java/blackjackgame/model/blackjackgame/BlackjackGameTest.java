package blackjackgame.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;

import blackjackgame.model.card.Card;
import blackjackgame.model.card.CardNumber;
import blackjackgame.model.card.CardShape;
import blackjackgame.model.card.Cards;
import blackjackgame.model.card.StaticCardDispenser;
import blackjackgame.model.participants.dealer.Dealer;
import blackjackgame.model.participants.player.Player;
import blackjackgame.model.participants.player.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private final BlackjackGame blackjackGame = prepareBlackjackGame();

    @DisplayName("게임 시작 시 딜러와 플레이어에게 카드 2장 지급")
    @Test
    void testDistributeCardsForSetting() {
        Cards cards = createDistributeTestCards();
        blackjackGame.distributeCardsForSetting(cards);

        assertThat(blackjackGame.getDealer().cardsSize()).isEqualTo(2);
        blackjackGame.getPlayers().getPlayers()
                .forEach(player -> assertThat(player.cardsSize()).isEqualTo(2));
    }

    private Cards createDistributeTestCards() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardFiveClover = new StaticCardDispenser(CardNumber.FIVE, CardShape.CLOVER);
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        StaticCardDispenser cardSevenClover = new StaticCardDispenser(CardNumber.SEVEN, CardShape.CLOVER);
        StaticCardDispenser cardSixDia = new StaticCardDispenser(CardNumber.SIX, CardShape.DIAMOND);
        StaticCardDispenser cardAceSpade = new StaticCardDispenser(CardNumber.ACE, CardShape.SPADE);

        return new Cards(
                List.of(new Card(cardJackDia), new Card(cardFiveClover), new Card(cardAceHeart),
                        new Card(cardSevenClover), new Card(cardSixDia), new Card(cardAceSpade))
        );
    }

    @DisplayName("각 플레이어에게 추가 카드를 지급한다")
    @Test
    void testHitFromPlayer() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        Players players = blackjackGame.getPlayers();
        List<Player> playersElement = players.getPlayers();
        blackjackGame.isHitByPlayer(playersElement.get(0), new Card(cardJackDia));
        Players updatedPlayers = blackjackGame.getPlayers();
        assertThat(updatedPlayers.getPlayers().get(0).cardsSize()).isEqualTo(1);
    }

    @DisplayName("딜러의 카드 합계가 16점 이하이면 카드가 1개 증가한 딜러 객체를 반환한다")
    @Test
    void testHitFromDealer() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        blackjackGame.isHitByDealer(new Card(cardJackDia));
        assertThat(blackjackGame.getDealer().cardsSize()).isEqualTo(1);
    }

    private BlackjackGame prepareBlackjackGame() {
        return new BlackjackGame(new Dealer(), Players.from(List.of("lily", "jojo")));
    }
}
