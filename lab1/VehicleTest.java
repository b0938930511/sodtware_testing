import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Vehicle VehicleTest1;
    private Vehicle VehicleTest2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        VehicleTest1 = new Vehicle();
        VehicleTest2 = new Vehicle(1 , "west");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        if(VehicleTest1 != null){
            VehicleTest1.finalize();
            VehicleTest1 = null;
        }
        if(VehicleTest2 != null){
            VehicleTest2.finalize();
            VehicleTest2 = null;
        }
    }

    @org.junit.jupiter.api.Test
    void testFinalize() {
        VehicleTest1.finalize();
        assertEquals(1, VehicleTest1.totalVehicle());
        VehicleTest1 = null;
        VehicleTest2.finalize();
        assertEquals(0, VehicleTest2.totalVehicle());
        VehicleTest2 = null;


    }

    @org.junit.jupiter.api.Test
    void setSpeed() {
        VehicleTest1.setSpeed(5);
        assertEquals(5, VehicleTest1.getSpeed());
        VehicleTest2.setSpeed(6);
        assertEquals(6, VehicleTest2.getSpeed());

    }

    @org.junit.jupiter.api.Test
    void setDir() {
        VehicleTest1.setDir("west");
        assertEquals("west", VehicleTest1.getDir());
        VehicleTest1.setDir("north");
        assertEquals("north", VehicleTest1.getDir());
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        assertEquals(0, VehicleTest1.getSpeed());
        assertEquals(1, VehicleTest2.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDir() {
        assertEquals("north" , VehicleTest1.getDir());
        assertEquals("west" , VehicleTest2.getDir());
    }

    @org.junit.jupiter.api.Test
    void totalVehicle() {
        assertEquals(2, VehicleTest1.totalVehicle());
        assertEquals(2, VehicleTest2.totalVehicle());
    }
}