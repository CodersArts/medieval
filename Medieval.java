

import javax.swing.JOptionPane;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;												// get counters working, name null prob, validation

public class Medieval {

	static PrintWriter outputfile;

	public static void main(String[] args) throws IOException {
		
		outputfile = new PrintWriter("winplay.txt");
		
		String playerInitials = "";
		int randomNum = randomNumInput();	
		String cpuchoice = null;
		String playerweap = null;
		String outmsg = null;
		String winlosemsg = null;
		int instance = 0;
		int plays = 0;
		int wins = 0;
		int loss = 0;
		int tie = 0;
		char play = 'y';
		
		int userOption=initialmenu(playerInitials, playerweap, cpuchoice, outmsg, winlosemsg, instance, plays, wins, loss,tie, play);
		
		if(userOption==1){
			playerInitials = playerInfo();
			while(true) {
				playerweap = playerInput(playerInitials);
				cpuchoice = cpuweaponselection(randomNum);
				instance = choosewinner(cpuchoice, playerweap);
				outmsg = msgarray(instance);
				winlosemsg = winlose(instance);		
				playresult(playerInitials,playerweap,"Computer", cpuchoice);
				playresult_2(outmsg);
				playresult_3(winlosemsg);
				plays = playcounter(instance);
				wins = wincounter(instance);
				loss = losscounter(instance);
				tie = tiecounter(instance);	
				
				consoleOutput(playerInitials, playerweap, cpuchoice, outmsg, winlosemsg, instance, plays, wins, loss, tie);
				printwriterOutput(playerInitials, playerweap, cpuchoice, outmsg, winlosemsg, instance, plays, wins, loss, tie);
				outputfile.close();
				
				ArrayList<String> countList = new ArrayList<String>();
				Scanner scanner = new Scanner("winplay.txt");
				while (scanner.hasNextLine())
					countList.add(scanner.nextLine());
				for (int i = 0; i < countList.size(); i++)
					System.out.println(i);	
				if(playAgain()==false)
					break;
				}
		}else if(userOption==2){
			playerInitials = playerInfo();
			String player2=playerInfo();
			String playerweap1="";
			String playerweap2="";
			while(true) {
				playerweap1 = playerInput(playerInitials);
				playerweap2 = playerInput(player2);
				instance = choosewinner(playerweap2, playerweap1);
				outmsg = msgarray(instance);
				winlosemsg = winlose(instance);		
				playresult(playerInitials,playerweap1,player2, playerweap2);
				playresult_2(outmsg);
				playresult_3(winlosemsg);
				plays = playcounter(instance);
				wins = wincounter(instance);
				loss = losscounter(instance);
				tie = tiecounter(instance);	
				
				consoleOutput(playerInitials, playerweap1, playerweap2, outmsg, winlosemsg, instance, plays, wins, loss, tie);
				printwriterOutput(playerInitials, playerweap1, playerweap2, outmsg, winlosemsg, instance, plays, wins, loss, tie);
				outputfile.close();
				
				ArrayList<String> countList = new ArrayList<String>();
				Scanner scanner = new Scanner("winplay.txt");
				while (scanner.hasNextLine())
					countList.add(scanner.nextLine());
				for (int i = 0; i < countList.size(); i++)
					System.out.println(i);	
				
				if(playAgain()==false)
					break;
			
		}
		}
	}
	
	/**
	 * @param 	
	 * @return
	 */
	public static String playerInfo () {				// fig. out why return = null
		
			 		 
				String playerInitials= null;
				int flag = 0;			
					do
					{
						if (flag == 1)
					{
							JOptionPane.showMessageDialog(null, "Initials only please");
					}
						playerInitials = JOptionPane.showInputDialog("Enter Player Name");
						
				flag = 1;

					}	while(!playerInitials.matches("[a-zA-Z]+"));									// user input for initials
					
		return playerInitials;
		
	}
	public static boolean playAgain(){
		boolean flag=false;
		String input = JOptionPane.showInputDialog("Do you want play again(y/n)\n\n");
		if(input.equalsIgnoreCase("y"))
			flag=true;
		return flag;	
	}
	public static int randomNumInput(){
		Random random=new Random();
		int stringnumber;
		stringnumber = random.nextInt(3)+1;
		return stringnumber;
	}
	/**
	 * 
	 */
	public static int initialmenu(String playerInitials, String playerweap, String cpuchoice, String outmsg, String winlosemsg,
									int instance, int plays, int wins, int loss, int tie, char play) {
		
		String input;
		
		
		input = JOptionPane.showInputDialog("Enter 1 to play: Player1 vs. CPU\nEnter 2 to play: Player1 vs. Player2\nEnter 3 for  View Winning  Summary\nEnter 4 to Exit\n\n");	// initial game menu
		int choice = Integer.parseInt(input);
			{
		switch (choice)
		{
			case 1: 
			play = 'y';
			gameRules();
			break;
			case 2: 
				play = 'y';
				gameRules();
				break;
			case 3: consoleOutput (playerInitials, playerweap, cpuchoice, outmsg, winlosemsg, instance, plays, wins, loss, tie);  // change to console output
			gameRules();
			break;	
			case 4:		
				choiceExit(); 																		// add console file output to satisfy requirements
			break;
		}
		return choice;
		}		
	}
	
	public static void gameRules() {
		JOptionPane.showMessageDialog(null, "The rules are: \n Rock beats Scissors . \n Scissors beats Paper. \n Paper beats Rock.");
	}
	public static void playresult(String player1,String playerweap1, String player2,String playerweap2) {
		JOptionPane.showMessageDialog(null, player1+" have chosen " + playerweap1 + " and "+player2+" have chosen " + playerweap2 +"\n");
		
	}
	public static void playresult_2(String outmsg) {
		JOptionPane.showMessageDialog(null,  outmsg);
	}
	public static void playresult_3(String winlosemsg) {
		JOptionPane.showMessageDialog(null,  winlosemsg);
	}

	public static String playerInput(String playerInitials) {
		
		JOptionPane.showMessageDialog(null, "Choose a weapon");
		
		String playerweap = JOptionPane.showInputDialog(null,   ("----------------------------------------\n") +
		("Medieval Weapon Selection (1, 2, 3)\n") + 
		("----------------------------------------\n") +
		("1.Rock\n2.Scissors\n3.Paper\n\n"));
    String playerchoice = null;
		
	if (Integer.parseInt(playerweap) == 1) {
			playerchoice = "Rock";
	}	else if (Integer.parseInt(playerweap) == 2) {
		playerchoice = "Scissors";
	}	else if (Integer.parseInt(playerweap) == 3) {
		playerchoice = "Paper";
	}
		return playerchoice;
	}
	
	public static String cpuweaponselection(int stringnumber) {
		String cpuchoice = null;
		
		if (stringnumber == 1) {
			cpuchoice = "Rock";
	}	else if (stringnumber == 2) {
		cpuchoice = "Paper";
	}	else if (stringnumber == 3) {
			cpuchoice = "Scissors";
	}	return cpuchoice;
	
	}
	
	public static  int choosewinner (String cpuchoice, String playerweap) {
		int instance = 0;
		
		if (cpuchoice.equals( "Rock")  && playerweap.equalsIgnoreCase("Scissors")) {		//Rock beats Scissors
			instance = 1;
		}
																			// cpu win
		else if (playerweap.equalsIgnoreCase("Rock") && cpuchoice.equals("Scissors")) {	
			instance = 2;
		}
																				//player win
	
		if (cpuchoice.equals( "Scissors")  && playerweap.equalsIgnoreCase("Paper")) {	//Scissors beats Paper
			instance = 3;
		}
			
																								// cpu win
		else if (playerweap.equalsIgnoreCase("Scissors") && cpuchoice.equals("Paper")) {
			instance = 4;
		}
																		// player win
		
		if (cpuchoice.equals( "Paper")  && playerweap.equalsIgnoreCase("Rock")) {	//Hammer beats Sword
			instance = 5;
		}
																		//cpu win
		else if (playerweap.equalsIgnoreCase("Paper") && cpuchoice.equals("Rock")) {
			instance = 6;
		}
																			//player win
		if (cpuchoice.equals( "Rock")  && playerweap.equalsIgnoreCase("Rock")) {	// tied
			instance = 7;
		}
		if (cpuchoice.equals( "Scissors")  && playerweap.equalsIgnoreCase("Scissors")) {	// tied
			instance = 8;
		}
		if (cpuchoice.equals( "Paper")  && playerweap.equalsIgnoreCase("Paper")) {	// tied
			instance = 9;
		}
		return instance;
		}
	
	public static int playcounter(int instance) {
		int plays = 0;
		if (instance !=0) {
			plays += 1;
		}return plays;
	} 
	public static int wincounter(int instance) {
		int wins = 0;
		
			switch(instance)
			{	
				case 2: {
					wins += 1;	
				} break;
				
				case 4: {
					wins += 1;
				} break;
				case 6: {
					wins += 1;
				}break;
			}return wins;
		
	}
	public static int losscounter(int instance) {
		int loss = 0;
		
		switch(instance)
		{	
			case 1: {
				loss += 1;	
			} break;
			
			case 3: {
				loss += 1;
			} break;
			case 5: {
				loss += 1;
			} break;
		}return loss;
	}
	public static int tiecounter(int instance) {
		int tie = 0;
		
			if (instance == 7) {
				tie += 1;
				if (instance ==8) {
					tie +=1;
				} if (instance == 9) {
					tie +=1;
				}	
			}return tie;
	}

	public static String msgarray(int instance) {
		
		String[] msgarray = new String[4];
		
		 msgarray[0] = "Rock beats Scissors!";					// win messages stored in array
		 msgarray[1] = "Scissors beats Paper";
		 msgarray[2] = "Paper beats Rock";
		 msgarray[3] = "Tied!";		
		 
		 
		 
		String outmsg = null;
		if (instance == 1) {
		outmsg = msgarray[0];
		
		 }
		 if (instance == 2) {
			 outmsg = msgarray[0];
			 
		 }
			 if (instance == 3) {
				 outmsg = msgarray[1];
				
			 }
				 if (instance == 4) {
					 outmsg = msgarray[1];
					 
				 }
					 if (instance == 5) {
						 outmsg = msgarray[2];
						
					 }
						 if (instance== 6) {
							 outmsg = msgarray[2];
							
						 }
							 if (instance == 7) {
								 outmsg = msgarray[3];
								 
							 }
								 if (instance == 8) {
									 outmsg = msgarray[3];
									 
								 }
									 if (instance == 9) {
										 outmsg = msgarray[3];
									 }
									 
									return outmsg;}   
										
	public static String winlose (int instance) {
		
		String[] winlosearray = new String[3];
		
		winlosearray[0] = "I have vanquished you!";					// win  lose messages stored in array
		winlosearray[1] = "You have vanquished me!";
		winlosearray[2] = "Our strength is equal, we must battle again!";
		
		
		
		String winlosemsg = null;
		if (instance == 1) {
			winlosemsg = winlosearray[0];
			
			 }
			 if (instance == 2) {
				 winlosemsg = winlosearray[1];
				 
			 }
				 if (instance == 3) {
					 winlosemsg = winlosearray[0];
					
				 }
					 if (instance == 4) {
						 winlosemsg = winlosearray[1];
						 
					 }
						 if (instance == 5) {
							 winlosemsg = winlosearray[0];
							
						 }
							 if (instance == 6) {
								 winlosemsg = winlosearray[1];
								
							 }
								 if (instance == 7) {
									 winlosemsg = winlosearray[2];
									 
								 }
									 if (instance == 8) {
										 winlosemsg = winlosearray[2];
										 
									 }
										 if (instance == 9) {
											 winlosemsg = winlosearray[2];
										 }
										return winlosemsg;
	}



	public static void choiceExit() {
		System.out.println("You chose to Exit, Goodbye");
		System.exit(0);
	}
	
	/**
	 * 
	 * 
	 *@param playerInitials
	 */

	public static void consoleOutput ( String playerInitials, String playerweap, String cpuchoice, String outmsg, String winlossmsg,
										int instance, int plays, int wins, int loss, int tie) {
		
		System.out.printf("Hello %s\n", playerInitials);
		System.out.println(outmsg);
		System.out.println(winlossmsg);
		System.out.println("\n\n\n");
		System.out.printf("Games Played: \t%d \n", plays);
		System.out.printf("Games Won: \t%d \n", wins);
		System.out.printf("Games Lost: \t%d \n", loss);
		System.out.printf("Games Tied: \t%d \n",tie);
		
		

		
	}
	/**
	 * 
	 * @param playerInitials
	 */
	public static void printwriterOutput (String playerInitials, String playerweap, String cpuchoice, String outmsg, String winlossmsg,
											int instance, int plays, int wins, int loss, int tie) {
		outputfile.printf("Hello %s ", playerInitials);
		outputfile.println(outmsg);
		outputfile.println(winlossmsg);
		outputfile.println("\n\n\n");
		outputfile.println("Games Played:\t" + plays);
		outputfile.println("Games Won: \t" + wins);
		outputfile.println("Games Lost: \t" + loss);
		outputfile.println("Games Tied: \t" + tie);
		
	}
}
