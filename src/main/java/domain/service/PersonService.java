package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import domain.model.PlayerBetting;
import repository.DealerRepository;
import repository.PlayerBettingRepository;
import repository.PlayerRepository;

import java.util.List;

import static constant.ErrorMessage.PLAYER_BETTING_NOT_FOUND;

public class PersonService {

    private final PlayerRepository playerRepository;
    private final DealerRepository dealerRepository;
    private final PlayerBettingRepository playerBettingRepository;

    public PersonService(
            PlayerRepository playerRepository,
            DealerRepository dealerRepository,
            PlayerBettingRepository playerBettingRepository
    ) {
        this.playerRepository = playerRepository;
        this.dealerRepository = dealerRepository;
        this.playerBettingRepository = playerBettingRepository;
    }

    public void saveAllPlayers(List<Player> players) {
        playerRepository.saveAll(players);
    }

    public void saveDealer(Dealer dealer) {
        dealerRepository.save(dealer);
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Dealer getDealer() {
        return dealerRepository.getDealer();
    }

    public void savePlayerBetting(PlayerBetting playerBetting) {
        playerBettingRepository.save(playerBetting);
    }

    public PlayerBetting findPlayerBettingByPlayer(Player player) {
        return playerBettingRepository.findByPlayer(player)
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_BETTING_NOT_FOUND.getMessage()));
    }
}
