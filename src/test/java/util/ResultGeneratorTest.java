package util;

import domain.card.CardDeck;
import domain.game.Results;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import factory.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultGeneratorTest {
    @Test
    @DisplayName("결과 생성 확인")
    void create() {
        Player player = new Player("플레이어");
        Player player2 = new Player("플레이어2");
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck(CardFactory.create());
        cardDeck.shuffle();
        CardDistributor.giveOneCard(cardDeck, player);
        CardDistributor.giveOneCard(cardDeck, player);
        CardDistributor.giveOneCard(cardDeck, player2);
        CardDistributor.giveOneCard(cardDeck, player2);
        dealer.addCard(cardDeck.drawOne());
        dealer.addCard(cardDeck.drawOne());

        assertThat(ResultGenerator.create(dealer, new Players(Arrays.asList(player, player2)))).isInstanceOf(Results.class);
    }
}
