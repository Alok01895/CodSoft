import java.util.*;

class Player
{
    int attempt;
    int score;
    public Player(int attempt,int score)
    {
        this.attempt=attempt;
        this.score=score;
    }
}
public class NumberGameTASK1
{

    public void hint(int guess,int number,int countAttempt)
    {
        if(guess<number)
        {
            if(guess<number/2)
            {
                System.out.println("Try Again! Your guess is too low...");
                return;
            }
            else{
                System.out.println("Try Again! Your guess is low but close...");
                return;
            }
        }
        if(guess>number)
        {
            if(guess>number+(number/2))
            {
                System.out.println("Try Again! Your guess is too high...");
                return;
            }
            else{
                System.out.println("Try Again! Your guess is high but close...");
                return;
            }
        }
        
        
    }

    public void celebrate(int guess, int number, int countAttempt)
    {
        if(guess==number)
        {
            if(countAttempt==1)
            {
                 System.out.println("Bravo! You Rocked...  #Genius");
                 return;
            }
            else{
                System.out.println("Hurray! You guessed correctly...");
                return;
            }
        }
    }

    public boolean game(Player player,int number)
    {
        Scanner sc=new Scanner(System.in);
        boolean win=false;
        int countAttempt=0;
        while(countAttempt!=player.attempt)
        {
            countAttempt++;
            int guess=sc.nextInt();
            if(guess==number)
            {
                celebrate(guess,number,countAttempt);
                win=true;
                break;
            }
            else{
                hint(guess,number,countAttempt);
            }
        }
        return win;
    }

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        // Instantiating the random class
        Random random=new Random();


        // Instantiating our player
        Player player=new Player(8,0); //Setting the number of attempts which a player can make to 8


        System.out.println("Let the fun begin!");

        while(true)
        {
            System.out.println("Guess the number:");

            // Genearting a random number between 1 and 100;
            int randomNumber= random.nextInt(100) +1;
            System.out.println("Just to show!  " + "Generated random number by system---> " + randomNumber);


            NumberGameTASK1 obj=new NumberGameTASK1();
            boolean win=obj.game(player,randomNumber); //taking the win status
            if(win)
            {
                player.score+=1;
                System.out.println("Your Score:- " + player.score);
            }
            else{
                System.out.println("Don't be sad! Better luck next time");
                System.out.println("You have potential! Keep shining...");
                System.out.println("Your Score:- " + player.score);
            }

            System.out.println();
            System.out.println("Wanna play another round?..");
            System.out.println("Press 1 to get the  show started..");
            System.out.println("Press 0 to exit");


            int chance=sc.nextInt();
            while(chance!=0 && chance!=1)
            {
                System.out.println("Invalid choice my friend...");
                System.out.println("Press 1 to get the  show started..");
                System.out.println("Press 0 to exit");
                chance=sc.nextInt();
            }
            if(chance==0)
            {
                System.out.println("will be waiting for you for the next time:)");
                System.out.println("Hope to see you soon.");
                System.out.println("Bye Bye!");
                break; 
            }
            

        }

    }
}