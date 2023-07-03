import java.io.*;
import java.net.*;

public class ServerApplication {
    public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String englishText = null;
			try {
				englishText = (String) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            String translatedText = translate(englishText);
            out.writeObject(translatedText);
            socket.close();
        }
    }
    private static String translate(String englishText) {
        // TODO: Implement translation logic
        return englishText;
    }
}
