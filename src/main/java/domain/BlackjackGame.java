package domain;

import domain.card.CardDeck;
import domain.card.CardGenerator;
import domain.dealer.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import domain.player.Players;
import domain.player.dto.PlayerHandDto;
import domain.view.ApplicationView;

import java.util.List;

public class BlackjackGame {

    public ApplicationView view;
    public CardDeck cardDeck;

    public BlackjackGame(ApplicationView view, CardGenerator cardGenerator) {
        this.view = view;
        this.cardDeck = CardDeck.from(cardGenerator);
    }

    public void start() {
        Dealer dealer = enterDealer();
        Players players = enterPlayers();

        dealInitialCard(dealer, players);

        view.printFirstHandOutResult(players.displayNames());
        view.printParticipantHand(PlayerHandDto.of(dealer));
        view.printAllParticipantsHand(getPlayerHandInformation(players));


    }

    private void dealInitialCard(Dealer dealer, Players players) {
        dealer.drawMySelf(2);
        players.giveMeFirstCardBundle(dealer);
    }

    private Dealer enterDealer() {
        return Dealer.of(cardDeck);
    }

    private Players enterPlayers() {
        return Players.from(requestPlayerNames()
                .stream()
                .map(Player::from)
                .toList()
        );
    }

    private List<PlayerName> requestPlayerNames() {
        return view.requestPlayerNames();
    }

    private List<PlayerHandDto> getPlayerHandInformation(Players players) {
        return players.stream()
                .map(PlayerHandDto::of)
                .toList();
    }

}
