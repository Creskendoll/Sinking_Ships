import java.util.Scanner;
import java.util.Random;

public class amiral {
	public static void main(String[] args) throws InterruptedException {
		/* String[][] debuggerShip = new String[10][10]; //delete before launch
		for (int i = 0; i < debuggerShip.length; i++) {
			for (int j = 0; j < debuggerShip.length; j++) {
				debuggerShip[i][j] = "[0]";
			}
		}*/
		System.out.println("Dedicated 2 Efsun Karaca.");
		Random r = new Random();
		int[] selectedShip = new int[5];
		String[][][] randomBombs = new String[10][10][100];
		String[][][] userBombs = new String[10][10][100];
		String[][] insBoard  = new String[10][10];
		for (int i = 0; i < insBoard.length; i++) { //instruction board
			for (int j = 0; j < insBoard.length; j++) {
				insBoard[i][j] = "[" + (i+1) + "-" + (j+1) + "]";
			}
		}
		String[][] secureUserBoard = new String[10][10];
		Scanner giris = new Scanner(System.in); 
		String[][] userBoard = new String[10][10];
		String[][] userShipBoard = new String[10][10];
		String[][] cpuBoard = new String[10][10];
		String[][] cpuShipBoard = new String[10][10];
		for (int i = 0; i < cpuShipBoard.length; i++) {
			for (int j = 0; j < cpuShipBoard.length; j++) {
				userShipBoard[i][j] = "[ ]";
				userBoard[i][j] = "[ ]";
				cpuBoard[i][j] = "[ ]";
			}
		}
    // printBoard(insBoard);
	 cpuShipBoard = cpuShipBoard(cpuBoard);	//cpu generated its board	
	// printBoard(cpuShipBoard); //shows the cpu board
	 System.out.println("Cpu has placed its ships.");
	 System.out.println();
     System.out.println("Place your ships:");
     System.out.println("Place the ships automaticly? (y/n)");
     String yesNo = giris.next();
    while(!yesNo.equals("y") && !yesNo.equals("n")){
    	System.out.println("y for yes n for no.");
    	yesNo = giris.next();
    }
     if(yesNo.equals("n")){ //Manual input of ships
    	  printBoard(userBoard);
    	   System.out.println(); 
    	 for (int i = 0; i < selectedShip.length-1; i++) { //place your ships -----
        	 int bayrak = 0;
        	 for (int k = 2; k <= 5; k++) { //prints the ships that are available
        		 for (int j = 0; j < selectedShip.length; j++) {
                	 if(k == selectedShip[j]){
                		 bayrak = k; 
                		 break;
                	 }           	
    			}
        		 if(bayrak == k){ //doesn't print the placed ship
        			 continue;
        		 }
        			 String[] userShips = new String[k];
                	 userShips = ship(k);
                	 for (int j = 0; j < k; j++) {
                		 System.out.print(userShips[j]);	
            		}
                	 System.out.print("---" + k);
                	 System.out.println();    		     		
        	}
        	 
             System.out.println();
             System.out.println("Which ship do you want to place?");
             int selection = giris.nextInt();
             
             while(selection < 2 || selection > 5){ 
            	 System.out.println("Select a ship on the list:");
            	 selection = giris.nextInt();
             }
              selectedShip[i] = selection;     
             
            	 String[] userShips = new String[selectedShip[i]+1];
            	 userShips = ship(selectedShip[i]);
            	 for (int j = 0; j < selectedShip[i]; j++) { //prints horizontal
            		 System.out.print(userShips[j]);	
        		}
            	 System.out.print("----1");
            	 System.out.println();
            	 System.out.println();
            	 
            	 for (int j = 0; j < selectedShip[i]; j++) { //prints vertical
            		 if(j == selectedShip[i]-1){
            			 System.out.print(userShips[j]);
            			 System.out.print("----2");
            			 continue;
            		 }
            		 System.out.println(userShips[j]);	   		 
        		}
            	 
            	 System.out.println();
            	 System.out.println("How do you want to orient it?");
            	 boolean flag = true;
            	 int orientation = 0;
            	 while(flag){
            		 orientation = giris.nextInt() - 1;
            		 if(orientation == 0 || orientation == 1){
            			 flag = false;
            		 }
            		 else{
            			 System.out.println("Select 1 or 2!");
            		 }
            	 }        	 
            	 System.out.println("Which coordinates do you want to place it?");
            	 System.out.println("X:");
            	 int user_x = giris.nextInt() - 1;
            	 while(user_x < 0 || user_x > 9){
            		 System.out.println("Between 1 and 10!");
            		 user_x = giris.nextInt() - 1;
            	 }
            	 System.out.println("Y:");
            	 int user_y = giris.nextInt() - 1;
            	 while(user_y < 0 || user_y > 9){
            		 System.out.println("Between 1 and 10!");
            		 user_y = giris.nextInt() - 1;
            	 }
            	 secureUserBoard = userShipBoard;
            	         	 
            	 try{
            		// secureUserBoard = debuggerShip; //debug
            		 for (int j = 0; j < selectedShip[i]; j++) {
            			 if((orientation == 0 && secureUserBoard[user_x][user_y+j]  == "[ ]") || (orientation == 1 && secureUserBoard[user_x+j][user_y] == "[ ]")){
                			 secureUserBoard = userShipedBoard(user_x, user_y, orientation, selectedShip[i], userShipBoard);	    
                			 break;
                		 }
                		 else if((orientation == 0 && secureUserBoard[user_x][user_y+j] == "[0]") || (orientation == 1 && secureUserBoard[user_x+j][user_y] == "[0]")){
                			 System.out.println("There is an interception between your ships, please place it somewhere else!");            		        			
                			 secureUserBoard = boardCleaner(user_x, user_y, orientation, selectedShip[i], userShipBoard);
                			 selectedShip[i] = 0;
                			 bayrak = 0; //buraya seçtiğin gemiyi kaldırmamak için bişi
                			 i--;    
                			 break;
                		 }	
    				}       		
            	 }
            	 catch(Exception e){ //gemi tahtanın dışına çıkarsa...
            		 bayrak = 0;  
            		 selectedShip[i] = 0;
            		 System.out.println("Ship out of board area! Try Again!");
            		 if(orientation == 0){
            		 secureUserBoard = boardCleaner(user_x, user_y, orientation, userShipBoard.length - user_y, userShipBoard);	 
            		 }
            		 else{        			 
            	     secureUserBoard = boardCleaner(user_x, user_y, orientation, userShipBoard.length - user_x, userShipBoard);	 
            		 }        		       		 
            		 i--; 
            	 }
            	 
            	 printBoard(secureUserBoard);
    	}
     }
     else{
    	 secureUserBoard = cpuShipBoard(userShipBoard);
     }
    
     
     
     
     System.out.println("You have placed your ships.");
     System.out.println();
     printBoard(secureUserBoard);
     Thread.sleep(800);

	 int cpuCounter = 0;
	 int userCounter = 0;
	 boolean gameStopper = true;
	 String winner = "";
	 int counter = 0;
	 int innerCounter = 0;
	 int innerCounterUser = 0;
	 boolean isHit = false;
	 int[] hitStorer = new int[2];
     while(gameStopper){       	     	
    	 if(counter%2 == 0){ //cpu dropping a bomb	 
			 counter++;			 
    		 System.out.print("CPU is playing.");
    		 Thread.sleep(500);
    		 System.out.print(".");
    		 Thread.sleep(500);
    		 System.out.print(".");
    		 Thread.sleep(500);
    		 System.out.print(".");
    		 Thread.sleep(500);
    		 System.out.print(".");
    		 
    		 int x = r.nextInt(10);
			 int y = r.nextInt(10);
			 if(innerCounter == 0){
				 randomBombs[x][y][innerCounter] = "bombed";				 
			 }
			 else if(isHit){	
				 int[] a = bomberAi(hitStorer[0], hitStorer[1]);
				 x = a[0];
				 y = a[1];
				 randomBombs[x][y][innerCounter] = "bombed";
				 for (int k = innerCounter; k >= 0; k--) {
						if(randomBombs[x][y][k] == "bombed"){
							a = bomberAi(hitStorer[0], hitStorer[1]);
							x = a[0];
							y = a[1];
							k = innerCounter;
						}
					}
					 randomBombs[x][y][innerCounter] = "bombed";	
			 }
			 else if(!isHit && innerCounter != 0){
				 x = r.nextInt(10);
				 y = r.nextInt(10);
				 randomBombs[x][y][innerCounter] = "bombed";
				 for (int k = innerCounter; k >= 0; k--) {
					if(randomBombs[x][y][k] == "bombed"){
						x = r.nextInt(10);
						y = r.nextInt(10);
						k = innerCounter;
					}
				}
				 randomBombs[x][y][innerCounter] = "bombed";	
			 }
			 
			 innerCounter++;
			 
    		 if(comparer(x, y, secureUserBoard)){ //if CPU hits a target
            	 cpuCounter++;
            	 isHit = true;
            	 hitStorer[0] = x;
            	 hitStorer[1] = y;
            	 printBombedBoard(x, y, secureUserBoard, isHit);
        		 System.out.println("You hit " + userCounter + " of CPU's targets.");
        		 System.out.println("CPU hit " + cpuCounter + " of your targets.");
        		 System.out.println();
            	 System.out.println("Cpu scored!");
             }
    		 else{
    			 isHit = false;
    			 printBombedBoard(x, y, secureUserBoard, isHit);
    			 System.out.println("Cpu missed.");
        		 System.out.println("You hit " + userCounter + " of CPU's targets.");
        		 System.out.println("CPU hit " + cpuCounter + " of your targets.");
        		 System.out.println();   			 
    		 }  
    		 if(innerCounter == 99){
    			 break;
    		 }    	
    	 }
    	 else { //add a innerCounter and holder array for the user
			 counter++;
    		 System.out.println("Select a point to drop your bomb.");
             System.out.println("X:");
             int userBombX = giris.nextInt() - 1;
             while(userBombX < 0 || userBombX > 9){
            	 userBombX = giris.nextInt() - 1;
             }
             System.out.println("Y:");
             int userBombY = giris.nextInt() - 1;           
             while(userBombY < 0 || userBombY > 9){
            	 userBombY = giris.nextInt() - 1;
             }
             if(innerCounterUser == 0){
            	 userBombs[userBombX][userBombY][innerCounterUser] = "bombed";            	 
             }             
             else if (innerCounterUser >= 1) {					
					for (int i = innerCounterUser; i >= 0; i--) {						
							if (userBombs[userBombX][userBombY][i] == "bombed") {
								i = innerCounterUser;
								System.out.println("You bombed that point before!");
								System.out.println("X:");
								userBombX = giris.nextInt() - 1;
								while (userBombX < 0 || userBombX > 9) {
									userBombX = giris.nextInt() - 1;
								}
								System.out.println("Y:");
								userBombY = giris.nextInt() - 1;
								while (userBombY < 0 || userBombY > 9) {
									userBombY = giris.nextInt() - 1;
								}								
							}	
							userBombs[userBombX][userBombY][innerCounterUser] = "bombed";
					}
					//innerCounterUser++;
				}
             innerCounterUser++;
             System.out.print("Playing");
				Thread.sleep(200);	
				 System.out.print(".");
					Thread.sleep(200);
					 System.out.print(".");
						Thread.sleep(200);
						 System.out.print(".");
							Thread.sleep(200);
             if(comparer(userBombX, userBombY, cpuShipBoard)){
            	 printBombedBoard(userBombX, userBombY, userBoard, true);
            	 userCounter++;
            	 System.out.println("You scored!");
            	 System.out.println("You hit " + userCounter + " of CPU's targets.");
              	System.out.println("CPU hit " + cpuCounter + " of your targets.");
              	System.out.println();
             } 
             else{
            	 printBombedBoard(userBombX, userBombY, userBoard, false);
            	 System.out.println("You missed.");
            	 System.out.println("You hit " + userCounter + " of CPU's targets.");
              	System.out.println("CPU hit " + cpuCounter + " of your targets.");
              	System.out.println();
             }         	
    	 }
    	 
    	 if(cpuCounter == 14){
    		 winner = "CPU";
    		 gameStopper = false;
    	 }
    	 else if(userCounter == 14){
    		 winner = "user";
    		 gameStopper = false;
    	 }             	 
     }
     if(winner == "CPU"){
    	 System.out.println("CPU is the winner!!!!!!!");
    	 giris.close();
     }
     if(winner == "user"){
    	 System.out.println("User is the winner!!");
    	 giris.close();
     }
	}

	private static void printBombedBoard(int x, int y, String[][] b, boolean isHit) {		
	String boom = "[X]";
	if(isHit){
		boom = "[X]";
	}
	else{
		boom = "[@]";
	}
	System.out.println();
	for (int i = 0; i < b.length; i++) {
		
		for (int j = 0; j < b.length; j++) {
			if(x == i && j == y){
				b[i][j] = boom;				
			}	
			//System.out.print(b[i][j]);	
		}
	}
	printBoard(b);
	System.out.println();
		
	}

	private static String[][] cpuShipBoard(String[][] b) {//çalışıyor
		Random r = new Random();
		int[] oriantation = new int[4]; // aşağı-yukarı belirleyicisi, 4 tane
										// için
		int[] x_position = new int[4]; // geminin başı, 4 tane için
		int[] y_position = new int[4];

		for (int a = 0; a < 4; a++) {
			String[] ship = ship(a + 2); // bu ship 2-3-4-5 uzunluğunda olcak
			oriantation[a] = r.nextInt(2);
			x_position[a] = r.nextInt(10);
			y_position[a] = r.nextInt(10);
			if (y_position[a] + ship.length > b.length || x_position[a] + ship.length > b.length) { 
				a--;
			}
			 else {
				if (oriantation[a] == 0) { // sağa doğru
					for (int j = 0; j < ship.length; j++) {
						if (b[x_position[a]][y_position[a] + j] != "[0]") {
							b[x_position[a]][y_position[a] + j] = ship[j];
						} else {							
							for (int i = 0; i < b.length; i++) {
								for (int s = 0; s < b.length; s++) {
									b[i][s] = "[ ]";
								}								
							}
							
							for (int i = 0; i < ship.length; i++) {
								ship[i] = "0";
							}
							j = ship.length;
							a = -1; //oha
						}
					}
				} else if (oriantation[a] == 1) { // aşağı doğru
					for (int k = 0; k < ship.length; k++) {
						if (b[x_position[a] + k][y_position[a]] != "[0]") {
							b[x_position[a] + k][y_position[a]] = ship[k];
						} else {
							for (int i = 0; i < b.length; i++) {
								for (int s = 0; s < b.length; s++) {
									b[i][s] = "[ ]";
								}
							}
							
							for (int i = 0; i < ship.length; i++) {
								ship[i] = "0";
							}
							k = ship.length;
							a = -1; //bu muymuş amk
						}
					}
				}
			}
		}

		return b;
	}

	public static String[] ship(int shipLength) { // generates a string array of
													// shipLength
		String shipPart = "[0]";
		String finShip[] = new String[shipLength];
		for (int i = 0; i < shipLength; i++) {
			finShip[i] = shipPart;
		}
		return finShip;
	}

	private static void printBoard(String[][] b) { // prints the two dimensional array
		
		for (int i = 0; i < b.length; i++) {
			System.out.print((i+1) + "  ");	
		}
		
		for (int i = 0; i < b.length; i++) {
			System.out.println();
			for (int j = 0; j < b.length; j++) {
				System.out.print(b[i][j]);
			}
			System.out.print(" " + (i+1));
		}
		
		System.out.println();
	}

	private static String[][] userShipedBoard(int x, int y, int orientation, int shipLength, String[][] b){
		if(orientation == 0){
			for (int i = 0; i < shipLength; i++) {
				b[x][y+i] = "[0]"; 
			}	
		}
		else if(orientation == 1){
			for (int i = 0; i < shipLength; i++) {
					b[x+i][y] = "[0]"; 			
			}	
		}
		return b;
	}
	
	private static String[][] boardCleaner(int x, int y, int orientation, int shipLength, String[][] b){
		if(orientation == 0){
			for (int i = 0; i < shipLength; i++) {
				b[x][y+i] = "[ ]";  
			}	
		}
		else if(orientation == 1){
			for (int i = 0; i < shipLength; i++) {
					b[x+i][y] = "[ ]"; 			
			}	
		}
		return b;
	}

	private static boolean comparer(int x, int y, String[][] b){
		if(b[x][y] == "[0]"){
			return true;
		}
		else{
			return false;
		}		
	}

    private static int[] bomberAi(int x, int y){
		int[] a = new int[2];
		Random r = new Random();		
			int generatedX = 0;
			int generatedY = 0;
		int subAdd = r.nextInt(4);
		while (true) {
			int xTemp = r.nextInt(2);
			int yTemp = r.nextInt(2);
			if (subAdd == 0 && (xTemp > 0 || yTemp > 0)) { // logic....
				if (yTemp != 1) {
					generatedX = xTemp + x;
				}
				else{
					generatedX = x;
				}
				generatedY = yTemp + y;
			} else if (subAdd == 1 && (xTemp > 0 || yTemp > 0)) {
				if (yTemp != 1) {
					generatedX = xTemp + x;
				}
				else{
					generatedX = x;
				}
				generatedY = y - yTemp;
			} else if (subAdd == 2 && (xTemp > 0 || yTemp > 0)) {
				if (yTemp != 1) {
					generatedX = x - xTemp;
				}
				else{
					generatedX = x;
				}
				generatedY = yTemp + y;
			} else if (subAdd == 3 && (xTemp > 0 || yTemp > 0)) {
				if (yTemp != 1) {
					generatedX = x - xTemp;
				}
				else{
					generatedX = x;
				}
				generatedY = y - yTemp;
			}
			if (generatedX >= 0 && generatedX < 10 && generatedY >= 0 && generatedY < 10) {
				break;
			}
		}
		
		a[0] = generatedX;
		a[1] = generatedY;
		return a;
	}
}
