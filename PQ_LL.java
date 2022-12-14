import java.util.StringTokenizer;

public class PQ_LL {
    node head;
    StringTokenizer tk;


    public PQ_LL(){
        head = null;
    }


    public void add(node _new){
        if (head == null)
            head =_new;
        else {
            node walker = head;
            while (walker.next != null)
                walker = walker.next;
            walker.next =_new;
        }
    }


    void sortedAdd(node _new) {
        node walker;
        if (head == null || head.getPriority() <= _new.getPriority()) {

            //checks if prios are equal
            if (head != null && head.getPriority().equals(_new.getPriority()) ){

                //if priors are equal compares AT. if _new AT is smaller switch
                if (head.getArrivalTime() > _new.getArrivalTime()){
                    _new.next = head;
                    head = _new;
                }
                else {
                    head.next = _new;
                }
            }
            else {
                _new.next = head;
                head = _new;
            }
        }
        else {
            walker = head;
            while (walker.next != null && walker.next.getPriority() > _new.getPriority()) {
                    walker = walker.next;
            }
            _new.next = walker.next;
            walker.next = _new;
        }

    }




    public int getindex(String ID){
        node walker;
        int fin = 0;

        //int fin = 0;
        for (walker = head; walker != null; walker = walker.next) {
            if (walker.getID().equals(ID)) {
                break;
            }

        }
        for ( int i =0; i < getlength(); i++){
            if (getNode(i) == walker){
                fin = i;
                break;
            }
        }
        return fin;

    }

     int getlength(){
        node walker;
        int i = 0;
        for (walker = head; walker != null; walker = walker.next){
            i++;
        }
        return i;
    }

//Works
    public node getNode(int index){
        node walker = head;

        for (int i = 0; i < index; i++){
            walker = walker.next;
        }
        return walker;
    }

    //Works
    public void tranverse(){
        node walker;
        for (walker = head; walker != null; walker = walker.next){
            walker.print();
        }
    }



    public void getindex(){
        node walker;
        int i = 0;
        for (walker = head; walker != null; walker = walker.next){
            i++;
        }
    }


    public void idp(String tempID, double tempPri){

         node temp = getNode( getindex(tempID));
         long tempAT = temp.getArrivalTime();
         remove(getindex(tempID));
        node _new = new node(tempID,tempAT,tempPri );
        sortedAdd(_new);



    }



    //Works
    public void removeID(String ID){
        node walker;
        int i = 0;
        for (walker = head; walker != null; walker = walker.next){
            if (walker.getID().equals(ID)){
                remove(i);
                break;
            }
            i++;
        }

    }

    public void deleteRequest(){

        int i = 0;
        if (i==0){
            head.print();
            head = head.next;
        }

    }

    public void searchID(String ID){
        node walker;
        boolean x = false;
        for (walker = head; walker != null; walker = walker.next) {
            if (walker.getID().equals(ID)) {
                x = true;
                walker.print();
            }
        }
            if(!x) {
                System.out.println("404 not found");
            }

    }

    public void searchPRI(double PRI){
        node walker;
        boolean x = false;
        for (walker = head; walker != null; walker = walker.next){
            if(walker.getPriority() == PRI){
                x = true;
                walker.print();
            }
        }
        if(!x) {
            System.out.println("404 not found");
        }
    }
    //Works
    public void remove(int i){
        if (i==0){
            head = head.next;
        }
        else {
            node walker = getNode(i-1);
            //System.out.println(walker.getID());
            node toRMv = walker.next;
            walker.next = toRMv.next;

        }
    }



        //Old sorting method, not to be used unefficent
        public void sort(){
        //inefficient and generally bad but hey it works
        //Sort by converting node to array and back to node
            node walker;
            int x = getlength();
            //Gets largest item

            node[] temp = new node[x];

            //Puts item into a temperary list to sort nodes
            for (int i = 0; i < x; i++ ){
                if(getNode(i) == null){
                    break;
                }

                temp[i] = getNode(i);
                //temp[i].print();
               // System.out.println(temp[i]);

            }
            //clears out list
            clear();


            //Actually sorts it, previous code wasn't working so I had to convert it into a list so it would actulaly switch and sort
            node tempy2;
            for (int i = 0; i < temp.length; i++){
                for (int j = 1 ;j < (temp.length - i) ; j++){
                    if (temp[j-1].getPriority() < temp[j].getPriority()){
                        tempy2 = temp[j-1];
                        temp[j-1] = temp[j];
                        temp[j] = tempy2;

                    }
                    else if (temp[j-1].getPriority().equals(temp[j].getPriority()) ){
                        if (temp[j-1].getArrivalTime() > temp[j].getArrivalTime()){
                            tempy2 = temp[j-1];
                            temp[j-1] = temp[j];
                            temp[j] = tempy2;
                        }


                    }
                }
            }

            //tempNum works as a dirty way to add items to the temp Array

            String[] tokenTemp = new String[3];

            int tempNum = 0;

            //seperates from list into nodes
            for (int i = 0; i < temp.length  ; i++){
                String y = temp[i].print2();
                tk = new StringTokenizer(y);


                while (tk.hasMoreTokens()) {

                    if (tempNum == 3){
                        break;
                    }

                    tokenTemp[tempNum] = tk.nextToken();
                    //System.out.println(tokenTemp[tempNum]);
                    tempNum++;
                    //System.out.println(tempNum);
                }
                String ID = tokenTemp[0];
                Long AT = Long.parseLong(tokenTemp[1]);
                Double Priority = Double.parseDouble(tokenTemp[2]);
                node _new = new node(ID, AT, Priority);
                add(_new);
                tempNum = 0;
            }
        }

        //Emptys LL to remake
        public void clear(){
        head = null;
        }


        public int getnum(){
            node walker;
            int x = 0;
            for (walker = head; walker != null; walker = walker.next){
                x++;
            }
            return x;
        }



    }

