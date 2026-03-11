package repository;

import domain.model.Dealer;

public class DealerRepository {

    private Dealer dealer = null;

    public DealerRepository() {
    }

    public Dealer save(Dealer dealer) {
        this.dealer = dealer;
        return dealer;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
