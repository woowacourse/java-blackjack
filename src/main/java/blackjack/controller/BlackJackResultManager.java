package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Players;
import blackjack.domain.Result;

public class BlackJackResultManager {

    public Result calculateCardResult(Players players, Dealer dealer) {
        return Result.of(players, dealer);
    }
}
