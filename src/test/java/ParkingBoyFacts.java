import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("001");

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car firstCar = new Car("001");
        Car secondCar = new Car("002");

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("001");
        ParkingTicket wrongTicket = new ParkingTicket(null, null);

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongTicket = new ParkingTicket(null, null);

        parkingBoy.fetch(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongTicket = new ParkingTicket(null, parkingLot);

        parkingBoy.fetch(wrongTicket);
        assertNotNull(parkingBoy.getLastErrorMessage());

        ParkingTicket ticket = parkingBoy.park(new Car("001"));
        assertNotNull(ticket);
        assertNull(parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("001");

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(null));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.fetch(null);

        assertEquals(
                "Please provide your parking ticket.",
                parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("001");

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        assertNull(parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("001");

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        parkingBoy.fetch(ticket);

        assertEquals(
                "Unrecognized parking ticket.",
                parkingBoy.getLastErrorMessage()
        );
    }

    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(new Car("001"));

        assertNull(parkingBoy.park(new Car("001")));
    }

    @Test
    void should_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(new Car("001"));
        parkingBoy.park(new Car("002"));

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }

    @Test
    void mult_parking_lot() {
        final int capacity = 1;
        ParkingLot parkingLot1 = new ParkingLot(capacity);
        ParkingLot parkingLot2 = new ParkingLot(capacity);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car firstCar = new Car("001");
        Car secondCar = new Car("002");

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }


    @Test
    void smart_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        Car firstCar = new Car("001");

        ParkingTicket firstTicket = parkingBoy.park(firstCar);

        assertSame(parkingLot2, firstTicket.getParkingLot());

    }

    @Test
    void super_smart_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(3);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        Car firstCar = new Car("001");
        Car secondCar = new Car("002");
        Car thirdCar = new Car("003");

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);
        ParkingTicket thirdTicket = parkingBoy.park(thirdCar);

        assertSame(parkingLot2, thirdTicket.getParkingLot());

    }

    @Test
    void Parking_Lot_Service_Manager_add_parking_boy() {

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();


        ParkingLot parkingLot1 = new ParkingLot(3);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        parkingLotServiceManager.addParkingBoy(parkingBoy );

        Car firstCar = new Car("001");
        Car secondCar = new Car("002");
        Car thirdCar = new Car("003");

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);
        ParkingTicket thirdTicket = parkingBoy.park(thirdCar);

        assertSame(parkingLot2, thirdTicket.getParkingLot());

    }


    @Test
    void Parking_Lot_Service_Manager_use_parking_boy_park() {

        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car("001");
        parkingLotServiceManager.addParkingBoy(parkingBoy);

        ParkingTicket ticket = parkingLotServiceManager.useParkingBoyToPark(parkingBoy, car1);
        Car actualNullCar = parkingLotServiceManager.useParkingBoyToFetch(parkingBoy, null);
        Car actualFetchCar = parkingLotServiceManager.useParkingBoyToFetch(parkingBoy, ticket);

        assertEquals("Please provide your parking ticket.",parkingBoy.getLastErrorMessage());
        assertNull(actualNullCar);
        assertSame(car1, actualFetchCar);

    }
}