package foodorder.com;

public class FoodOrder {
    private UserAccount userAccount;
    private FoodDeliveryService deliveryService;

    public FoodOrder(UserAccount userAccount, FoodDeliveryService deliveryService) {
        this.userAccount = userAccount;
        this.deliveryService = deliveryService;
    }

    public boolean placeOrder(String foodItem, double price) {
        if (userAccount.hasSufficientBalance(price)) {
            deliveryService.deliverFood(foodItem);
            return true;
        }
        return false;
    }
}
