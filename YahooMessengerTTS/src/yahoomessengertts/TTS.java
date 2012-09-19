package yahoomessengertts;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import java.io.FileInputStream;

public class TTS
{
    void call(String msg)
    {
        Voice voice;
        FreeTTS freetts;
        VoiceManager vm=VoiceManager.getInstance();
        voice=vm.getVoice("kevin16");
        System.setProperty("com.sun.speech.freetts.voice.defaultAudioPlayer", "com.sun.speech.freetts.audio.SingleFileAudioPlayer");
        FileInputStream fis;
        SingleFileAudioPlayer sfap;
        if(voice!=null)
        {
            voice.allocate();
        }
        //System.out.println("---1---");
        freetts=new FreeTTS(voice);
        String text = new String(msg);
        byte b[] = text.getBytes();
        if(b==null)
        {
            System.out.println("no byte array");
            System.exit(1);
        }
        try
        {
            sfap = (SingleFileAudioPlayer)voice.getDefaultAudioPlayer();
            sfap.write(b);
        }
        catch(Exception e)
        {
        }
        voice.speak(msg);
        voice.deallocate();
        AePlayWave aw = new AePlayWave( "freetts.wav" );
        aw.run(); 
    }
    TTS()
    {
    
    }
    TTS(String str)
    {
        call(str);
    }
}