import java.net.*;
import java.io.*; 

public class Client
{
	// inicializamos socket and input-output streams y el bufferedreader
	private Socket socket		 = null;
	private DataInputStream input = null;
    private DataOutputStream out	 = null;
    private BufferedReader br = null;

	// constructor to put ip address and port
	public Client(String address, int port)
	{
        // establish a connection
        
        
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected");

			// takes input from terminal
            input = new DataInputStream(System.in);
            
            br = new BufferedReader(new InputStreamReader(input));


			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}

		// string to read message from input
		String line = "";

		// keep reading until "Over" is input
		while (!line.equals("Over"))
		{
			try
			{
				
                line = br.readLine();
				out.writeUTF(line);
			}
			catch(IOException i)
			{
				System.out.println(i);
			}
		}

		// close the connection
		try
		{
			input.close();
			out.close();
			socket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String[] args)
	{
		Client client = new Client("127.0.0.1", 5000);
	}
}