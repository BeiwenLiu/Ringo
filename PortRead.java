import com.fazecast.jSerialComm.*;
import java.util.*;


import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.bluetooth.*;
import javax.bluetooth.UUID;
import javax.microedition.io.*;

public class PortRead 
{

public static SerialPort userPort;
static InputStream in;

public static void main(String args[]) 
{   
	try {
		LocalDevice localDevice = LocalDevice.getLocalDevice();
	    System.out.println("Address: "+localDevice.getBluetoothAddress());
	    System.out.println("Name: "+localDevice.getFriendlyName());
	
//	    PortRead sampleSPPServer=new PortRead();
	    //sampleSPPServer.startServer();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	Scanner input = new Scanner(System.in);

    /*
     * This returns an array of commport addresses, not useful for the client 
     * but useful for iterating through to get an actual list of com parts available 
     */

    SerialPort ports[] = SerialPort.getCommPorts();
    int i = 1;

    //User port selection
    System.out.println("COM Ports available on machine");
    for(SerialPort port : ports) //iterator to pass through port array
    {
        System.out.println(i++ + ": " + port.getSystemPortName()); //print windows com ports
    }
    System.out.println("Please select COM PORT: 'COM#'");
    SerialPort userPort = SerialPort.getCommPort("cu.usbmodem1411");
    
    //Initializing port 
    userPort.openPort();
    if(userPort.isOpen())
    {
        System.out.println("Port initialized!");
        //timeout not needed for event based reading

        //userPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
    }

    else
    {
        System.out.println("Port not available");
        return;
    }   

    userPort.addDataListener(new SerialPortDataListener(){
        @Override
        public int getListeningEvents(){return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;}

        public void serialEvent(SerialPortEvent event)
        {
            if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                return;
            StringBuffer sb = new StringBuffer();
            BufferedReader br = null;

            Scanner s = new Scanner(userPort.getInputStream()).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            System.out.println(result);
        }
    });
}
//private void startServer() throws IOException{
//
//    //Create a UUID for SPP
//    UUID uuid = new UUID("1101", true);
//    //Create the servicve url
//    String connectionString = "btspp://localhost:" + uuid +";name=Sample SPP Server";
//
//    //open server url
//    StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier)Connector.open( connectionString );
//
//    //Wait for client connection
//    System.out.println("\nServer Started. Waiting for clients to connect...");
//    StreamConnection connection=streamConnNotifier.acceptAndOpen();
//
//    RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
//    System.out.println("Remote device address: "+dev.getBluetoothAddress());
//    System.out.println("Remote device name: "+dev.getFriendlyName(true));
//
//    //read string from spp client
//    InputStream inStream=connection.openInputStream();
//    BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));
//    String lineRead=bReader.readLine();
//    System.out.println(lineRead);
//
//    //send response to spp client
//    OutputStream outStream=connection.openOutputStream();
//    BufferedWriter bWriter=new BufferedWriter(new OutputStreamWriter(outStream));
//    bWriter.write("Response String from SPP Server\r\n");
//
//    /*PrintWriter pWriter=new PrintWriter(new OutputStreamWriter(outStream));
//    pWriter.write("Response String from SPP Server\r\n");
//    pWriter.flush();
//    pWriter.close();*/
//
//    streamConnNotifier.close();
//
//}
}