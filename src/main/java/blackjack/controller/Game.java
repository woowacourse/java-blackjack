package blackjack.controller;

import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Players;

public record Game(Players players, Dealer dealer, CardGenerator cardGenerator) {
}
