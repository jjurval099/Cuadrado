package ies.jandula.cuadrado.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import ies.jandula.cuadrado.servidor.CuadradoServidor;

public class CuadradoCliente 
{
    private static final String HOST ="localhost";
    
    public static void main(String[] args) 
    {
        Scanner scanner = null;
        Socket socketCliente = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        try 
        {
            scanner = new Scanner(System.in);
            
            socketCliente = new Socket(HOST, CuadradoServidor.PUERTO);
            
            System.out.println("Introduce numero: ");
            String numero = scanner.nextLine();
            
            dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());    
            dataOutputStream.writeUTF(numero);
            
            dataInputStream = new DataInputStream(socketCliente.getInputStream());
			System.out.println(dataInputStream.readUTF());
            
        } 
        catch (UnknownHostException unknownHostException) 
        {
            unknownHostException.printStackTrace();
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally
        {           
            try 
            {
                if (dataOutputStream != null)
                {
                    dataOutputStream.close();
                }
                if (dataInputStream != null)
                {
                    dataInputStream.close();
                }
                if (socketCliente != null)
                {
                    socketCliente.close();
                }
                if (scanner != null)
                {
                    scanner.close();
                }
            } 
            catch (IOException ioException) 
            {
                ioException.printStackTrace();
            }
            
        }
    }
}
