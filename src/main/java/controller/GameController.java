package controller;

import static java.util.stream.Collectors.*;

import java.util.List;

import domain.card.CardFactory;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.gamer.YesOrNo;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class GameController {
    public void run() {
        Gamers gamers = new Gamers(generatePlayers(), new Dealer());
        Deck deck = new Deck(CardFactory.create());

        gamers.initCard(deck);

        OutputView.printInitCardGuide(gamers);
        OutputView.printPlayer(gamers);
        addCardAtPlayers(gamers, deck);
        OutputView.printPlayer(gamers);
    }

    private List<Player> generatePlayers() {
        return InputUtils.splitAsDelimiter(InputView.inputAsPlayerName())
                .stream()
                .map(Player::new)
                .collect(toList());
    }

    private void addCardAtPlayers(Gamers gamers, Deck deck) {
    	gamers.stream()
				.forEach(player -> drawCardOfPlayer(deck, player));
    }

    private void drawCardOfPlayer(Deck deck, Player player) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = YesOrNo.findYesOrNo(InputView.inputAsDrawable(player));
            player.addCard(deck.popCard(1));
        } while (yesOrNo.getDrawable() && player.isDrawable());
    }
}
