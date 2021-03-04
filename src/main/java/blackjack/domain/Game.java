package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {
    private static final String ANSWER_YES = "y";

    private final User dealer;
    private final List<User> players;
    private final Deck deck;

    public Game(List<String> names) {
        dealer = new Dealer();
        players = createPlayer(names);
        deck = new Deck();
    }

    private List<User> createPlayer(List<String> names) {
        return names.stream()
            .map(Player::create)
            .collect(Collectors.toList());
    }

    public void initialCards() {
        dealer.initialHands(deck.pickInitialCards());
        players.forEach(player -> player.initialHands(deck.pickInitialCards()));
    }

    public void drawCard(PlayerDto playerDto, String askIfMoreCard) {
        players.stream()
            .filter(player -> player.getName().equals(playerDto.getName()))
            .forEach(player -> addCardOrChangeStatus(askIfMoreCard, player));
    }

    private void addCardOrChangeStatus(String askIfMoreCard, User player) {
        if(askIfMoreCard.equals(ANSWER_YES)) {
            player.draw(deck.pickSingleCard());
            return;
        }
        player.setStatusToStay();
    }

    public boolean askDrawToDealer() {
        if(!dealer.isHit()) {
            return false;
        }
        while(dealer.isHit()) {
            dealer.draw(deck.pickSingleCard());
        }
        return true;
    }

    public User getDealer() {
        return dealer;
    }

    public List<User> getPlayers() {
        return players;
    }

    public boolean isAnyPlayerHit() {
        return players.stream().anyMatch(User::isHit);
    }

    public PlayerDto getAnyHitPlayerDto() {
        Optional<User> first = players.stream().filter(User::isHit).findFirst();
        if(!first.isPresent()) {
            throw new IllegalArgumentException("Hit 이면서 주어진 이름과 같은 플레이어가 존재하지 않습니다.");
        }
        return new PlayerDto(first.get());
    }

    public List<ResultDTO> getResultDTOs() {
        List<ResultDTO> resultDTOS = new ArrayList<>();
        resultDTOS.add(dealer.getResultDTO());
        players.forEach(player -> resultDTOS.add(player.getResultDTO()));

        return resultDTOS;
    }

    public List<WinningResultDTO> getWinningResultDTOs() {
        return players.stream().map(player ->
            new WinningResultDTO(player.getName(), MatchResult.calculateResult(player, dealer)))
            .collect(Collectors.toList());
    }
}
