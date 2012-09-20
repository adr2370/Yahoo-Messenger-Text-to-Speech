package yahoomessengertts;

 
import com.google.code.chatterbotapi.*;
import java.util.*;
public class ChatterBotApiTest 
{    
    public ChatterBotApiTest()
    {
    }
    public static void main(String[] args) throws Exception 
    {
        //System.out.println("Begin Talking:");
        ChatterBotFactory factory = new ChatterBotFactory();

        ChatterBot bot1 = factory.create(ChatterBotType.CLEVERBOT);
        ChatterBotSession bot1session = bot1.createSession();

        ChatterBot bot2 = factory.create(ChatterBotType.PANDORABOTS, "d689f7b8de347251");
        ChatterBotSession bot2session = bot2.createSession();

        String s = "Hi";
        String my = "";
        s = new Scanner(System.in).nextLine();
        while (!s.toLowerCase().equals("exit")) {
            write("bot1> " + s);
            TTS speak2=new TTS(s);
            my=response(s);
            if(!my.equals(""))
                s = my;
            else
                s = bot2session.think(s);
            write("bot2> " + s);
            TTS speak=new TTS(s);
            s = new Scanner(System.in).nextLine();
            //s = bot1session.think(s);
        }
    }
    public static void write(String s) {
        System.out.println(s);
    }
    public static String response(String s) throws Exception {
        s=s.toLowerCase();
        for(int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            if(c=='?'||c=='.'||c==';'||c==':'||c==','||c=='!') s=s.substring(0,i)+s.substring(i+1,s.length());
        }
        if(greeting(s)) return "Greetings from U.C.L.A Yahoo Hack U.";
        if(askAboutGoingToUCLA(s)) return "Yes I do.";
        if(askLoveYahoo(s)) return "Of course I love Yahoo.";
        if(askForJoke(s)) return aJoke();
        if(askAboutBestSchool(s)) return "U.C.L.A is the best school.";
        if(askAboutWorstSchool(s)) return "U.S.C is the worst school.";
        if(askForEightClap(s)) {
            AePlayWave aw = new AePlayWave( "eightClap.wav" );
            aw.run(); 
            return "I love that cheer.";
        }
        ChatterBotFactory factory = new ChatterBotFactory();
        ChatterBot bot1 = factory.create(ChatterBotType.CLEVERBOT);
        ChatterBotSession bot1session = bot1.createSession();
        return bot1session.think(s);
    }
    public static boolean askForEightClap(String s) {
        if(contains(s,"8clap")||contains(s,"eightclap")) return true;
        if(!contains(s,"8")||!contains(s,"eight")) return false;
        if(!contains(s,"clap")) return false;
        return true;
    }
    private static boolean contains(String st,String s) {
        StringTokenizer newSt=new StringTokenizer(st);
        while(newSt.hasMoreTokens()) {
            String n=newSt.nextToken();
            if(n.equals(s)) return true;
        }
        return false;
    }
    private static boolean askAboutWorstSchool(String s) {
        int num=0;
        if(contains(s,"what")) num++;
        if(contains(s,"is")) num++;
        if(contains(s,"the")) num++;
        if(contains(s,"worst")) num++;
        else return false;
        if(contains(s,"school")) num++;
        else return false;
        return num>=4;
    }
    private static boolean askAboutBestSchool(String s) {
        int num=0;
        if(contains(s,"what")) num++;
        if(contains(s,"is")) num++;
        if(contains(s,"the")) num++;
        if(contains(s,"best")) num++;
        else return false;
        if(contains(s,"school")) num++;
        else return false;
        return num>=4;
    }
    private static boolean greeting(String s) {
        if(contains(s,"hi")) return true;
        if(contains(s,"hello")) return true;
        if(contains(s,"hey")) return true;
        return false;
    }
    static int lastJoke=-1;
    private static String aJoke() {
        Random rand=new Random();
        int numJokes=4;
        int nextJoke=rand.nextInt(numJokes);
        if(nextJoke==lastJoke) nextJoke=-1;
        else lastJoke=nextJoke;
        switch(nextJoke) {
            case 0:
            return "What do you do with a dead scientist?\nYou barium.";
            case 1:
            return "What is beethoven doing in his grave?\nDecomposing.";
            case 2:
            return "Why is gambling illegal in Africa?\nThere are too many cheetahs.";
            case 3:
            return "Why is an ocean friendly?\nBecause it waves.";
            default:
            return "I can't think of a joke. :(";
        }
    }
    private static boolean askForJoke(String s) {
        int num=0;
        if(contains(s,"tell")||contains(s,"another")) num++;
        else return false;
        if(contains(s,"joke")) num++;
        else return false;
        return num>=2;
    }
    private static boolean askLoveYahoo(String s) {
        int num=0;
        if(contains(s,"do")) num++;
        if(contains(s,"you")) num++;
        if(contains(s,"love")) num++;
        else return false;
        if(contains(s,"yahoo")) num++;
        else return false;
        return num>=3;
    }
    private static boolean askAboutGoingToUCLA(String s) {
        int num=0;
        if(contains(s,"do")) num++;
        if(contains(s,"you")) num++;
        if(contains(s,"go")) num++;
        else return false;
        if(contains(s,"to")) num++;
        else return false;
        if(contains(s,"ucla")) num++;
        else return false;
        return num>=5;
    }
}