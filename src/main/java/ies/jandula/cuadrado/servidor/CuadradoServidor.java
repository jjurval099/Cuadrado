package ies.jandula.cuadrado.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CuadradoServidor 
{
    public static final int PUERTO = 2000;
    
    public static void main(String[] args) 
    {
        
        ServerSocket socketServer = null;
        Socket socketCliente = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        
        try 
        {
            socketServer = new ServerSocket(PUERTO);            
            
            socketCliente = socketServer.accept();
            
            dataInputStream = new DataInputStream(socketCliente.getInputStream());
            int numero = Integer.parseInt(dataInputStream.readUTF());
            
            int resultado = numero * numero;
            
            dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
            dataOutputStream.writeUTF(String.valueOf(resultado));
            
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally
        {
            try 
            {
                if (dataInputStream != null)
                {
                    dataInputStream.close();
                }
                if (dataOutputStream != null)
                {
                    dataOutputStream.close();
                }
                if (socketCliente != null)
                {
                    socketCliente.close();
                }
                if (socketServer != null)
                {
                    socketServer.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }       
    }
}