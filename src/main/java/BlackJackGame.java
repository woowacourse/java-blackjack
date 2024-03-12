import domain.game.GameResults;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.PlayerNames;
import domain.playingcard.Deck;
import domain.playingcard.PlayingCards;
import dto.DealerHandStatusDto;
import dto.PlayerHandStatusDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackGame {

    public void run() {
        PlayingCards playingCards = PlayingCards.init();
        Deck deck = Deck.init(playingCards.value());
        Dealer dealer = Dealer.init(deck);
        List<Player> players = initPlayers(deck);

        play(deck, dealer, players);

        GameResults gameResults = GameResults.of(dealer, players);
        OutputView.printGameResult(gameResults);
    }

    private List<Player> initPlayers(Deck deck) {
        return initPlayerNames().values()
                .stream()
                .map(playerName -> Player.of(playerName, deck))
                .toList();
    }

    private PlayerNames initPlayerNames() {
        try {
            List<String> inputPlayerNames = InputView.inputPlayerNames();
            return PlayerNames.of(inputPlayerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return initPlayerNames();
        }
    }

    private void play(final Deck deck, final Dealer dealer, final List<Player> players) {
        players.forEach(player -> playForPlayer(deck, player));
        playForDealer(deck, dealer);

        DealerHandStatusDto dealerHandStatusDto = DealerHandStatusDto.of(dealer);
        List<PlayerHandStatusDto> playerHandStatusDtos = players.stream().map(PlayerHandStatusDto::of).toList();
        OutputView.printFinalHandStatus(dealerHandStatusDto, playerHandStatusDtos);
    }

    private void playForDealer(final Deck deck, final Dealer dealer) {
        while (dealer.isDrawable()) {
            dealer.draw(deck);
            OutputView.printDealerDrawMessage();
        }
    }

    private void playForPlayer(final Deck deck, final Player player) {
        while (checkPlayerDrawAvailable(player)) {
            player.draw(deck);
            OutputView.printPlayerDrawStatus(PlayerHandStatusDto.of(player));
        }
    }

    private boolean checkPlayerDrawAvailable(final Player player) {
        if (!player.isDrawable()) {
            return false;
        }
        return inputDrawDecision(player.getPlayerName());
    }

    private boolean inputDrawDecision(PlayerName playerName) {
        try {
            return InputView.inputDrawDecision(playerName);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputDrawDecision(playerName);
        }
    }
}
