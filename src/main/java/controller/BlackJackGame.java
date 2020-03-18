package controller;

import common.GamerDto;
import domain.GameResult;
import domain.PlayerResult;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import infra.repository.SingleDeck;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {
    public void play() {
        Deck deck = SingleDeck.shuffle();
        List<String> playerNames = InputView.inputPlayerNames();
    }
}
