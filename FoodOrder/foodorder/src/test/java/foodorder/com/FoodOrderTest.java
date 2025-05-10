package foodorder.com;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FoodOrderTest {

    @Test
    public void testPlaceOrder_Success() {
        // Arrange
        UserAccount mockUserAccount = mock(UserAccount.class);
        FoodDeliveryService mockDeliveryService = mock(FoodDeliveryService.class);

        when(mockUserAccount.hasSufficientBalance(100.0)).thenReturn(true);

        FoodOrder foodOrder = new FoodOrder(mockUserAccount, mockDeliveryService);

        // Act
        boolean result = foodOrder.placeOrder("Burger", 100.0);

        // Assert
        assertTrue(result); // Pastikan pemesanan berhasil
        verify(mockUserAccount).hasSufficientBalance(100.0); // Verifikasi method dicek
        verify(mockDeliveryService).deliverFood("Burger");   // Verifikasi pengiriman makanan dipanggil
    }

    @Test
    public void testPlaceOrder_Failure() {
        // Arrange
        UserAccount mockUserAccount = mock(UserAccount.class);
        FoodDeliveryService mockDeliveryService = mock(FoodDeliveryService.class);

        when(mockUserAccount.hasSufficientBalance(150.0)).thenReturn(false);

        FoodOrder foodOrder = new FoodOrder(mockUserAccount, mockDeliveryService);

        // Act
        boolean result = foodOrder.placeOrder("Pizza", 150.0);

        // Assert
        assertFalse(result); // Pastikan pemesanan gagal
        verify(mockUserAccount).hasSufficientBalance(150.0); // Verifikasi pengecekan saldo
        verify(mockDeliveryService, never()).deliverFood(anyString()); // Pastikan tidak ada pengiriman makanan
    }
}
