package controller;

import config.CardDeckFactory;
import domain.blackJack.BlackJack;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.InputUntilValid;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        CardDeck cardDeck = setUpCardDeck();
        Players players = setUpPlayers();

        playBlackJack(players, new Dealer(), cardDeck);
    }

    private Players setUpPlayers() {
        List<String> names = inputView.readPlayersName();
        Players.validateIsDuplicate(names);
        Players.validatePlayerNumbers(names);

        List<Player> players = new ArrayList<>();
        for (String name : names) {
            Money money = Money.from(inputView.askPlayerForBattingMoney(name));
            players.add(new Player(name, money));
        }

        return Players.from(players);
    }

    private CardDeck setUpCardDeck() {
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        return cardDeckFactory.create();
    }

    private void playBlackJack(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        BlackJack blackJack = new BlackJack(players, dealer, cardDeck);
        blackJack.hitCardsToParticipant();
        outputView.printParticipant(players, dealer);

        blackJack.drawPlayers(
                (player) -> InputUntilValid.validatePlayer(player, inputView::askPlayerForHitOrStand),
                outputView::printPlayerDeck);

        outputView.printDrawDealer(dealer);
        blackJack.drawDealer();

        calculateBlackJackResult(players, dealer, blackJack);
    }

    private void calculateBlackJackResult(Players players, Dealer dealer, BlackJack blackJack) {
        outputView.printScore(players, dealer);
        Map<Player, Integer> profitOfPlayer = blackJack.calculatePlayerResult();
        int dealerProfit = blackJack.calculateDealerResult();
        outputView.printProfit(profitOfPlayer, dealerProfit);
    }
}
