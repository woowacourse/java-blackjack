package blackjack.controller;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;
import blackjack.domain.result.ProfitResult;
import blackjack.dto.ParticipantDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    public void start() {
        try {
            Players players = registerPlayers();
            Dealer dealer = new Dealer();
            Deck deck = cardDeckSetting();
            distributeCards(players, dealer, deck);
            playersTurn(players, deck);
            dealerTurn(dealer, deck);
            showProfitResult(players, dealer);
        } catch (IllegalStateException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private Players registerPlayers() {
        try {
            OutputView.enterPlayersName();
            Players players = generatePlayers(InputView.inputName());
            players.bettingEachPlayer();
            return players;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return registerPlayers();
        }
    }

    private Players generatePlayers(List<String> allPlayersName) {
        return new Players(allPlayersName.stream()
                .map(Nickname::new)
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public static void bettingEachPlayer(Player player) {
        try {
            OutputView.askBettingMoney(player);
            player.betting(new BettingMoney(InputView.inputBettingMoney()));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            bettingEachPlayer(player);
        }
    }

    private Deck cardDeckSetting() {
        Deck deck = new Deck(Card.values());
        deck.shuffleDeck();
        return deck;
    }

    private void distributeCards(Players players, Dealer dealer, Deck deck) {
        dealer.firstDraw(deck.drawCard(), deck.drawCard());
        players.eachPlayerFirstDraw(deck);
        OutputView.distributeFirstTwoCard(toPlayersDto(players), toParticipantDto(dealer));
    }

    private List<ParticipantDto> toPlayersDto(Players players) {
        return players.getPlayers().stream()
                .map(this::toParticipantDto)
                .collect(Collectors.toList());
    }

    private ParticipantDto toParticipantDto(Participant participant) {
        Cards cards = participant.getCurrentCards();
        return new ParticipantDto(participant.getName(), cards, cards.calculateScore());
    }

    private void playersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            eachPlayerTurn(deck, player);
        }
    }

    private void eachPlayerTurn(Deck deck, Player player) {
        while (player.canDraw() && askDrawCard(player)) {
            player.draw(deck.drawCard());
            OutputView.showCards(toParticipantDto(player));
        }
    }

    private boolean askDrawCard(Player player) {
        try {
            OutputView.askOneMoreCard(toParticipantDto(player));
            boolean wantDraw = InputView.inputAnswer();
            playerWantStopDraw(player, wantDraw);
            return wantDraw;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return askDrawCard(player);
        }
    }

    private void playerWantStopDraw(Player player, boolean wantDraw) {
        if (!wantDraw) {
            OutputView.showCards(toParticipantDto(player));
            player.stay();
        }
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.draw(deck.drawCard());
            OutputView.dealerReceiveOneCard();
        }
        if (dealer.isHit()) {
            dealer.stay();
        }
    }

    private void showProfitResult(Players players, Dealer dealer) {
        OutputView.showAllCards(toPlayersDto(players), toParticipantDto(dealer));
        ProfitResult profitResult = new ProfitResult();
        profitResult.calculateProfit(players.verifyResultByCompareScore(dealer), dealer);
        OutputView.showFinalProfitResult(profitResult);
    }
}
