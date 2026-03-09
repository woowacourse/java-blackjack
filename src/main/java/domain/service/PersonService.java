package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import repository.DealerRepository;
import repository.PlayerRepository;

import java.util.List;

public class PersonService {

    private final PlayerRepository playerRepository;
    private final DealerRepository dealerRepository;

    public PersonService(PlayerRepository playerRepository, DealerRepository dealerRepository) {
        this.playerRepository = playerRepository;
        this.dealerRepository = dealerRepository;
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
}
