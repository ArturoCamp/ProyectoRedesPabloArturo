/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorredes;

import Data.connection;
import Domain.Usuarios;
import GUI.Ventana;
import GUI.VentanaPrincipal;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.*;
import java.net.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.ListAdapter;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author Pablo
 */
public class ServidorRedes {
    
   static ArrayList<Usuarios> lista = new ArrayList<>(100);

    /**
     * @param args the command line arguments
     */
   
   
 
       public static void iniciarServidor() throws IOException 
       {
               ServerSocket servidor  = new ServerSocket( 4400 );

          System.out.println( "Esperando recepcion de archivos..." ); 
          while( true )
          {
 
            try
            {
                
               Socket cliente = servidor.accept(); 
 
               // Creamos flujo de entrada para leer los datos que envia el cliente 
               DataInputStream dis = new DataInputStream( cliente.getInputStream() );
        
               // Obtenemos el nombre del archivo
               String nombreArchivo = dis.readUTF().toString(); 
 
               // Obtenemos el tamaño del archivo
               int tam = dis.readInt(); 
               String nombreUsuario=dis.readUTF();
 
               System.out.println( "Recibiendo archivo "+nombreArchivo );
        
      
       File directorio = new File("directorios//"+nombreUsuario+"//");
               FileOutputStream fos = new FileOutputStream( directorio+"//"+nombreArchivo);
               BufferedOutputStream out = new BufferedOutputStream( fos );
               BufferedInputStream in = new BufferedInputStream( cliente.getInputStream() );

               byte[] buffer = new byte[ tam ];
 
               for( int i = 0; i < buffer.length; i++ )
               {
                  buffer[ i ] = ( byte )in.read( ); 
               }
 
               // Escribimos el archivo 
               out.write( buffer ); 
 
               // Cerramos flujos
               out.flush(); 
               in.close();
               out.close(); 
               //cliente.close();
 
               System.out.println( "Archivo Recibido "+nombreArchivo );
        
           }
           catch( Exception e )
           {
              System.out.println( "Recibir: "+e.toString() ); 
           }
         } 
       }
         

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
          //Ventana ventana = new Ventana();
        VentanaPrincipal v =new VentanaPrincipal();
        v.setVisible(true);
  iniciarServidor();        
    }

 
        public void crearDirectorio(String nombre){
   
        File directorio = new File("directorios//"+nombre);
        System.out.println("C:\\Users\\Pablo\\Desktop\\ServidorRedes"+nombre);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    
    }
    
    
}
