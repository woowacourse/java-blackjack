package domain.strategy;

import domain.TrumpCard;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface DrawStrategy {

    TrumpCard draw(List<TrumpCard> trumpCards);


    void validateDraw(List<TrumpCard> trumpCards);
}
