package blackjack.domain;

public interface Ownable<O> {
    default boolean ownedSameOwner(Ownable<O> object){
        return getOwner().equals(object.getOwner());
    }

    O getOwner();
}
