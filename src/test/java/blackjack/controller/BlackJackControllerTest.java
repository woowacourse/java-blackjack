package blackjack.controller;

import static blackjack.domain.MatchResult.*;
import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.MatchResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackControllerTest extends BlackJackController {

    private Dealer createDealerWithDenominations(Denomination... denominations) {
        Dealer dealer = new Dealer();
        for (Denomination denomination : denominations) {
            dealer.receiveCard(new Card(denomination, HEART));
        }
        return dealer;
    }

    private Player createPlayerWithDenominations(String playerName, Denomination... denominations) {
        Player player = new Player(playerName);
        for (Denomination denomination : denominations) {
            player.receiveCard(new Card(denomination, CLOVER));
        }
        return player;
    }

}
