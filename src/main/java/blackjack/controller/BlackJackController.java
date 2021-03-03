package blackjack.controller;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.view.InputView;

public class BlackJackController {

    public static void play() {

        Players players = Players.valueOf(InputView.inputString());

    }

}
