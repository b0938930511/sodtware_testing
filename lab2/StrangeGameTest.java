import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PrisonStub extends Prison {
    @Override
    public void imprisonment (Player player) throws InterruptedException {}
}

class MypaypalService implements paypalService{
    int money = 0 ;
    void setMoney(int m){money = m;}
    public String doDonate(){
        if(money > 99)
            return "Success" ;
        else
            return "Fail" ;
    }
}
@ExtendWith(MockitoExtension.class)
class StrangeGameTest {
    @Mock
    Hour hour = mock(Hour.class);
    @Mock
    GAMEDb  GAMEDb_test = mock(GAMEDb.class);
    @Spy
    PrisonStub prison;



    @InjectMocks
    StrangeGame StrangeGame_test = new StrangeGame();

    @Test
    void test_a() throws InterruptedException {
        when(hour.getHour()).thenReturn(10);
        Player player = new Player();
        System.out.println(StrangeGame_test.enterGame(player));
        verify(prison,never()).crime(player);
    }
    @Test
    void test_b() throws InterruptedException {
        Player player = new Player();
        when(hour.getHour()).thenReturn(15);
        String test_b = new String();
        test_b = StrangeGame_test.enterGame(player);
        assertEquals("After a long period of punishment, the player can leave! :)" , test_b);
    }

    @Test
    void test_c() throws InterruptedException {
        when(hour.getHour()).thenReturn(16);
        Player player1 = new Player("01",-1);
        Player player2 = new Player("02",-1);
        Player player3 = new Player("03",-1);
        StrangeGame_test.setPrison(prison);
        StrangeGame_test.enterGame(player1);
        StrangeGame_test.enterGame(player2);
        StrangeGame_test.enterGame(player3);
        int num = 0;
        ArrayList player_log = new ArrayList();
        player_log = StrangeGame_test.prison.getLog();

        assertEquals(player_log.get(0),"01");
        assertEquals(player_log.get(1),"02");
        assertEquals(player_log.get(2),"03");
    }
    @Test
    void test_d() throws InterruptedException {
        when(hour.getHour()).thenReturn(16);
        Player player0 = new Player("310554034",0);
        when(GAMEDb_test.getScore(player0.getPlayerId())).thenReturn(100);
        StrangeGame_test.enterGame(player0);
        assertEquals(StrangeGame_test.db.getScore(player0.getPlayerId()),100);
    }
    @Test
    void test_e(){
        MypaypalService paypalService = new MypaypalService();
        paypalService.setMoney(50);
        assertEquals(StrangeGame_test.donate(paypalService),"Some errors occurred");
        paypalService.setMoney(100);
        assertEquals(StrangeGame_test.donate(paypalService),"Thank you");
    }





}