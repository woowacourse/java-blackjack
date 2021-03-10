package blackjack.controller;

import blackjack.controller.dto.PlayerStatusDto;
import blackjack.controller.dto.RoundStatusDto;
import blackjack.domain.Answer;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {
    public static final int GAME_OVER_SCORE = 21;

    private final InputView inputView = new InputView(new Scanner(System.in));

    public void start() {
        Users users = Users.of(inputView.getPlayerNames());
        Deck deck = new Deck(Card.getShuffledCards());
        startRound(users, deck);
        OutputView.showInitialStatus(createRoundStatusDto(users));
        addUsersCardOrPass(users, deck);
        OutputView.showFinalStatus(createRoundStatusDto(users));
        OutputView.showOutcomes(new Result(users));
    }

    public void startRound(Users users, Deck deck) {
        users.getDealer().addFirstCards(deck.drawCard(), deck.drawCard());
        for (Player player : users.getPlayers()) {
            player.addFirstCards(deck.drawCard(), deck.drawCard());
        }
    }

    private RoundStatusDto createRoundStatusDto(Users users) {
        RoundStatusDto roundStatusDto = new RoundStatusDto(users.getDealer().getName(),
                users.getDealer().getCardsStatus(),
                createPlayerStatusDto(users.getPlayers()),
                users.getDealer().score());
        return roundStatusDto;
    }

    private List<PlayerStatusDto> createPlayerStatusDto(List<Player> players) {
        return players.stream()
                .map(this::getPlayerStatusDto)
                .collect(Collectors.toList());
    }

    private PlayerStatusDto getPlayerStatusDto(Player player) {
        return new PlayerStatusDto(player.getName(), player.getCardsStatus(), player.score());
    }

    private void addUsersCardOrPass(Users users, Deck deck) {
        users.getPlayers().forEach(player -> askAddCardOrPass(player, deck));
        while (!users.getDealer().canAddCard()) {
            users.getDealer().addCard(deck.drawCard());
            OutputView.showDealerAddCard(Dealer.TURN_OVER_COUNT);
        }
    }

    private void askAddCardOrPass(Player player, Deck deck) {
        String answer = "";
        while (!player.isGameOver() && !Answer.NO.equals(answer)) {
            answer = inputView.getCardOrPass(player.getName());
            addPlayerCard(answer, player, deck);
        }
    }

    private void addPlayerCard(String answer, Player player, Deck deck) {
        if (Answer.YES.equals(answer)) {
            player.addCard(deck.drawCard());
            OutputView.showPlayCardStatus(player.getName(), player.getCardsStatus());
        }
    }
}
