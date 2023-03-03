package blackjack.view;

public class Order {
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String NOT_RIGHT_ORDER_MESSAGE = "y나 n을 입력하셔야 합니다.";
    private final String order;
    public Order(String order){
        this.order =order;
    }
    private void validate(String order){

        if(!order.equals(YES)||!order.equals(NO)){
            throw new IllegalArgumentException(NOT_RIGHT_ORDER_MESSAGE);
        }
    }

    public String getOrder() {
        return order;
    }
}
