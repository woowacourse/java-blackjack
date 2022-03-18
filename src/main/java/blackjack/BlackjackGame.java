package blackjack;

import blackjack.domain.Betting;
import blackjack.domain.GameResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Names;
import blackjack.domain.participant.Player;
import blackjack.dto.HitRequest;
import blackjack.dto.ParticipantInitialResponse;
import blackjack.dto.ParticipantResponse;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    public void play() {
        CardDeck deck = CardDeck.createGameDeck();
        Dealer dealer = new Dealer(deck.drawDouble());
        List<Player> players = createPlayers(inputPlayerNames(), deck);
        proceed(deck, dealer, players);
    }

    private Names inputPlayerNames() {
        try {
            return new Names(InputView.inputPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerNames();
        }
    }

    private List<Player> createPlayers(Names playerNames, CardDeck deck) {
        return playerNames.getNames()
                .stream()
                .map(name -> new Player(name, deck.drawDouble(), inputBetting(name)))
                .collect(Collectors.toUnmodifiableList());
    }

    private Betting inputBetting(Name name) {
        try {
            return new Betting(InputView.inputBetMoney(name.getValue()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputBetting(name);
        }
    }

    private void proceed(CardDeck deck, Dealer dealer, List<Player> players) {
        alertStart(dealer, players);
        proceedPlayers(players, deck);
        proceedDealer(dealer, deck);
        showResult(dealer, players);
    }

    private void alertStart(Dealer dealer, List<Player> players) {
        ParticipantInitialResponse dealerResponse = new ParticipantInitialResponse(dealer);
        List<ParticipantResponse> playerResponses = players.stream()
                .map(ParticipantResponse::new)
                .collect(Collectors.toList());

        OutputView.printStartMessage(dealerResponse, playerResponses);
    }

    private void proceedPlayers(List<Player> players, CardDeck deck) {
        players.forEach(player -> proceedPlayer(player, deck));
    }

    private void proceedPlayer(Player player, CardDeck deck) {
        while (player.isHittable() && inputHitRequest(player) == HitRequest.YES) {
            player.hit(deck);
            OutputView.printPlayerCards(new ParticipantResponse(player));
        }
        showStopReason(player);
    }

    private HitRequest inputHitRequest(Player player) {
        try {
            return InputView.inputHitRequest(player.getName());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputHitRequest(player);
        }
    }

    private void showStopReason(Player player) {
        if (player.isBlackjack()) {
            OutputView.printBlackJackMessage(player.getName());
            return;
        }
        if (player.isBust()) {
            OutputView.printBustMessage(player.getName());
        }
    }

    private void proceedDealer(Dealer dealer, CardDeck deck) {
        while (dealer.shouldHit()) {
            dealer.hit(deck);
            OutputView.printDealerHitMessage(new ParticipantResponse(dealer));
        }
    }

    private void showResult(Dealer dealer, List<Player> players) {
        OutputView.printCardResultMessage();
        OutputView.printPlayerCards(new ParticipantResponse(dealer));
        OutputView.printPlayersCards(players.stream()
                .map(ParticipantResponse::new)
                .collect(Collectors.toUnmodifiableList()));
        OutputView.printGameResult(GameResult.of(dealer, players));
    }
}
