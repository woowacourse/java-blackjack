import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Game;
import domain.Player;
import domain.constants.CardValue;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("참가자들에게 2장 씩 카드를 분배한다.")
    @Test
    void startBlackJack() {
        List<String> playerNames = Arrays.asList("pobi", "jason");
        Game game = new Game(playerNames);

        CardsStatus cardsStatus = game.start();
        List<CardStatus> statuses = cardsStatus.status();

        for (CardStatus status : statuses) {
            Assertions.assertThat(status.cards().size()).isEqualTo(2);
        }
    }

    @DisplayName("딜러의 점수가 16 이하인동안 반복해서 카드를 받는다.")
    @Test
    void giveCardsUntilDealerScoreOverThreshold() {
        // given
        Dealer dealer = new Dealer("딜러");
        dealer.add(new Card(CardValue.FIVE, Shape.DIAMOND));
        dealer.add(new Card(CardValue.FIVE, Shape.CLOVER));
        List<Player> players = List.of(dealer);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardValue.THREE, Shape.DIAMOND));
        cards.add(new Card(CardValue.THREE, Shape.CLOVER));
        cards.add(new Card(CardValue.TWO, Shape.HEART));

        Game game = new Game(players, new Cards(cards));

        // when
        int count = game.giveCardsToDealer();

        // then
        Assertions.assertThat(count).isEqualTo(3);
    }
}
