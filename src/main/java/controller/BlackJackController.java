package controller;

import card.Cards;
import cardGame.CardGame;
import dealer.Dealer;
import java.util.ArrayList;
import java.util.List;
import player.Name;
import player.Player;
import player.Players;
import player.dto.PlayersCardStatusDto;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void playGame() {
        List<String> nameString = inputView.inputPlayerNames();

        List<Name> names = nameString.stream()
                .map(Name::new)
                .toList();

        Dealer dealer = new Dealer();

        List<CardGame> cardGames = new ArrayList<>();

        Players players = Players.from(names);
        for (Player player : players.getPlayers()) {
            player.receiveCard(dealer.giveCard());
            player.receiveCard(dealer.giveCard());
            CardGame cardGame = new CardGame(player, dealer);
            cardGames.add(cardGame);
        }

        outputView.printInitCardStatus(PlayersCardStatusDto.of(players), dealer.getCards());
        // cardGame
        for (CardGame cardGame : cardGames) {
            Player player = cardGame.getPlayer();
            while (!player.isOverMaxCardScore() && inputView.inputPlayerCommand(player.getName())) {
                Cards cards = cardGame.playRound();
                outputView.printCardsStatus(player.getName(), cards);
            }
        }
    }
}
