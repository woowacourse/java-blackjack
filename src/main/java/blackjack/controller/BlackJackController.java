package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;
import blackjack.domain.result.MatchResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    private static final String YES = "Y";

    public void run() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck(Card.values());
        List<Name> playerNames = getPlayerNames();
        Players players = makePlayers(playerNames);
        playGame(dealer, deck, players);
        showResult(dealer, players);
    }

    private List<Name> getPlayerNames() {
        OutputView.printNameInputGuideMessage();
        try {
            return InputView.getPlayerNameInput().stream()
                    .map(Name::new)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getPlayerNames();
        }
    }

    private Players makePlayers(List<Name> playerNames) {
        List<Player> players = new ArrayList<>();
        for (Name name : playerNames) {
            BettingMoney bettingMoney = getBettingMoney(name);
            players.add(new Player(name, bettingMoney));
        }
        return new Players(players);
    }

    private BettingMoney getBettingMoney(Name name) {
        OutputView.printBettingMoneyInputGuideMessage(name);
        return new BettingMoney(InputView.getBettingMoney());
    }

    private void playGame(Dealer dealer, Deck deck, Players players) {
        drawAtFirst(dealer, players, deck);
        OutputView.printAfterDrawAtFirstGuideMessage(players);
        OutputView.printParticipantsCardAtFirst(dealer, players);
        askPlayersToHit(players, deck);
        drawMoreCardToDealer(dealer, deck);
    }

    private void drawAtFirst(Dealer dealer, Players players, Deck deck) {
        dealer.drawAtFirst(deck);
        players.drawAtFirst(deck);
    }

    private void askPlayersToHit(Players players, Deck deck) {
        players.getPlayers()
                .forEach(player -> askHit(player, deck));
    }

    private void drawMoreCardToDealer(Dealer dealer, Deck deck) {
        while (dealer.canHit()) {
            dealer.hit(deck.pop());
            OutputView.printDealerHitMessage();
        }
    }

    private void askHit(Player player, Deck deck) {
        String doesPlayerWantMoreCard;

        do {
            OutputView.printHitGuideMessage(player);
            doesPlayerWantMoreCard = InputView.getHitValue();
            draw(player, doesPlayerWantMoreCard, deck);
        } while (player.isNotBust() && doesPlayerWantMoreCard.equals(YES));
    }

    private void draw(Player player, String doesPlayerWantMoreCard, Deck deck) {
        if (doesPlayerWantMoreCard.equals(YES)) {
            player.hit(deck.pop());
            OutputView.printPlayerCards(player);
        }
    }

    private void showResult(Dealer dealer, Players players) {
        OutputView.printCardsAndScore(dealer, players);
        MatchResult matchResult = new MatchResult(dealer, players);
        OutputView.printMatchResult(matchResult);
    }
}
