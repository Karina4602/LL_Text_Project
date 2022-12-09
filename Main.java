import java.awt.*;
import java.io.*;
import java.util.*;
//TODO SAVE FILE :)
public class Main {
    String txtFile = "data.txt";
    File existing_file;
    BufferedReader br;
    StringTokenizer tk;


    node _new;
    PQ_LL LL;

    int cho;

    public String[] readtxt() throws IOException {
        //READS TEXT AND ADD IT TO NODE
        LL = new PQ_LL();

        String[] ConTemp;


        existing_file = new File(txtFile);
        FileReader fr = null;
        try {
            fr = new FileReader(txtFile);
        } catch (IOException e) {
            System.out.println("Input Error with FileWriter");
            e.printStackTrace();
        }

        br = new BufferedReader(fr);

        //Consumes first line and sets to N
        int N = Integer.parseInt(br.readLine());

        ConTemp = new String[N];

        //reads items after first line
        for (int i = 0; i < N; i++) {
            String content = br.readLine();
            ConTemp[i] = content;
        }

        br.close();


        return ConTemp;

    }

    void ConvertToken(String[] temp) {
        //SEPPERATES WORDS FROM TXT TEMP LIST
        int N = temp.length;
        //tempNum works as a dirty way to add items to the temp Array
        int tempNum = 0;
        String[] tokenTemp = new String[3];
        for (int i = 0; i < N; i++) {
            //Sets items to node
            tk = new StringTokenizer(temp[i]);

//            //Seperates Line into indivudal words
            while (tk.hasMoreTokens()) {

                tokenTemp[tempNum] = tk.nextToken();
                tempNum++;
            }
            //Removes items from list
            String ID = tokenTemp[0];
            Long AT = Long.parseLong(tokenTemp[1]);
            Double Priority = Double.parseDouble(tokenTemp[2]);

            _new = new node(ID, AT, Priority);
            //_new.print();
            LL.sortedAdd(_new);
            tempNum = 0;
            //LL.sort();

        }


    }

    public void save() throws IOException {
        System.out.println(LL.getlength());
        node[] part1 = new node[LL.getlength()];

        for (int i = 0; i < LL.getlength(); i++ ){
            part1[i] = LL.getNode(i);

        }
        String x = String.valueOf(part1.length);



        //emptys file
        FileWriter fw = new FileWriter("data.txt", false);

        existing_file = new File(txtFile);
         fw = new FileWriter(txtFile, true);
        BufferedWriter  br = new BufferedWriter (fw);
        br.write(x + "\n");

        for (int i = 0; i< part1.length  ; i++){
            br.write(part1[i].print2());
        }
        br.close();

    }




    public void main() {
        //Reprint this list of menu after every choice but when 8 exit is chosen.//
        System.out.println("Menu: Choices");
        System.out.println("1. Display Current Requests");
        System.out.println("2. Create new Request");
        System.out.println("3. Search");
        System.out.println("4. Delete a Request");
        System.out.println("5. Serving a Request");
        System.out.println("6. Increase/Decrease Priority of a Request");
        System.out.println("7. Save");
        System.out.println("8. Exit");
        System.out.println("Enter the number choice you wish to do.");
    }

    public void main_cho() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (cho != 8) {
            main();
            cho = sc.nextInt();
            sc.nextLine();
            switch (cho) {
                case 1:
                    //fixed
                    System.out.println("1. Display Current Requests");
                    LL.tranverse();
                    break;
                case 2:
                    //fixed
                    System.out.println("2. Create new Request");
                    System.out.println("Type in ID.");
                    String tempID = sc.nextLine();
                    System.out.println("Type Arrival time");
                    Long tempAT = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Type in Priority");
                    double tempPri= sc.nextDouble();
                    sc.nextLine();
                    _new = new node(tempID, tempAT, tempPri);
                    LL.sortedAdd(_new);
                    //LL.sort();
                    break;

                case 3:
                    System.out.println("3. Search");
                    System.out.println("Which type of search would you like by 1. ID or 2.Priority, enter 1 or 2?");
                    int x = 0;
                    while (x != 2 || x!=1) {
                        x = sc.nextInt();
                        sc.nextLine();
                        if (x == 1) {
                            System.out.println("Search by ID, please enter the ID you wish to see.");
                            tempID = sc.nextLine();
                            LL.searchID(tempID);
                            break;
                        } else if (x == 2) {
                            System.out.println("Search by Priority, please enter the priority you wish to see.");
                            tempPri = sc.nextDouble();
                            sc.nextLine();
                            LL.searchPRI(tempPri);
                            break;
                        } else {
                            System.out.println("Entered in valid answer, please choose 1 or 2");
                        }
                    }
                    break;
                case 4:

                    //Fixed
                    System.out.println("4. Delete a Request");
                    System.out.println("Enter the ID you wish to delete.");
                     tempID = sc.nextLine();
                    LL.removeID(tempID);
                    break;
                case 5:
                    System.out.println("5. Serving a Request");
                    LL.deleteRequest();
                    break;
                case 6:
                    System.out.println("6. Increase/Decrease Priority of a Request");
                    System.out.println("What is the ID you wish change?");
                    tempID = sc.nextLine();
                    System.out.println("What is the new priority?");
                    tempPri = sc.nextDouble();
                    sc.nextLine();
                    LL.idp(tempID,tempPri);
                    break;
                case 7:
                    System.out.println("7. Save");
                    save();
                    break;
                case 8:
                    System.out.print("Thank you for taking part, goodbye!");
                    break;
                default:
                    System.out.println("Sorry, but" + cho + " is not a valid input.");
                    break;
            }
        }
    }






        public static void main (String[]args) throws IOException {
            Main m = new Main();
            m.ConvertToken(m.readtxt());
            m.main_cho();


        }

    }
