package blackjack.domain;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.RandomCardsGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.DealerDto;
import blackjack.dto.DealerResultsDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayerResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;

public class BlackjackRunner {

    public void run() {
        Deck deck = new Deck(new RandomCardsGenerator());
        Dealer dealer = new Dealer(deck.getInitCards());
        Players players = new Players(deck, InputView.getNames());

        OutputView.printStartInfo(DealerDto.from(dealer), toPlayersDto(players.getValue()));

        players.getValue().forEach(player -> playing(deck, player));
        drawDealer(deck, dealer);

        OutputView.printResultInfo(DealerDto.from(dealer), toPlayersDto(players.getValue()));
        OutputView.printGameResult(DealerResultsDto.of(players, dealer), createPlayerResult(players, dealer));
    }

    private static List<PlayerDto> toPlayersDto(List<Player> players) {
        return players.stream()
                .map(PlayerDto::from)
                .collect(toList());
    }

    private static void playing(Deck deck, Player player) {
        if (player.isDrawable()) {
            drawCard(deck, player);
            playing(deck, player);
        }
    }

    private static void drawCard(Deck deck, Player player) {
        PlayCommand playCommand = InputView.getPlayCommand(player);

        if (playCommand.isYes()) {
            player.append(deck.draw());
            OutputView.printPlayerCardInfo(PlayerDto.from(player));
        }
    }

    private static void drawDealer(Deck deck, Dealer dealer) {
        if (dealer.isDrawable()) {
            dealer.append(deck.draw());
            OutputView.printDealerDrawableInfo();
        }
    }

    private static List<PlayerResultDto> createPlayerResult(Players players, Dealer dealer) {
        return players.getValue()
                .stream()
                .map(player -> PlayerResultDto.of(player, dealer))
                .collect(toList());
    }
}
