package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Game;
import blackjack.domain.game.GameBettings;
import blackjack.domain.game.GameParticipants;
import blackjack.domain.gameresult.Betting;
import blackjack.domain.gameresult.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameController {

    private GameController() {
    }

    public static void run() {
        Deck deck = Deck.createShuffledDeck();
        Game game = makeGame(deck);
        Dealer gameDealer = game.getDealer();
        Players gamePlayers = game.getPlayers();

        printInitialHands(gameDealer.getFirstCard(), gamePlayers.getPlayers());
        confirmParticipantsHands(gamePlayers, deck, gameDealer);

        OutputView.printFinalHandsAndScoreMessage(gameDealer, gamePlayers);
        OutputView.printGameResult(game.makeGameResult());
    }

    private static Game makeGame(Deck deck) {
        try {
            Players players = makePlayers();
            GameBettings gameBettings = makeGameBattings(players.getPlayers());
            return Game.of(GameParticipants.of(players), gameBettings, deck);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            makeGame(deck);
        }
        throw new IllegalStateException("게임 객체가 생성되지 않았습니다.");
    }

    private static Players makePlayers() {
        OutputView.printAskNameMessage();
        return new Players(InputView.readNames());
    }

    private static GameBettings makeGameBattings(List<Player> players) {
        Map<Player, Betting> gameBattings = new LinkedHashMap<>();
        for (Player player : players) {
            gameBattings.put(player, InputView.readBatting(player.getName()));
        }
        return new GameBettings(gameBattings);
    }

    private static void confirmParticipantsHands(Players players, Deck deck, Dealer dealer) {
        askDrawUntilConfirmPlayerHands(players, deck);
        confirmDealerHands(dealer, deck);
    }

    private static void printInitialHands(Card dealerFirstCard, List<Player> players) {
        OutputView.printDrawInitialHandsMessage(players);
        OutputView.printParticipantsInitialHands(dealerFirstCard, players);
    }

    private static void confirmDealerHands(Dealer dealer, Deck deck) {
        while (dealer.canAddCard()) {
            dealer.addCard(deck.draw());
            OutputView.printDealerDrawMessage();
        }
    }

    private static void askDrawUntilConfirmPlayerHands(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            askDrawToPlayer(player, deck);
        }
    }

    private static void askDrawToPlayer(Player player, Deck deck) {
        while (player.canAddCard() && InputView.askDraw(player.getName())) {
            player.addCard(deck.draw());
            OutputView.printParticipantHands(player.getName(), player.getHandsCards());
        }
    }
}
