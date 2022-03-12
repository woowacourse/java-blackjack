package blackjack;

import blackjack.domain.HitRequest;
import blackjack.domain.Name;
import blackjack.domain.WinResult;
import blackjack.domain.card.BlackJackCardsGenerator;
import blackjack.domain.card.CardDeck;
import blackjack.domain.dto.ParticipantInitialResponse;
import blackjack.domain.dto.ParticipantResponse;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    public void play() {
        CardDeck deck = new CardDeck(new BlackJackCardsGenerator());
        Dealer dealer = new Dealer(deck.drawDouble());
        List<Player> players = createPlayers(inputPlayerNames(), deck);
        proceed(deck, dealer, players);
    }

    private List<Name> inputPlayerNames() {
        try {
            return InputView.inputPlayerNames()
                    .stream()
                    .map(Name::new)
                    .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerNames();
        }
    }

    private List<Player> createPlayers(List<Name> playerNames, CardDeck deck) {
        return playerNames.stream()
                .map(name -> new Player(name, deck.drawDouble()))
                .collect(Collectors.toUnmodifiableList());
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
            OutputView.printParticipantCards(new ParticipantResponse(player));
        }
        showStopReason(player);
    }

    private HitRequest inputHitRequest(Player player) {
        try {
            return HitRequest.find(InputView.inputHitRequest(player.getName()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputHitRequest(player);
        }
    }

    private void showStopReason(Player player) {
        if (player.isBlackJack()) {
            OutputView.printBlackJackMessage(player.getName());
            return;
        }
        if (player.isBust()) {
            OutputView.printBustMessage(player.getName());
        }
    }

    private void proceedDealer(Dealer dealer, CardDeck deck) {
        while (dealer.isHittable()) {
            dealer.hit(deck);
            OutputView.printDealerHitMessage(new ParticipantResponse(dealer));
        }
    }

    private void showResult(Dealer dealer, List<Player> players) {
        OutputView.printCardResultMessage();
        OutputView.printParticipantCards(new ParticipantResponse(dealer));
        players.forEach(player -> OutputView.printParticipantCards(new ParticipantResponse(player)));
        OutputView.printWinResult(WinResult.of(dealer, players));
    }
}
