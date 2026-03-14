package domain.game;

import static util.Constants.DEALER_NAME;
import static util.Constants.DEFAULT_CARD_SET;
import static util.Constants.DEFAULT_START_CARD_COUNT;

import domain.card.GameCards;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.attribute.Hand;
import domain.player.attribute.Money;
import domain.player.attribute.Name;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("게임을 처음 시작했을 떄 딜러와 참가자들 각각에게 카드가 2장씩 가는지 검증")
    void 게임_초기화_검증 () {
        // given
        Name dealerName = new Name(DEALER_NAME);
        Hand dealerHand = new Hand();
        Dealer dealer = new Dealer(dealerName, dealerHand);

        List<Gambler> gamblersList = List.of("pobi", "jason")
                .stream()
                .map(nameInput -> {
                    Name name = new Name(nameInput);
                    Hand hand = new Hand();
                    Money money = new Money("10000");
                    return new Gambler(name, hand, money);
                })
                .toList();
        Gamblers gamblers = new Gamblers(gamblersList);
        GameCards gameCards = new GameCards(DEFAULT_CARD_SET);

        Game game = new Game(dealer, gamblers, gameCards);

        // when
        game.initializeGame();

        // then
        Assertions.assertThat(game.getDealerHandSize()).isEqualTo(2);
        Assertions.assertThat(game.getGamblersHandSize()
                .stream()
                .allMatch(count -> count == DEFAULT_START_CARD_COUNT))
                        .isEqualTo(true);
    }
}