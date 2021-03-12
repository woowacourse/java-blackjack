package blackjack.controller;

import blackjack.controller.dto.PlayerStatusDto;
import blackjack.controller.dto.RoundStatusDto;
import blackjack.domain.Answer;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {
    public static final int FIRST_TWO_CARD = 2;
    private final InputView inputView = new InputView(new Scanner(System.in));

    public void start() {
        Deck deck = new Deck(Card.getShuffledCards());
        Dealer dealer = new Dealer(deck.drawCard(FIRST_TWO_CARD));
        List<String> playerNames = inputView.getPlayerNames();
        isDuplicatePlayers(playerNames, dealer.getName());
        List<Player> players = joinPlayers(deck, playerNames);
        OutputView.showInitialStatus(createRoundStatusDto(dealer, players));
        addUsersCardOrPass(dealer, players, deck);
        OutputView.showFinalStatus(createRoundStatusDto(dealer, players));
        OutputView.showOutcomes(new Result(dealer, players));
    }

    private List<Player> joinPlayers(Deck deck, List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            int battingAmount = inputView.getBattingAmount(playerName);
            players.add(new Player(playerName, battingAmount, deck.drawCard(FIRST_TWO_CARD)));
        }
        return players;
    }

    private static void isDuplicatePlayers(List<String> players, String dealer) {
        if (players.stream().distinct().count() != players.size() || players.contains(dealer)) {
            throw new IllegalArgumentException("유저 이름이 중복됩니다!");
        }
    }

    private RoundStatusDto createRoundStatusDto(Dealer dealer, List<Player> players) {
        RoundStatusDto roundStatusDto = new RoundStatusDto(dealer.getName(),
                dealer.getCardsStatus(),
                createPlayerStatusDto(players),
                dealer.scoreToInt());
        return roundStatusDto;
    }

    private List<PlayerStatusDto> createPlayerStatusDto(List<Player> players) {
        return players.stream()
                .map(this::getPlayerStatusDto)
                .collect(Collectors.toList());
    }

    private PlayerStatusDto getPlayerStatusDto(Player player) {
        return new PlayerStatusDto(player.getName(), player.getCardsStatus(), player.scoreToInt());
    }

    private void addUsersCardOrPass(Dealer dealer, List<Player> players, Deck deck) {
        players.forEach(player -> askAddCardOrPass(player, deck));
        while (dealer.canAddCard()) {
            dealer.addCard(deck.drawCard());
            OutputView.showDealerAddCard(Dealer.TURN_OVER_COUNT);
        }
        dealer.stayIfNotFinished();
    }

    private void askAddCardOrPass(Player player, Deck deck) {
        while (!player.isFinished()) {
            String answer = inputView.getCardOrPass(player.getName());
            addCardByAnswer(player, deck, answer);
            OutputView.showPlayCardStatus(player.getName(), player.getCardsStatus());
        }
    }

    private void addCardByAnswer(Player player, Deck deck, String answer) {
        if (Answer.isYes(answer)) {
            player.addCard(deck.drawCard());
            return;
        }
        player.stayIfNotFinished();
    }
}
