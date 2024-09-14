import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
public class QrCodeGenration {
    public static void generateQRCode(String filePath, String outputPath) throws Exception 
    {
        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix matrix = writer.encode(filePath, BarcodeFormat.QR_CODE, 300, 300, hints);
        MatrixToImageWriter.writeToPath(matrix, "PNG", Paths.get(outputPath));
    }
    public static void startFileServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/file.txt", (HttpExchange exchange) -> {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the file path to share: ");
            String filePath = scanner.nextLine();
            File file = new File(filePath);
            byte[] bytes = new FileInputStream(file).readAllBytes();

            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        });

        server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Server started at: http://localhost:8080/file.txt");
    }

    public static void main(String[] args) 
    {
        try 
        {
            String fileToShare = "http://localhost:8080/file.txt"; // File on local server
            String outputFilePath = "E:\\vs code for java\\demo\\src\\QrCode.png"; // Path to save the QR code
            generateQRCode(fileToShare, outputFilePath);
            System.out.println("QR Code generated at: " + outputFilePath);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
