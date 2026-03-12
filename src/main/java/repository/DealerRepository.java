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
        // TODO: 새로운 Dealer 객체 생성후 반환으로 추후에 변경 예정
    }
}
