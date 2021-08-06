public class Worker {

    // Worker has-a relationship with career
    private Career position;

    public Worker(){
        this.position=new Career();
        position.setSalary(1000L);
    }
    public long getSalary() {
        return position.getSalary();
    }

}

