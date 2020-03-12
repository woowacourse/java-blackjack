package util;

import domain.card.CardDeck;
import domain.game.Results;
import domain.user.Dealer;
import domain.user.User;
import domain.user.Users;
import factory.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultGeneratorTest {
    @Test
    @DisplayName("결과 생성 확인")
    void create() {
        User userA = new User("유저A");
        User userB = new User("유저B");
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck(CardFactory.create());
        cardDeck.shuffle();
        CardDistributor.giveOneCard(cardDeck, userA);
        CardDistributor.giveOneCard(cardDeck, userA);
        CardDistributor.giveOneCard(cardDeck, userB);
        CardDistributor.giveOneCard(cardDeck, userB);
        dealer.addCard(cardDeck.drawOne());
        dealer.addCard(cardDeck.drawOne());

        assertThat(ResultGenerator.create(dealer, new Users(Arrays.asList(userA, userB)))).isInstanceOf(Results.class);
    }
}
