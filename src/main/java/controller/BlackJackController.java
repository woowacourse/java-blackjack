package controller;

import config.CardDeckFactory;
import domain.BlackJack;
import domain.card.Deck;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Names;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Deck deck = cardDeckFactory.create();
        Players players = initializePlayerBets();
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));

        playBlackJack(players, dealer, deck);
    }

    private void playBlackJack(final Players players, final Dealer dealer, final Deck deck) {
        BlackJack blackJack = new BlackJack(players, dealer);
        blackJack.hitCardsToParticipant(deck);
        outputView.printParticipant(players, dealer);

        blackJack.drawPlayers(
                (player) -> InputUntilValid.validatePlayerAnswer(player, inputView::askPlayerForHitOrStand),
                outputView::printPlayerDeck, deck);

        outputView.printDrawDealer(dealer);
        blackJack.drawDealer(deck);
        outputView.printScore(players, dealer);
        outputView.printResult(blackJack.calculateDealerProfit(), blackJack.calculatePlayerResult());
    }

    private Players initializePlayerBets() {
        Names names = getPlayerNamesUntilValidate();
        return Players.from(registerPlayerBets(names));
    }

    private Map<Name, Money> registerPlayerBets(Names names) {
        try {
            Map<Name, Money> playerBets = new LinkedHashMap<>();
            for (Name name : names.getNames()) {
                playerBets.put(name, new Money(inputView.askPlayerBattingMoney(name)));
            }
            return playerBets;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return registerPlayerBets(names);
        }
    }

    private Names getPlayerNamesUntilValidate() {
        try {
            return new Names(inputView.readPlayersName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPlayerNamesUntilValidate();
        }
    }
}
