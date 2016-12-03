import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.*;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class PortRead2
{

public static SerialPort userPort;
static InputStream in;

public static void main(String args[]) 
{   
	
	Scanner input = new Scanner(System.in);

    /*
     * This returns an array of commport addresses, not useful for the client 
     * but useful for iterating through to get an actual list of com parts available 
     */

//    SerialPort ports[] = SerialPort.getCommPorts();
//    int i = 1;
//
//    //User port selection
//    System.out.println("COM Ports available on machine");
//    for(SerialPort port : ports) //iterator to pass through port array
//    {
//        System.out.println(i++ + ": " + port.getSystemPortName()); //print windows com ports
//    }
//    System.out.println("Please select COM PORT: 'COM#'");
//    SerialPort userPort = SerialPort.getCommPort("cu.usbmodem1411");
//    
//    //Initializing port 
//    userPort.openPort();
//    if(userPort.isOpen())
//    {
//        System.out.println("Port initialized!");
//        //timeout not needed for event based reading
//
//        //userPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
//    }
//
//    else
//    {
//        System.out.println("Port not available");
//        return;
//    }   
    
    try{
        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/testingwav.wav"));
        Clip test = AudioSystem.getClip();  

        test.open(ais);
        test.start();

        while (!test.isRunning())
            Thread.sleep(10);
        while (test.isRunning())
            Thread.sleep(10);

        test.close();
        test.open(ais);
        test.start();

        while (!test.isRunning())
            Thread.sleep(10);
        while (test.isRunning())
            Thread.sleep(10);

        test.close();
    }catch(Exception ex){
        ex.printStackTrace();
    }

//    userPort.addDataListener(new SerialPortDataListener(){
//        @Override
//        public int getListeningEvents(){return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;}
//
//        public void serialEvent(SerialPortEvent event)
//        {
//            if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
//                return;
//            StringBuffer sb = new StringBuffer();
//            BufferedReader br = null;
//
//            Scanner s = new Scanner(userPort.getInputStream()).useDelimiter("\\A");
//            String result = s.hasNext() ? s.next() : "";
//            System.out.println(result);
//        }
//    });
}
}