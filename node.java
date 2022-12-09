public class node {
    String ID;
    Long arrivalTime;
    Double Priority;
    node next;

    public node(String ID, Long arrivalTime, Double Priority) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.Priority = Priority;
        next = null;
    }

    public String getID() {
        return ID;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }

    public Double getPriority() {
        return Priority;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setArrivalTime(Long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPriority(Double priority) {
        Priority = priority;
    }




    public void print(){
        System.out.println("ID: " + ID +", Arrival Time: " + arrivalTime + ", Priority" + Priority);
    }
    public String print2(){
        return ID +" " + arrivalTime + " " + Priority+"\n";
    }
    public void print3(){
        System.out.println(ID +" " + arrivalTime + " " + Priority);
    }
}
