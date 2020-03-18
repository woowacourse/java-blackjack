package util;

import domain.betting.BettingLog;
import domain.betting.BettingLogs;
import domain.betting.BettingMoney;
import domain.card.CardDeck;
import domain.game.Results;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
import factory.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultGeneratorTest {
    @Test
    @DisplayName("결과 생성 확인")
    void create() {
        User userA = new User("userA");
        User userB = new User("userB");
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck(CardFactory.create());
        List<BettingLog> bettingLogsList = new ArrayList<>();

        bettingLogsList.add(new BettingLog("userA", new BettingMoney("1000")));
        bettingLogsList.add(new BettingLog("userB", new BettingMoney("2000")));

        BettingLogs bettingLogs = new BettingLogs(bettingLogsList);

        cardDeck.shuffle();
        CardDistributor.giveOneCard(cardDeck, userA);
        CardDistributor.giveOneCard(cardDeck, userA);
        CardDistributor.giveOneCard(cardDeck, userB);
        CardDistributor.giveOneCard(cardDeck, userB);
        dealer.addCard(cardDeck.drawOne());
        dealer.addCard(cardDeck.drawOne());

        assertThat(ResultGenerator.create(dealer, new Users(Arrays.asList(userA, userB)), bettingLogs)).isInstanceOf(Results.class);
    }
}
