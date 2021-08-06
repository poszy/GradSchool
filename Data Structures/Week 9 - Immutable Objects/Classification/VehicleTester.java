public class VehicleTester {

    public static void main(String args[]){
        System.out.println("Honda Car: \n");
        HondaCar mHonda = new HondaCar();
        mHonda.drive();
        mHonda.honk();
        mHonda.stop();
        mHonda.activateHondaBoosters();
        System.out.println("\n");

        System.out.println("Other Car: \n");
        Car mnotAHonda = new Car();
        mnotAHonda.drive();
        mnotAHonda.honk();
        mnotAHonda.stop();

    }
}
