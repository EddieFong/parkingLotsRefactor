import java.util.ArrayList;

public class ParkingLotServiceManager{
    private ArrayList<ParkingBoy> managementList;
    public ParkingLotServiceManager(ArrayList<ParkingBoy> managementList) {
        this.managementList = managementList;
    }

    public ParkingLotServiceManager() {
        this.managementList = new ArrayList<>();

    }


    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.managementList.add(parkingBoy);
    }

    public ParkingTicket useParkingBoyToPark (ParkingBoy parkingBoy, Car car){
        ParkingTicket ticket =  parkingBoy.park(car);
        return ticket;
    }

    public Car useParkingBoyToFetch (ParkingBoy parkingBoy, ParkingTicket ticket){
        Car car =  parkingBoy.fetch(ticket);
        return car;
    }

}
