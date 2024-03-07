import static org.assertj.core.api.Assertions.assertThat;

import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Game;
import domain.Participant;
import domain.Player;
import domain.constants.CardValue;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("참가자들에게 2장 씩 카드를 분배한다.")
    @Test
    void startBlackJack() {
        List<String> playerNames = Arrays.asList("pobi", "jason");
        Dealer dealer = new Dealer("딜러");
        Game game = new Game(dealer, playerNames);

        CardsStatus cardsStatus = game.initiateGameCondition();
        List<CardStatus> statuses = cardsStatus.status();

        for (CardStatus status : statuses) {
            assertThat(status.cards()).hasSize(2);
        }
    }

    @DisplayName("딜러의 점수가 16 이하인동안 반복해서 카드를 받는다.")
    @Test
    void giveCardsUntilDealerScoreOverThreshold() {
        // given
        Dealer dealer = new Dealer("딜러");
        dealer.saveCard(new Card(CardValue.FIVE, Shape.DIAMOND));
        dealer.saveCard(new Card(CardValue.FIVE, Shape.CLOVER));
        List<Player> players = List.of(new Player("pobi"));
        Participant participant = new Participant(dealer, players);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardValue.THREE, Shape.DIAMOND));
        cards.add(new Card(CardValue.THREE, Shape.CLOVER));
        cards.add(new Card(CardValue.TWO, Shape.HEART));

        Game game = new Game(participant, new Cards(cards));

        // when
        int count = game.giveCardsToDealer();

        // then
        assertThat(count).isEqualTo(3);
    }

    @DisplayName("참가자들의 승패 여부를 판단한다.")
    @Test
    void judgeWinners() {
        // given
        Dealer dealer = new Dealer("딜러");
        dealer.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
        dealer.saveCard(new Card(CardValue.SEVEN, Shape.CLOVER));

        Player player = new Player("pobi");
        player.saveCard(new Card(CardValue.TEN, Shape.HEART));
        player.saveCard(new Card(CardValue.EIGHT, Shape.SPADE));

        List<Player> players = List.of(player);
        Participant participant = new Participant(dealer, players);

        Game game = new Game(participant, new Cards());

        // when
        List<Boolean> isWinner = game.judgeWinners();

        // then
        assertThat(isWinner).containsExactly(true);
    }
}
